package com.xiepanpan.gmall.cart;

import com.xiepanpan.gmall.cart.vo.Cart;
import com.xiepanpan.gmall.cart.vo.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootTest
@Slf4j
public class GmallCartApplicationTests {

    @Test
    public void contextLoads() {
        CartItem cartItem = new CartItem();
        cartItem.setCount(2);
        cartItem.setPrice(new BigDecimal("10.98"));

        CartItem cartItem2 = new CartItem();
        cartItem2.setCount(1);
        cartItem2.setPrice(new BigDecimal("11.3"));
        log.info(String.valueOf(cartItem.getPrice()));

        Cart cart = new Cart();
        cart.setCartItems(Arrays.asList(cartItem,cartItem2));

        log.info(String.valueOf(cart.getCount()));
        log.info(String.valueOf(cart.getTotalPrice()));
    }

}
