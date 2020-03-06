package com.xiepanpan.gmall.constant;

/**
 * @author: xiepanpan
 * @Date: 2020/3/5
 * @Description: 购物车常量
 */
public class CartConstant {

    /**
     * 临时购物车的key 后面加cartKey
     */
    public static final String TEMP_CART_KEY_PREFIX="cart:temp:";
    /**
     *  用户购物车的key 后面加用户id
     */
    public static final String USER_CART_KEY_PREFIX="cart:user:";
    /**
     * 购物车在redis中存储被选中商品项的key
     */
    public static final String CART_CHECKED_KEY="checked";
}
