package com.xiepanpan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiepanpan.gmall.pms.entity.SkuStock;
import com.xiepanpan.gmall.pms.mapper.SkuStockMapper;
import com.xiepanpan.gmall.pms.service.SkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
@Service
@Component
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements SkuStockService {

}
