package com.xiepanpan.gmall.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiepanpan.gmall.ums.entity.Admin;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author Lfy
 * @since 2019-05-08
 */
public interface AdminService extends IService<Admin> {

    Admin login(String username, String password);

    Admin getUserInfo(String userName);
}
