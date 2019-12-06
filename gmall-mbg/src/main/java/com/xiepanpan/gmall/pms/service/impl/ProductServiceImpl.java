package com.xiepanpan.gmall.pms.service.impl;

import com.xiepanpan.gmall.pms.entity.Product;
import com.xiepanpan.gmall.pms.mapper.ProductMapper;
import com.xiepanpan.gmall.pms.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
