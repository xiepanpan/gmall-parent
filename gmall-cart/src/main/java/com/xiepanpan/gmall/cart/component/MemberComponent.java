package com.xiepanpan.gmall.cart.component;

import com.alibaba.fastjson.JSON;
import com.xiepanpan.gmall.cart.vo.UserCartKey;
import com.xiepanpan.gmall.constant.CartConstant;
import com.xiepanpan.gmall.constant.SysCacheConstant;
import com.xiepanpan.gmall.ums.entity.Member;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author: xiepanpan
 * @Date: 2020/3/4
 * @Description: 查询用户信息
 */
@Component
public class MemberComponent {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 根据accessToken从缓存中获取用户信息
     * @param accessToken
     * @return
     */
    public Member getMemberByAccessToken(String accessToken) {
        String userJson = redisTemplate.opsForValue().get(SysCacheConstant.LOGIN_MEMBER+accessToken);
        return JSON.parseObject(userJson,Member.class);
    }

    /**
     * 构建用户所用缓存的key
     * @param accessToken
     * @param cartKey
     * @return
     */
    public UserCartKey getCartKey(String accessToken, String cartKey) {
        UserCartKey userCartKey = new UserCartKey();
        Member member = null;
        if (!StringUtils.isEmpty(accessToken)) {
            member = getMemberByAccessToken(accessToken);
        }
        if (member!=null) {
            //用户已经登录
            userCartKey.setLogin(true);
            userCartKey.setUserId(member.getId());
            userCartKey.setFinalCartKey(CartConstant.USER_CART_KEY_PREFIX+member.getId());
            return userCartKey;
        }else if(!StringUtils.isEmpty(cartKey)) {
            //用户未登录 用临时cartKey
            userCartKey.setLogin(false);
            userCartKey.setFinalCartKey(CartConstant.TEMP_CART_KEY_PREFIX+cartKey);
            return userCartKey;
        }else {
            //用户没有登录 也没有临时购物车
            String replace = UUID.randomUUID().toString().replace("-", "");
            userCartKey.setLogin(false);
            userCartKey.setFinalCartKey(CartConstant.TEMP_CART_KEY_PREFIX+replace);
            userCartKey.setTempCartKey(replace);
            return userCartKey;
        }
    }
}
