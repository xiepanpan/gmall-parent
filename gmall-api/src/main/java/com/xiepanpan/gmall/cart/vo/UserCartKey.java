package com.xiepanpan.gmall.cart.vo;

import lombok.Data;

/**
 * @author: xiepanpan
 * @Date: 2020/3/5
 * @Description: 用户购物车缓存键
 */
@Data
public class UserCartKey {
    /**
     * 用户是否登录
     */
    private boolean login;
    /**
     * 用户登录id
     */
    private Long userId;
    /**
     * 用户没有登录 临时购物车key
     */
    private String tempCartKey;
    /**
     * 用户最终所用购物车的key
     */
    private String finalCartKey;
}
