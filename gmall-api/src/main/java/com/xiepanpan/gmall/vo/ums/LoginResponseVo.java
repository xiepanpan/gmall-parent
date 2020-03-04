package com.xiepanpan.gmall.vo.ums;

import lombok.Data;

/**
 * @author: xiepanpan
 * @Date: 2020/3/4
 * @Description:
 */
@Data
public class LoginResponseVo {

    private Long memberLevelId;

    private String username;

    private String nickname;

    private String phone;

    /**
     * 访问令牌
     */
    private String accessToken;

}
