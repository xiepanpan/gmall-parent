package com.xiepanpan.gmall.oms.service.impl;

import com.xiepanpan.gmall.oms.entity.OrderItem;
import com.xiepanpan.gmall.oms.mapper.OrderItemMapper;
import com.xiepanpan.gmall.oms.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
