package com.xiepanpan.gmall.pms.service;

import com.xiepanpan.gmall.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiepanpan.gmall.vo.product.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    List<PmsProductCategoryWithChildrenItem> listCategoryWithChildren(Integer i);
}
