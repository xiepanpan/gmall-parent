package com.xiepanpan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiepanpan.gmall.pms.entity.ProductCategory;
import com.xiepanpan.gmall.pms.mapper.ProductCategoryMapper;
import com.xiepanpan.gmall.pms.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiepanpan.gmall.vo.product.PmsProductCategoryWithChildrenItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
@Component
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper productCategoryMapper;
    @Override
    public List<PmsProductCategoryWithChildrenItem> listCategoryWithChildren(Integer i) {
        List<PmsProductCategoryWithChildrenItem> items = productCategoryMapper.listCategoryWithChildren(i);
        return items;
    }
}
