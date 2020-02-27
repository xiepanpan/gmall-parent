package com.xiepanpan.gmall.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.xiepanpan.gmall.pms.service.ProductService;
import com.xiepanpan.gmall.to.CommonResult;
import com.xiepanpan.gmall.to.es.EsProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiepanpan
 * @Date: 2020/2/26
 * @Description:  商品详情控制层
 */
@RestController
public class ProductItemController {

    @Reference
    ProductService productService;

    /**
     * 商品详情
     * @param id
     * @return
     */
    @GetMapping("/item/{id}.html")
    public CommonResult productInfo(@PathVariable("id") Long id) {
        EsProduct esProduct = productService.productAllInfo(id);
        return new CommonResult().success(esProduct);
    }

    @GetMapping("/item/sku/{id}.html")
    public CommonResult productSkuInfo(@PathVariable("id")Long id) {
        EsProduct esProduct = productService.productSkuInfo(id);
        return new CommonResult().success(esProduct);
    }

}
