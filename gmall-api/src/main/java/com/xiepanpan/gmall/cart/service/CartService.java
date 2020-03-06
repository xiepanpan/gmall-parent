package com.xiepanpan.gmall.cart.service;

import com.xiepanpan.gmall.cart.vo.CartResponse;

import java.util.concurrent.ExecutionException;

/**
 * @author: xiepanpan
 * @Date: 2020/3/4
 * @Description: 购物侧服务
 */
public interface CartService {

    /**
     * 添加到购物车
     * @param skuId
     * @param num
     * @param cartKey
     * @param accessToken
     * @return
     */
    CartResponse addToCart(Long skuId,Integer num,String cartKey,String accessToken) throws ExecutionException, InterruptedException;
}
