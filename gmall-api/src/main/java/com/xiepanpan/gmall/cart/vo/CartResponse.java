package com.xiepanpan.gmall.cart.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiepanpan
 * @Date: 2020/3/4
 * @Description:
 */
@Data
public class CartResponse implements Serializable {

    /**
     * 购物车
     */
    private Cart cart;
    /**
     * 购物项
     */
    private CartItem cartItem;
    private String cartKey;
}
