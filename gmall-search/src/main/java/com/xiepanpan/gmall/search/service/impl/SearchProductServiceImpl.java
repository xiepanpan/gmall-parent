package com.xiepanpan.gmall.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiepanpan.gmall.search.SearchProductService;
import com.xiepanpan.gmall.vo.search.SearchParam;
import com.xiepanpan.gmall.vo.search.SearchResponse;
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
import org.springframework.stereotype.Component;

/**
 * @author: xiepanpan
 * @Date: 2020/2/12
 * @Description:
 */
@Service
@Component
@Slf4j
public class SearchProductServiceImpl implements SearchProductService {
    @Override
    public SearchResponse searchProduct(SearchParam searchParam) {
        // 1. 构建检索条件
        String dsl = buildDsl(searchParam);
        log.info("商品检索的详细数据{}",dsl);
        // 2. 检索
        // 3. 将返回的SearchResult转为SearchResponse


        return null;
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
