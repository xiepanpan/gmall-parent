package com.xiepanpan.gmall.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiepanpan.gmall.cart.service.CartService;
import com.xiepanpan.gmall.cart.vo.CartResponse;
import com.xiepanpan.gmall.to.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加购物车
     * @param skuId
     * @param num
     * @param cartKey
     * @param accessToken
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/add")
    public CommonResult addToCart(@RequestParam("skuId") Long skuId,
                                  @RequestParam(value = "num",defaultValue = "1")Integer num,
                                  @RequestParam(value = "cartKey",required = false)String cartKey,
                                  @RequestParam(value = "accessToken",required = false)String accessToken) throws ExecutionException, InterruptedException {
        CartResponse cartResponse = cartService.addToCart(skuId, num, cartKey, accessToken);
        return new CommonResult().success(cartResponse);
    }

    /**
     * 更新购物车的购物项
     * @param skuId
     * @param num
     * @param cartKey
     * @param accessToken
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/update")
    public CommonResult updateCartItem(@RequestParam("skuId") Long skuId,
                                  @RequestParam(value = "num",defaultValue = "1")Integer num,
                                  @RequestParam(value = "cartKey",required = false)String cartKey,
                                  @RequestParam(value = "accessToken",required = false)String accessToken) throws ExecutionException, InterruptedException {
        CartResponse cartResponse = cartService.updateCartItem(skuId, num, cartKey, accessToken);
        return new CommonResult().success(cartResponse);
    }

    /**
     * 查看购物车
     * @param cartKey
     * @param accessToken
     * @return
     */
    @GetMapping("/list")
    public CommonResult cartList(@RequestParam(value = "cartKey",required = false)String cartKey,
                                 @RequestParam(value = "accessToken",required = false)String accessToken) {
        CartResponse cartResponse = cartService.listCart(cartKey, accessToken);
        return new CommonResult().success(cartResponse);
    }

    /**
     * 删除购物车中的购物项
     * @param skuId
     * @param cartKey
     * @param accessToken
     * @return
     */
    @GetMapping("/del")
    public CommonResult cartDel(@RequestParam("skuId")Long skuId,
                                @RequestParam(value = "cartKey",required = false)String cartKey,
                                @RequestParam(value = "accessToken",required = false)String accessToken) {
        CartResponse cartResponse = cartService.delCartItem(skuId, cartKey, accessToken);
        return new CommonResult().success(cartResponse);
    }
}
