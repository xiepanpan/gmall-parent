package com.xiepanpan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiepanpan.gmall.pms.entity.Product;
import com.xiepanpan.gmall.pms.mapper.ProductMapper;
import com.xiepanpan.gmall.pms.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiepanpan.gmall.vo.PageInfoVo;
import com.xiepanpan.gmall.vo.product.PmsProductQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
@Component
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam) {
        IPage<Product> page = productMapper.selectPage(new Page<Product>(productQueryParam.getPageNum(), productQueryParam.getPageSize()), null);
        PageInfoVo pageInfoVo = new PageInfoVo(page.getTotal(),page.getPages(),productQueryParam.getPageSize(),page.getRecords(),page.getCurrent());
        return pageInfoVo;
    }
}
