package com.xiepanpan.gmall.cart.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xiepanpan
 * @Date: 2020/3/4
 * @Description: 购物单项
 */
@Setter
public class CartItem implements Serializable {

    @Getter
    private Long skuId;

    @Getter
    private String name;

    @Getter
    private String skuCode;

    @Getter
    private Integer stock;
    @Getter
    private String sp1;
    @Getter
    private String sp2;
    @Getter
    private String sp3;
    @Getter
    private String pic;
    @Getter
    private BigDecimal price;
    @Getter
    private BigDecimal promotionPrice;

    //购物项的选中状态
    @Getter
    private boolean check = true;
    //有多少个
    @Getter
    private Integer count;
    //当前购物项总价
    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        BigDecimal bigDecimal = price.multiply(new BigDecimal(count.toString()));
        return bigDecimal;
    }
}
