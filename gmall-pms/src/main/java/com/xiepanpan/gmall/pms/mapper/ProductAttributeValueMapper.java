package com.xiepanpan.gmall.pms.mapper;

import com.xiepanpan.gmall.pms.entity.ProductAttribute;
import com.xiepanpan.gmall.pms.entity.ProductAttributeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiepanpan.gmall.to.es.EsProductAttributeValue;

import java.util.List;

/**
 * <p>
 * 存储产品参数信息的表 Mapper 接口
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
public interface ProductAttributeValueMapper extends BaseMapper<ProductAttributeValue> {

    /**
     *  根据商品id获取该商品分类下的所有属性
     * @param id
     * @return
     */
    List<ProductAttribute> selectProductSaleAttrName(Long id);

    List<EsProductAttributeValue> selectProductBaseAttrAndValue(Long id);
}
