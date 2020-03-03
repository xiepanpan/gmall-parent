package com.xiepanpan.gmall.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: xiepanpan
 * @Date: 2020/3/2
 * @Description: 登录控制层
 */
@Controller
public class LoginController {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 登录
     * @param redirectUrl
     * @param ssoUser
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "redirect_url")String redirectUrl,
                        @CookieValue(value = "sso_user",required = false)String ssoUser,
                        HttpServletResponse response, Model model) throws IOException {
        //判断是否登录过
        if (!StringUtils.isEmpty(ssoUser)) {
            //登录过 回到之前的地方 并且把当前ssoserver获取到的cookie以url参数方式传递到其他域名 实现cookie同步
            String url = redirectUrl + "?" + "sso_user=" + ssoUser;
            response.sendRedirect(url);
            return null;
        }else {
            //转到login.html页面
            model.addAttribute("redirect_url",redirectUrl);
            return "login";
        }
    }

    /**
     * 处理登录操作
     * @param username
     * @param password
     * @param response
     * @param redirectUrl
     * @throws IOException
     */
    @PostMapping("/doLogin")
    public void doLogin(String username,String password,HttpServletResponse response,
                        @RequestParam(value = "redirect_url")String redirectUrl) throws IOException {
        //1. 模拟用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("email",username+"@qq.com");
        //2. 标识用户登录
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, JSON.toJSONString(map));

        //3. 登录成功做两件事
        // （1） 浏览器保存当前token作为cookie sso_user=token
        Cookie cookie = new Cookie("sso_user",token);
        response.addCookie(cookie);
        // (2) 重定向到之前的路径
        response.sendRedirect(redirectUrl+"?"+"sso_user="+token);
    }
}
