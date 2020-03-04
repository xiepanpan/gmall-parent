package com.xiepanpan.gmall.ums.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiepanpan.gmall.ums.entity.Member;
import com.xiepanpan.gmall.ums.mapper.MemberMapper;
import com.xiepanpan.gmall.ums.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
@Service
@Component
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public Member login(String username, String password) {
        String digest = DigestUtils.md5DigestAsHex(password.getBytes());
        Member member = memberMapper.selectOne(new QueryWrapper<Member>().eq("username", username).eq("password", digest));
        return member;
    }
}
