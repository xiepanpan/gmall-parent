package com.xiepanpan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiepanpan.gmall.constant.EsConstant;
import com.xiepanpan.gmall.pms.entity.*;
import com.xiepanpan.gmall.pms.mapper.*;
import com.xiepanpan.gmall.pms.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiepanpan.gmall.vo.PageInfoVo;
import com.xiepanpan.gmall.vo.product.PmsProductParam;
import com.xiepanpan.gmall.vo.product.PmsProductQueryParam;
import io.searchbox.client.JestClient;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.engine.Engine;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;

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
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    ProductFullReductionMapper productFullReductionMapper;

    @Autowired
    ProductLadderMapper productLadderMapper;

    @Autowired
    SkuStockMapper skuStockMapper;

    @Autowired
    JestClient jestClient;

    //当前线程共享同样的数据
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Override
    public PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (productQueryParam.getBrandId()!=null) {
            queryWrapper.eq("brand_id",productQueryParam.getBrandId());
        }
        if (!StringUtils.isEmpty(productQueryParam.getKeyword())) {
            queryWrapper.like("name",productQueryParam.getKeyword());
        }
        if (productQueryParam.getProductCategoryId()!=null) {
            queryWrapper.eq("product_category_id",productQueryParam.getProductCategoryId());
        }
        if (!StringUtils.isEmpty(productQueryParam.getProductSn())) {
            queryWrapper.like("product_sn",productQueryParam.getProductSn());
        }
        if (productQueryParam.getPublishStatus()!=null) {
            queryWrapper.eq("publish_status",productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus()!=null) {
            queryWrapper.eq("verify_status",productQueryParam.getVerifyStatus());
        }
        IPage<Product> page = productMapper.selectPage(new Page<Product>(productQueryParam.getPageNum(), productQueryParam.getPageSize()), queryWrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(page.getTotal(),page.getPages(),productQueryParam.getPageSize(),page.getRecords(),page.getCurrent());
        return pageInfoVo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveProduct(PmsProductParam productParam) {
        ProductServiceImpl proxy = (ProductServiceImpl) AopContext.currentProxy();
        //1）、pms_product：保存商品基本信息
        proxy.saveBaseInfo(productParam);

        //5）、pms_sku_stock：sku_库存表
        proxy.saveSkuStock(productParam);

        /**
         * 以下都可以try-catch互不影响
         */
        //2）、pms_product_attribute_value：保存这个商品对应的所有属性的值
        proxy.saveProductAttributeValue(productParam);

        //3）、pms_product_full_reduction：保存商品的满减信息
        proxy.saveFullReduction(productParam);

        //4）、pms_product_ladder：满减表
        proxy.saveProductLadder(productParam);

        //以上的写法只是相当于一个saveProduct事务。

    }

    @Override
    public void updatePublishStatus(List<Long> ids, Integer publishStatus) {
        if (publishStatus==0) {
            ids.forEach((id)->{
                //下架
                //改数据库状态
                setProductPublishStatus(publishStatus,id);
                //删es
                deleteProductFromEs(id);
            });
        }else {
            //上架
            ids.forEach((id)->{
                setProductPublishStatus(publishStatus,id);
                //加es
                saveProductToEs(id);
            });
        }
    }

    private void saveProductToEs(Long id) {
    }

    /**
     * 从es中删除商品
     * @param id
     */
    private void deleteProductFromEs(Long id) {
        Delete delete = new Delete.Builder(id.toString()).index(EsConstant.PRODUCT_ES_INDEX)
                .type(EsConstant.PRODUCT_INFO_ES_TYPE).build();
        DocumentResult execute = jestClient.execute(delete);
        try {
            if (execute.isSucceeded()) {
                log.info("商品：{} ==> ES下架完成",id);
            }else  {
                log.error("商品：{} ==》ES下架失败",id);
            }
        } catch (Exception e) {
            log.error("商品：{} ==》ES下架失败",id);
        }
    }

    private void setProductPublishStatus(Integer publishStatus, Long id) {
        Product product = new Product();
        product.setId(id);
        product.setPublishStatus(publishStatus);
        productMapper.updateById(product);
    }

    /**
     * 保存商品基础信息
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveBaseInfo(PmsProductParam productParam){
        //1）、pms_product：保存商品基本信息
        Product product = new Product();
        BeanUtils.copyProperties(productParam,product);
        productMapper.insert(product);
        //mybatis-plus能自动获取到刚才这个数据的自增id
        log.debug("刚才的商品的id：{}",product.getId());
        threadLocal.set(product.getId());

        log.debug("当前线程....{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());

    }
    //2）、pms_product_attribute_value：保存这个商品对应的所有属性的值
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveProductAttributeValue(PmsProductParam productParam){
        List<ProductAttributeValue> valueList = productParam.getProductAttributeValueList();
        valueList.forEach((item)->{
            item.setProductId(threadLocal.get());
            productAttributeValueMapper.insert(item);

        });

        log.debug("当前线程....{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveSkuStock(PmsProductParam productParam) {
        List<SkuStock> skuStockList = productParam.getSkuStockList();
        for (int i = 1; i<=skuStockList.size(); i++) {
            SkuStock skuStock = skuStockList.get(i-1);
            if(org.springframework.util.StringUtils.isEmpty(skuStock.getSkuCode())){
                //skuCode必须有  1_1  1_2 1_3 1_4
                //生成规则  商品id_sku自增id
                skuStock.setSkuCode(threadLocal.get()+"_"+i);
            }
            skuStock.setProductId(threadLocal.get());
            skuStockMapper.insert(skuStock);
        }

        log.debug("当前线程....{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());
    }

    /**
     * 默认出任何都回滚？
     *
     * @param productParam
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = FileNotFoundException.class,
            noRollbackFor = {ArithmeticException.class,NullPointerException.class})
    public void saveProductLadder(PmsProductParam productParam) {
        List<ProductLadder> productLadderList = productParam.getProductLadderList();
        productLadderList.forEach((productLadder)->{
            productLadder.setProductId(threadLocal.get());
            productLadderMapper.insert(productLadder);

        });

        log.debug("当前线程....{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());
//        int i = 10/0;
//        File xxxx = new File("xxxx");
//        new FileInputStream(xxxx);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = {Exception.class})
    public void saveFullReduction(PmsProductParam productParam) {
        List<ProductFullReduction> fullReductionList = productParam.getProductFullReductionList();
        fullReductionList.forEach((reduction)->{
            reduction.setProductId(threadLocal.get());
            productFullReductionMapper.insert(reduction);
        });

        log.debug("当前线程....{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());
    }
}
