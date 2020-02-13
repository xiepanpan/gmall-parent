package com.xiepanpan.gmall.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.xiepanpan.gmall.constant.EsConstant;
import com.xiepanpan.gmall.search.SearchProductService;
import com.xiepanpan.gmall.to.es.EsProduct;
import com.xiepanpan.gmall.vo.search.SearchParam;
import com.xiepanpan.gmall.vo.search.SearchResponse;
import com.xiepanpan.gmall.vo.search.SearchResponseAttrVo;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiepanpan
 * @Date: 2020/2/12
 * @Description:
 */
@Service
@Component
@Slf4j
public class SearchProductServiceImpl implements SearchProductService {

    @Autowired
    JestClient jestClient;

    @Override
    public SearchResponse searchProduct(SearchParam searchParam) {
        // 1. 构建检索条件
        String dsl = buildDsl(searchParam);
        log.info("商品检索的详细数据{}",dsl);
        Search search = new Search.Builder(dsl).addIndex(EsConstant.PRODUCT_ES_INDEX)
                .addType(EsConstant.PRODUCT_INFO_ES_TYPE).build();
        // 2. 检索
        SearchResult execute = null;
        try {
            execute = jestClient.execute(search);
        } catch (IOException e) {
            log.error("商品检索异常:{}"+e);
        }
        // 3. 将返回的SearchResult转为SearchResponse
        SearchResponse searchResponse = buildSearchResponse(execute);
        searchResponse.setPageNum(searchParam.getPageNum());
        searchResponse.setPageSize(searchParam.getPageSize());

        return null;
    }

    /**
     * 构建检索结果
     * @param execute
     * @return
     */
    private SearchResponse buildSearchResponse(SearchResult execute) {
        SearchResponse searchResponse = new SearchResponse();
        MetricAggregation aggregations = execute.getAggregations();

        //===================以下是分析聚合品牌信息===============================
        TermsAggregation brand_agg = aggregations.getTermsAggregation("brand_agg");
        List<String> brandNames = new ArrayList<>();
        brand_agg.getBuckets().forEach(bucket->{
            String keyAsString = bucket.getKeyAsString();
            brandNames.add(keyAsString);
        });
        SearchResponseAttrVo attrVo = new SearchResponseAttrVo();
        attrVo.setName("品牌");
        attrVo.setValue(brandNames);

        searchResponse.setBrand(attrVo);
        //=====================品牌提取完成===========================

        //=============以下提取分类信息======================
        TermsAggregation category_agg = aggregations.getTermsAggregation("category_agg");
        List<String> categoryValues = new ArrayList<>();
        category_agg.getBuckets().forEach(bucket->{
            String categoryName = bucket.getKeyAsString();
            TermsAggregation categoryId_agg = bucket.getTermsAggregation("categoryId_agg");
            String categoryId = categoryId_agg.getBuckets().get(0).getKeyAsString();
            Map<String,String> map = new HashMap<String,String>();
            map.put("id",categoryId);
            map.put("name",categoryName);
            String cateInfo = JSON.toJSONString(map);
            categoryValues.add(cateInfo);
        });

        SearchResponseAttrVo catelog = new SearchResponseAttrVo();
        catelog.setName("分类");
        catelog.setValue(categoryValues);

        searchResponse.setCatelog(catelog);
        //==================分类信息提取完成========================

        //==================提取聚合的属性信息=============
        TermsAggregation termsAggregation = aggregations.getChildrenAggregation("attr_agg").getTermsAggregation("attrName_agg");
        List<SearchResponseAttrVo> attrList = new ArrayList<>();

        termsAggregation.getBuckets().forEach(bucket->{
            //属性名字
            SearchResponseAttrVo vo = new SearchResponseAttrVo();
            String attrName = bucket.getKeyAsString();
            vo.setName(attrName);

            //属性id
            TermsAggregation attrIdAgg = bucket.getTermsAggregation("attrId_agg");
            vo.setProductAttributeId(Long.valueOf(attrIdAgg.getBuckets().get(0).getKeyAsString()));

            //属性所涉及的所有值
            TermsAggregation attrValueAgg = bucket.getTermsAggregation("attrValue_agg");
            List<String> valueList = new ArrayList<>();
            attrValueAgg.getBuckets().forEach(valueBucket->{
                valueList.add(valueBucket.getKeyAsString());
            });
            vo.setValue(valueList);
            attrList.add(vo);
        });
        searchResponse.setAttrs(attrList);
        //==================提取聚合的属性信息完成=============

        //===============提取检索到的商品数据=========================
        List<SearchResult.Hit<EsProduct, Void>> hits = execute.getHits(EsProduct.class);
        List<EsProduct> esProducts = new ArrayList<>();
        hits.forEach(hit->{
            EsProduct source = hit.source;
            //提取到高亮结果
            String title = hit.highlight.get("skuProductInfos.skuTitle").get(0);
            //设置高亮结果
            source.setName(title);
            esProducts.add(source);
        });
        searchResponse.setProducts(esProducts);
        //==============提取检索到的商品数据完成============
        searchResponse.setTotal(execute.getTotal());
        return searchResponse;
    }

    private String buildDsl(SearchParam searchParam) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //1.查询
        // 1.1 检索
        if (!StringUtils.isEmpty(searchParam.getKeyword())) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("skuProductInfos.skuTitle", searchParam.getKeyword());
            NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("skuProductInfos", matchQuery, ScoreMode.None);
            boolQueryBuilder.must(nestedQuery);
        }
        // 1.2 过滤
        if (searchParam.getCatelog3()!=null&&searchParam.getCatelog3().length>0) {
            //按照三级分类过滤
            boolQueryBuilder.filter(QueryBuilders.termQuery("productCategoryId",searchParam.getCatelog3()));
        }
        if (searchParam.getBrand()!=null&&searchParam.getBrand().length>0) {
            //按照品牌分类过滤
            boolQueryBuilder.filter(QueryBuilders.termQuery("brandName.keyword",searchParam.getBrand()));
        }
        if (searchParam.getProps()!=null&&searchParam.getProps().length>0) {
            //按照所有的筛选属性进行过滤
            String[] props = searchParam.getProps();
            for (String prop :props) {
                //2:4g-3g； 2号属性的值是4g或者3g
                String[] split = prop.split(":");
                BoolQueryBuilder must = QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("attrValueList.productAttributeId", split[0]))
                        .must(QueryBuilders.termQuery("attrValueList.value.keyword", split[1].split("-")));
                NestedQueryBuilder query = QueryBuilders.nestedQuery("attrValueList", must, ScoreMode.None);
                boolQueryBuilder.filter(query);
            }
        }
        if (searchParam.getPriceFrom()!=null || searchParam.getPriceTo()!=null) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");
            if (searchParam.getPriceFrom()!=null) {
                rangeQueryBuilder.gte(searchParam.getPriceFrom());
            }
            if (searchParam.getPriceTo()!=null) {
                rangeQueryBuilder.lte(searchParam.getPriceTo());
            }
            boolQueryBuilder.filter(rangeQueryBuilder);
        }

        builder.query(boolQueryBuilder);
        //2.高亮
        if (StringUtils.isEmpty(searchParam.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuProductInfos.skuTitle");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");
            builder.highlighter(highlightBuilder);
        }
        //3. 聚合
        //按照品牌的
        TermsAggregationBuilder brand_agg = AggregationBuilders.terms("brand_agg").field("brandName.keyword");
        brand_agg.subAggregation(AggregationBuilders.terms("brandId").field("brandId"));
        builder.aggregation(brand_agg);

        //按照分类的
        TermsAggregationBuilder category_agg = AggregationBuilders.terms("category_agg").field("productCategoryName.keyword");
        category_agg.subAggregation(AggregationBuilders.terms("categoryId_agg").field("productCategoryId"));
        builder.aggregation(category_agg);

        //按照属性的
        NestedAggregationBuilder attr_agg = AggregationBuilders.nested("attr_agg", "attrValueList");
        TermsAggregationBuilder attrName_agg = AggregationBuilders.terms("attrName_agg").field("attrValueList.name");
        //聚合看attrValue的值
        attrName_agg.subAggregation(AggregationBuilders.terms("attrValue_agg").field("attrValueList.value.keyword"));
        //聚合看attrId的值
        attrName_agg.subAggregation(AggregationBuilders.terms("attrId_agg").field("attrValueList.productAttributeId"));

        attr_agg.subAggregation(attrName_agg);
        builder.aggregation(attr_agg);

        //4. 分页
        builder.from((searchParam.getPageNum()-1)*searchParam.getPageSize());
        builder.size(searchParam.getPageSize());
        //5. 排序
        if (!StringUtils.isEmpty(searchParam.getOrder())) {
            String order = searchParam.getOrder();
            String[] split = order.split(":");
            if (split[0].equals("0")) {
                //综合排序 默认顺序
            } else if (split[0].equals("1")) {
                //销量
                FieldSortBuilder sale = SortBuilders.fieldSort("sale");
                if (split[1].equalsIgnoreCase("asc")) {
                    sale.order(SortOrder.ASC);
                }else {
                    sale.order(SortOrder.DESC);
                }
                builder.sort(sale);
            } else if (split[0].equals("2")) {
                FieldSortBuilder price = SortBuilders.fieldSort("price");
                if (split[1].equalsIgnoreCase("asc")) {
                    price.order(SortOrder.ASC);
                }else {
                    price.order(SortOrder.DESC);
                }
                builder.sort(price);
            }

        }
        return builder.toString();
    }
}
