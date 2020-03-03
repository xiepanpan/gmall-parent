package com.xiepanpan.gmall.controller;

import com.xiepanpan.gmall.config.SsoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xiepanpan
 * @Date: 2020/3/2
 * @Description:
 */
@Controller
public class HelloController {

    @Autowired
    SsoConfig ssoConfig;

    @GetMapping("/")
    public String index(Model model, @CookieValue(value = "sso_user",required = false)String ssoUserCookie,
                        @RequestParam(value = "sso_user",required = false) String ssoUserParam,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        StringBuffer requestURL = request.getRequestURL();
        if (!StringUtils.isEmpty(ssoUserParam)) {
            //没有去登录页面登录 跳转回来 说明已经远程登录了
            Cookie sso_user = new Cookie("sso_user",ssoUserParam);
            response.addCookie(sso_user);
        }
        //判断是否登录
        if (StringUtils.isEmpty(ssoUserCookie)) {
            //没登录 重定向到登录服务器
            String url = ssoConfig.getUrl() + ssoConfig.getLoginPath() + "?redirect_url=" + requestURL.toString();
            response.sendRedirect(url);
            return null;
        }
        //登录了，redis.get(ssoUserCookie)获取到用户信息，
        model.addAttribute("loginUser","XP");
        return "index";



    }

}
