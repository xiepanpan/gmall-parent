package com.xiepanpan.gmall.pms.service;

import com.xiepanpan.gmall.pms.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiepanpan.gmall.vo.PageInfoVo;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
public interface BrandService extends IService<Brand> {

    PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize);
}
