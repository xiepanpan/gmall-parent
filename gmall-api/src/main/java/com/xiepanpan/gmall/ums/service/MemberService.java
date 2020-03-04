package com.xiepanpan.gmall.ums.service;

import com.xiepanpan.gmall.ums.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
public interface MemberService extends IService<Member> {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    Member login(String username,String password);

}
