package com.xiepanpan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiepanpan.gmall.pms.entity.Brand;
import com.xiepanpan.gmall.pms.mapper.BrandMapper;
import com.xiepanpan.gmall.pms.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiepanpan.gmall.vo.PageInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    BrandMapper brandMapper;

    @Override
    public PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<Brand> name = null;
        //自动拼接%
        if (!StringUtils.isEmpty(keyword)) {
            name = new QueryWrapper<Brand>().like("name",keyword);
        }
        IPage<Brand> brandIPage = brandMapper.selectPage(new Page<>(pageNum.longValue(), pageSize.longValue()), name);
        PageInfoVo pageInfoVo = new PageInfoVo(brandIPage.getTotal(),brandIPage.getPages(),pageSize.longValue(),brandIPage.getRecords(),
                brandIPage.getCurrent());
        return pageInfoVo;
    }
}
