package com.xiepanpan.gmall.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiepanpan.gmall.cart.service.CartService;
import com.xiepanpan.gmall.cart.vo.CartResponse;
import com.xiepanpan.gmall.to.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author: xiepanpan
 * @Date: 2020/3/6
 * @Description: 购物车控制层
 */
@Api
@RequestMapping("/cart")
@RestController
public class CartController {

    @Reference
    CartService cartService;

    @PostMapping("/add")
    public CommonResult addToCart(@RequestParam("skuId") Long skuId,
                                  @RequestParam(value = "num",defaultValue = "1")Integer num,
                                  @RequestParam(value = "cartKey",required = false)String cartKey,
                                  @RequestParam(value = "accessToken",required = false)String accessToken) throws ExecutionException, InterruptedException {
        CartResponse cartResponse = cartService.addToCart(skuId, num, cartKey, accessToken);
        return new CommonResult().success(cartResponse);
    }
}
