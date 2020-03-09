package com.xiepanpan.gmall.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiepanpan.gmall.cart.component.MemberComponent;
import com.xiepanpan.gmall.cart.service.CartService;
import com.xiepanpan.gmall.cart.vo.Cart;
import com.xiepanpan.gmall.cart.vo.CartItem;
import com.xiepanpan.gmall.cart.vo.CartResponse;
import com.xiepanpan.gmall.cart.vo.UserCartKey;
import com.xiepanpan.gmall.constant.CartConstant;
import com.xiepanpan.gmall.pms.entity.Product;
import com.xiepanpan.gmall.pms.entity.SkuStock;
import com.xiepanpan.gmall.pms.service.ProductService;
import com.xiepanpan.gmall.pms.service.SkuStockService;
import com.xiepanpan.gmall.ums.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: xiepanpan
 * @Date: 2020/3/4
 * @Description:
 */
@Service
@Component
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    MemberComponent memberComponent;

    @Reference
    SkuStockService skuStockService;

    @Reference
    ProductService productService;

    @Autowired
    RedissonClient redissonClient;

    @Override
    public CartResponse addToCart(Long skuId, Integer num, String cartKey, String accessToken) throws ExecutionException, InterruptedException {

        Member member = memberComponent.getMemberByAccessToken(accessToken);
        if (member!=null&& StringUtils.isEmpty(cartKey)) {
            //用户登录了  合并购物车
            log.info("====合并购物车===");
            mergeCart(cartKey,member.getId());
        }
        UserCartKey userCartKey = memberComponent.getCartKey(accessToken, cartKey);
        String finalCartKey = userCartKey.getFinalCartKey();
        CartItem cartItem = addItemToCart(skuId, num, finalCartKey);
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartItem(cartItem);


        return cartResponse;
    }

    @Override
    public CartResponse updateCartItem(Long skuId, Integer num, String cartKey, String accessToken) {
        UserCartKey userCartKey = memberComponent.getCartKey(accessToken, cartKey);
        String finalCartKey = userCartKey.getFinalCartKey();
        RMap<Object, Object> map = redissonClient.getMap(finalCartKey);

        String json = (String) map.get(skuId.toString());
        CartItem cartItem = JSON.parseObject(json, CartItem.class);
        cartItem.setCount(num);

        String jsonString = JSON.toJSONString(cartItem);
        map.put(skuId.toString(),jsonString);
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartItem(cartItem);
        return cartResponse;
    }

    @Override
    public CartResponse listCart(String cartKey, String accessToken) {
        UserCartKey userCartKey = memberComponent.getCartKey(accessToken, cartKey);
        if (userCartKey.isLogin()) {
            //用户登录了 合并购物车
        }
        String finalCartKey = userCartKey.getFinalCartKey();
        RMap<String, String> map = redissonClient.getMap(finalCartKey);
        Cart cart = new Cart();
        CartResponse cartResponse = new CartResponse();
        List<CartItem> cartItems = new ArrayList<>();
        if (map!=null&& !map.isEmpty()) {
            map.entrySet().forEach(item->{
                //缓存中不仅存了购物项 还存了选中的集合 在遍历时排除
                if (!item.getKey().equalsIgnoreCase(CartConstant.CART_CHECKED_KEY)) {
                    String value = item.getValue();
                    CartItem cartItem = JSON.parseObject(value, CartItem.class);
                    cartItems.add(cartItem);
                }

            });
            cart.setCartItems(cartItems);
        }else {
            //用户还没有购物车 新建一个购物车
            cartResponse.setCartKey(userCartKey.getTempCartKey());
        }
        cartResponse.setCart(cart);
        return cartResponse;
    }

    @Override
    public CartResponse delCartItem(Long skuId, String cartKey, String accessToken) {
        UserCartKey userCartKey = memberComponent.getCartKey(accessToken, cartKey);
        String finalCartKey = userCartKey.getFinalCartKey();
        //删掉sku 把对应选中集合的sku也删掉
        checkItem(Arrays.asList(skuId),false,finalCartKey);

        //移除缓存中对应的key
        RMap<Object, Object> map = redissonClient.getMap(finalCartKey);
        map.remove(skuId.toString());

        CartResponse cartResponse = listCart(cartKey, accessToken);
        return cartResponse;
    }

    @Override
    public CartResponse clearCart(String cartKey,String accessToken) {
        UserCartKey userCartKey = memberComponent.getCartKey(accessToken, cartKey);
        String finalCartKey = userCartKey.getFinalCartKey();
        RMap<Object, Object> map = redissonClient.getMap(finalCartKey);
        map.clear();
        CartResponse cartResponse = new CartResponse();
        return cartResponse;
    }

    @Override
    public CartResponse checkCartItems(String skuIds, Integer ops, String cartKey, String accessToken) {
        List<Long> skuIdsList = new ArrayList<>();
        UserCartKey userCartKey = memberComponent.getCartKey(accessToken, cartKey);
        String finalCartKey = userCartKey.getFinalCartKey();
        RMap<String, String> map = redissonClient.getMap(finalCartKey);
        boolean checked = ops==1?true:false;
        if (!StringUtils.isEmpty(skuIds)) {
            String[] ids = skuIds.split(",");
            for (String id: ids) {
                long skuId = Long.parseLong(id);
                skuIdsList.add(skuId);
                if (map!=null&& !map.isEmpty()) {
                    String jsonValue = map.get(id);
                    CartItem cartItem = JSON.parseObject(jsonValue, CartItem.class);
                    cartItem.setCheck(checked);
                    map.put(id,JSON.toJSONString(cartItem));
                }
            }
        }

        //为了快速找到那个被选中的 我们单独维护了数组 数组在map中用的key 值是选中的skuId set集合
        checkItem(skuIdsList,checked,finalCartKey);

        //返回整个购物车
        CartResponse cartResponse = listCart(cartKey, accessToken);
        return cartResponse;
    }

    /**
     * 把临时购物车的数据合并到用户购物车中
     * @param cartKey 临时购物车的key
     * @param id
     */
    private void mergeCart(String cartKey, Long id) {
        String oldCartKey = CartConstant.TEMP_CART_KEY_PREFIX + cartKey;
        String userCartKey = CartConstant.USER_CART_KEY_PREFIX + id.toString();
        RMap<String, String> map = redissonClient.getMap(oldCartKey);

        if (map!=null&&!map.isEmpty()) {
            map.entrySet().forEach(item->{
                //skuId
                String key = item.getKey();
                if (!key.equalsIgnoreCase(CartConstant.CART_CHECKED_KEY)) {
                    String value = item.getValue();
                    CartItem cartItem = JSON.parseObject(value, CartItem.class);
                    try {
                        addItemToCart(Long.valueOf(key),cartItem.getCount(),userCartKey);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
            map.clear();
        }
    }

    private CartItem addItemToCart(Long skuId, Integer num, String finalCartKey) throws ExecutionException, InterruptedException {
        /**
         * 1、只接受上一步的结果
         * thenAccept(r){
         *     r:上一步的结果
         *
         * }
         *
         * 2、thenApply(r){
         *     r：把上一步的结果拿来进行修改再返回，
         * }
         *
         * 3、thenAccpet(){} 上一步结果1s+本次处理2s=3s
         *
         * 4、thenAccpetAsync(){} 上一步1s+异步2s = 最多等2s
         */
        //
        CartItem newCartItem = new CartItem();
        CompletableFuture<Void> skuFuture = CompletableFuture.supplyAsync(() -> {
            SkuStock skuStock = skuStockService.getById(skuId);
            return skuStock;
        }).thenAcceptAsync(stock -> {
            //拿到上一步的商品id
            Long productId = stock.getProductId();
            Product product = productService.getById(productId);
            //
            BeanUtils.copyProperties(stock, newCartItem);
            newCartItem.setSkuId(stock.getId());
            newCartItem.setName(product.getName());
            newCartItem.setCount(num);
        });
        RMap<String, String> map = redissonClient.getMap(finalCartKey);
        String itemJson = map.get(skuId.toString());
        skuFuture.get();
        //检查购物车是否已存在这个购物项
        if (!StringUtils.isEmpty(itemJson)) {
            //购物车存在该sku 数量添加
            CartItem oldItem = JSON.parseObject(itemJson, CartItem.class);
            Integer count = oldItem.getCount();
            newCartItem.setCount(count+newCartItem.getCount());
            String string = JSON.toJSONString(newCartItem);
            map.put(skuId.toString(),string);
        }else {
            //新增购物项
            String string = JSON.toJSONString(newCartItem);
            map.put(skuId.toString(),string);
        }
        //
        checkItem(Arrays.asList(skuId),true,finalCartKey);
        return newCartItem;
    }

    /**
     * 选中或是否移除 购物项
     * @param skuId
     * @param checked
     * @param finalCartKey
     */
    private void checkItem(List<Long> skuId, boolean checked, String finalCartKey) {
        RMap<String, String> cart = redissonClient.getMap(finalCartKey);
        String checkedJson = cart.get(CartConstant.CART_CHECKED_KEY);
        Set<Long> longSet = JSON.parseObject(checkedJson, new TypeReference<Set<Long>>() {
        });
        if (longSet==null||longSet.isEmpty()) {
            longSet = new LinkedHashSet<>();
        }
        if (checked) {
            longSet.addAll(skuId);
            log.info("目前被选中的商品：{}",longSet);
        }else {
            longSet.removeAll(skuId);
            log.info("目前被选中的商品：{}",longSet);
        }
        cart.put(CartConstant.CART_CHECKED_KEY,JSON.toJSONString(longSet));
    }
}
