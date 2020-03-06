package com.xiepanpan.gmall.cart.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: xiepanpan
 * @Date: 2020/3/4
 * @Description: 购物车
 */
@Setter
public class Cart implements Serializable {

    /**
     * 购物项
     */
    @Getter
    List<CartItem> cartItems;
    /**
     * 商品总数
     */
    private Integer count;
    /**
     * 已选商品总价格
     */
    private BigDecimal totalPrice;

    /**
     * 计算商品总数
     * @return
     */
    public Integer getCount() {
        if (cartItems!=null) {
            final AtomicInteger integer = new AtomicInteger(0);
            cartItems.forEach((cartItem)->{
                integer.getAndAdd(cartItem.getCount());
            });
            return integer.get();
        }else {
            return 0;
        }
    }

    /**
     * 计算总价格
     * @return
     */
    public BigDecimal getTotalPrice() {
        if (cartItems!=null) {
            AtomicReference<BigDecimal> allTotal = new AtomicReference<>(new BigDecimal("0"));
            cartItems.forEach(cartItem -> {
                BigDecimal add = allTotal.get().add(cartItem.getTotalPrice());
                allTotal.set(add);
            });
            return allTotal.get();
        }else {
            return new BigDecimal("0");
        }
    }
}
