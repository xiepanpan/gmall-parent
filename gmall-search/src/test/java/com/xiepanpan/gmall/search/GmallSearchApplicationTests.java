package com.xiepanpan.gmall.search;

import com.xiepanpan.gmall.vo.search.SearchParam;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class GmallSearchApplicationTests {

    @Autowired
    JestClient jestClient;
    @Autowired
    SearchProductService searchProductService;

    @Test
    public void dslTest() {
        SearchParam searchParam = new SearchParam();
        searchParam.setKeyword("手机");
        searchProductService.searchProduct(searchParam);
    }

    @Test
    public void contextLoads() throws IOException {
        Search build = new Search.Builder("").addIndex("product").addType("info").build();
        SearchResult execute = jestClient.execute(build);
        log.info(String.valueOf(execute.getTotal()));
    }

    @Test
    public void testSearchSource() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        searchSourceBuilder.query(boolQueryBuilder);
        log.info(searchSourceBuilder.toString());
    }



}
