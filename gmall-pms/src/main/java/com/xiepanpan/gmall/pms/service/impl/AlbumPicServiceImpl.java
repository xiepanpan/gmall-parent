package com.xiepanpan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiepanpan.gmall.pms.entity.AlbumPic;
import com.xiepanpan.gmall.pms.mapper.AlbumPicMapper;
import com.xiepanpan.gmall.pms.service.AlbumPicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 画册图片表 服务实现类
 * </p>
 *
 * @author xiepanpan
 * @since 2019-12-06
 */
@Service
@Component
public class AlbumPicServiceImpl extends ServiceImpl<AlbumPicMapper, AlbumPic> implements AlbumPicService {

}
