package com.xiepanpan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiepanpan.gmall.pms.entity.Brand;
import com.xiepanpan.gmall.pms.mapper.BrandMapper;
import com.xiepanpan.gmall.pms.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
@Service
@Component
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}
