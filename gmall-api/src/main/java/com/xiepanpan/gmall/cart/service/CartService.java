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

    /**
     * 更新购物车中的购物项数量
     * @param skuId
     * @param num
     * @param cartKey
     * @param accessToken
     * @return
     */
    CartResponse updateCartItem(Long skuId, Integer num, String cartKey, String accessToken);

    /**
     * 查看购物车
     * @param cartKey
     * @param accessToken
     * @return
     */
    CartResponse listCart(String cartKey, String accessToken);

    CartResponse delCartItem(Long skuId, String cartKey, String accessToken);

    CartResponse clearCart(String cartKey, String accessToken);
}
