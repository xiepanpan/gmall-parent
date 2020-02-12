package com.xiepanpan.gmall.search;

import com.xiepanpan.gmall.vo.search.SearchParam;
import com.xiepanpan.gmall.vo.search.SearchResponse;

/**
 * 商品检索服务
 */
public interface SearchProductService {

    SearchResponse searchProduct(SearchParam searchParam);
}
