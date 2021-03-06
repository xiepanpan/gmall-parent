package com.xiepanpan.gmall.pms.service;

import com.xiepanpan.gmall.pms.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiepanpan.gmall.to.es.EsProduct;
import com.xiepanpan.gmall.vo.PageInfoVo;
import com.xiepanpan.gmall.vo.product.PmsProductParam;
import com.xiepanpan.gmall.vo.product.PmsProductQueryParam;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    Product getProductInfo(Long id);

    /**
     * 根据复杂查询条件返回分页数据
     * @param productQueryParam
     * @return
     */
    PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam);

    /**
     * 保存商品数据
     * @param productParam
     */
    void saveProduct(PmsProductParam productParam);

    /**
     * 批量上下架
     * @param ids
     * @param publishStatus
     */
    void updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 从es中根据id获取商品信息
     * @param id
     * @return
     */
    EsProduct productAllInfo(Long id);

    EsProduct productSkuInfo(Long id);
}
