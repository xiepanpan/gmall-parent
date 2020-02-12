package com.xiepanpan.gmall.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiepanpan.gmall.search.SearchProductService;
import com.xiepanpan.gmall.vo.search.SearchParam;
import com.xiepanpan.gmall.vo.search.SearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiepanpan
 * @Date: 2020/2/12
 * @Description:  商品检索的controller
 */
@RestController
public class ProductSearchController {

    @Reference
    SearchProductService searchProductService;

    @GetMapping("/search")
    public SearchResponse productSearchResponse(SearchParam searchParam) {
        SearchResponse searchResponse = searchProductService.searchProduct(searchParam);
        return searchResponse;
    }

}
