package com.xiepanpan.gmall.oms.service.impl;

import com.xiepanpan.gmall.oms.entity.Order;
import com.xiepanpan.gmall.oms.mapper.OrderMapper;
import com.xiepanpan.gmall.oms.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
