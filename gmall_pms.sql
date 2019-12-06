/*Table structure for table `pms_album` */

DROP TABLE IF EXISTS `pms_album`;

CREATE TABLE `pms_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `cover_pic` varchar(1000) DEFAULT NULL,
  `pic_count` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='相册表';

/*Data for the table `pms_album` */

/*Table structure for table `pms_album_pic` */

DROP TABLE IF EXISTS `pms_album_pic`;

CREATE TABLE `pms_album_pic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `album_id` bigint(20) DEFAULT NULL,
  `pic` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='画册图片表';

/*Data for the table `pms_album_pic` */

/*Table structure for table `pms_brand` */

DROP TABLE IF EXISTS `pms_brand`;

CREATE TABLE `pms_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `first_letter` varchar(8) DEFAULT NULL COMMENT '首字母',
  `sort` int(11) DEFAULT NULL,
  `factory_status` int(1) DEFAULT NULL COMMENT '是否为品牌制造商：0->不是；1->是',
  `show_status` int(1) DEFAULT NULL,
  `product_count` int(11) DEFAULT NULL COMMENT '产品数量',
  `product_comment_count` int(11) DEFAULT NULL COMMENT '产品评论数量',
  `logo` varchar(255) DEFAULT NULL COMMENT '品牌logo',
  `big_pic` varchar(255) DEFAULT NULL COMMENT '专区大图',
  `brand_story` text COMMENT '品牌故事',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='品牌表';

/*Data for the table `pms_brand` */

insert  into `pms_brand`(`id`,`name`,`first_letter`,`sort`,`factory_status`,`show_status`,`product_count`,`product_comment_count`,`logo`,`big_pic`,`brand_story`) values (1,'万和','W',0,1,1,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(5).jpg','','Victoria\'s Secret的故事'),(2,'三星','S',100,1,1,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (1).jpg',NULL,'三星的故事'),(4,'格力','G',30,1,1,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/dc794e7e74121272bbe3ce9bc41ec8c3_222_222.jpg',NULL,'Victoria\'s Secret的故事'),(5,'方太','F',20,1,1,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (4).jpg',NULL,'Victoria\'s Secret的故事'),(6,'小米','X',0,1,1,100,200,NULL,NULL,NULL),(21,'OPPO','O',0,1,1,88,500,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(6).jpg','','string'),(49,'七匹狼','S',200,1,1,77,400,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/18d8bc3eb13533fab466d702a0d3fd1f40345bcd.jpg',NULL,'BOOB的故事'),(50,'海澜之家','H',200,1,1,66,300,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/99d3279f1029d32b929343b09d3c72de_222_222.jpg','','海澜之家的故事'),(51,'苹果','A',200,1,1,55,200,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg',NULL,'苹果的故事'),(52,'ggg','g',0,0,0,NULL,NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190315/7.jpg','','');

/*Table structure for table `pms_comment` */

DROP TABLE IF EXISTS `pms_comment`;

CREATE TABLE `pms_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `member_nick_name` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `star` int(3) DEFAULT NULL COMMENT '评价星数：0->5',
  `member_ip` varchar(64) DEFAULT NULL COMMENT '评价的ip',
  `create_time` datetime DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL,
  `product_attribute` varchar(255) DEFAULT NULL COMMENT '购买时的商品属性',
  `collect_couont` int(11) DEFAULT NULL,
  `read_count` int(11) DEFAULT NULL,
  `content` text,
  `pics` varchar(1000) DEFAULT NULL COMMENT '上传图片地址，以逗号隔开',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '评论用户头像',
  `replay_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价表';

/*Data for the table `pms_comment` */

/*Table structure for table `pms_comment_replay` */

DROP TABLE IF EXISTS `pms_comment_replay`;

CREATE TABLE `pms_comment_replay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) DEFAULT NULL,
  `member_nick_name` varchar(255) DEFAULT NULL,
  `member_icon` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '评论人员类型；0->会员；1->管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品评价回复表';

/*Data for the table `pms_comment_replay` */

/*Table structure for table `pms_feight_template` */

DROP TABLE IF EXISTS `pms_feight_template`;

CREATE TABLE `pms_feight_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `charge_type` int(1) DEFAULT NULL COMMENT '计费类型:0->按重量；1->按件数',
  `first_weight` decimal(10,2) DEFAULT NULL COMMENT '首重kg',
  `first_fee` decimal(10,2) DEFAULT NULL COMMENT '首费（元）',
  `continue_weight` decimal(10,2) DEFAULT NULL,
  `continme_fee` decimal(10,2) DEFAULT NULL,
  `dest` varchar(255) DEFAULT NULL COMMENT '目的地（省、市）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运费模版';

/*Data for the table `pms_feight_template` */

/*Table structure for table `pms_member_price` */

DROP TABLE IF EXISTS `pms_member_price`;

CREATE TABLE `pms_member_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `member_level_id` bigint(20) DEFAULT NULL,
  `member_price` decimal(10,2) DEFAULT NULL COMMENT '会员价格',
  `member_level_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8 COMMENT='商品会员价格表';

/*Data for the table `pms_member_price` */

insert  into `pms_member_price`(`id`,`product_id`,`member_level_id`,`member_price`,`member_level_name`) values (26,7,1,'500.00',NULL),(27,8,1,'500.00',NULL),(28,9,1,'500.00',NULL),(29,10,1,'500.00',NULL),(30,11,1,'500.00',NULL),(31,12,1,'500.00',NULL),(32,13,1,'500.00',NULL),(33,14,1,'500.00',NULL),(37,18,1,'500.00',NULL),(44,7,2,'480.00',NULL),(45,7,3,'450.00',NULL),(52,22,1,NULL,NULL),(53,22,2,NULL,NULL),(54,22,3,NULL,NULL),(58,24,1,NULL,NULL),(59,24,2,NULL,NULL),(60,24,3,NULL,NULL),(112,23,1,'88.00','黄金会员'),(113,23,2,'88.00','白金会员'),(114,23,3,'66.00','钻石会员'),(142,31,1,NULL,'黄金会员'),(143,31,2,NULL,'白金会员'),(144,31,3,NULL,'钻石会员'),(148,32,1,NULL,'黄金会员'),(149,32,2,NULL,'白金会员'),(150,32,3,NULL,'钻石会员'),(154,33,1,NULL,'黄金会员'),(155,33,2,NULL,'白金会员'),(156,33,3,NULL,'钻石会员'),(169,36,1,NULL,'黄金会员'),(170,36,2,NULL,'白金会员'),(171,36,3,NULL,'钻石会员'),(172,35,1,NULL,'黄金会员'),(173,35,2,NULL,'白金会员'),(174,35,3,NULL,'钻石会员'),(175,34,1,NULL,'黄金会员'),(176,34,2,NULL,'白金会员'),(177,34,3,NULL,'钻石会员'),(178,30,1,NULL,'黄金会员'),(179,30,2,NULL,'白金会员'),(180,30,3,NULL,'钻石会员'),(192,27,1,NULL,'黄金会员'),(193,27,2,NULL,'白金会员'),(194,27,3,NULL,'钻石会员'),(195,28,1,NULL,'黄金会员'),(196,28,2,NULL,'白金会员'),(197,28,3,NULL,'钻石会员'),(198,29,1,NULL,'黄金会员'),(199,29,2,NULL,'白金会员'),(200,29,3,NULL,'钻石会员'),(207,37,1,NULL,'黄金会员'),(208,37,2,NULL,'白金会员'),(209,37,3,NULL,'钻石会员'),(210,38,1,NULL,'黄金会员'),(211,38,2,NULL,'白金会员'),(212,38,3,NULL,'钻石会员'),(213,39,1,NULL,'黄金会员'),(214,39,2,NULL,'白金会员'),(215,39,3,NULL,'钻石会员'),(216,40,1,NULL,'黄金会员'),(217,40,2,NULL,'白金会员'),(218,40,3,NULL,'钻石会员'),(219,41,1,NULL,'黄金会员'),(220,41,2,NULL,'白金会员'),(221,41,3,NULL,'钻石会员'),(222,42,1,NULL,'黄金会员'),(223,42,2,NULL,'白金会员'),(224,42,3,NULL,'钻石会员'),(231,26,1,NULL,'黄金会员'),(232,26,2,NULL,'白金会员'),(233,26,3,NULL,'钻石会员'),(234,43,1,NULL,'黄金会员'),(235,43,2,NULL,'白金会员'),(236,43,3,NULL,'钻石会员');

/*Table structure for table `pms_product` */

DROP TABLE IF EXISTS `pms_product`;

CREATE TABLE `pms_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL,
  `product_category_id` bigint(20) DEFAULT NULL,
  `feight_template_id` bigint(20) DEFAULT NULL,
  `product_attribute_category_id` bigint(20) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `product_sn` varchar(64) NOT NULL COMMENT '货号',
  `delete_status` int(1) DEFAULT NULL COMMENT '删除状态：0->未删除；1->已删除',
  `publish_status` int(1) DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
  `new_status` int(1) DEFAULT NULL COMMENT '新品状态:0->不是新品；1->新品',
  `recommand_status` int(1) DEFAULT NULL COMMENT '推荐状态；0->不推荐；1->推荐',
  `verify_status` int(1) DEFAULT NULL COMMENT '审核状态：0->未审核；1->审核通过',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `sale` int(11) DEFAULT NULL COMMENT '销量',
  `price` decimal(10,2) DEFAULT NULL,
  `promotion_price` decimal(10,2) DEFAULT NULL COMMENT '促销价格',
  `gift_growth` int(11) DEFAULT '0' COMMENT '赠送的成长值',
  `gift_point` int(11) DEFAULT '0' COMMENT '赠送的积分',
  `use_point_limit` int(11) DEFAULT NULL COMMENT '限制使用的积分数',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  `description` text COMMENT '商品描述',
  `original_price` decimal(10,2) DEFAULT NULL COMMENT '市场价',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `low_stock` int(11) DEFAULT NULL COMMENT '库存预警值',
  `unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `weight` decimal(10,2) DEFAULT NULL COMMENT '商品重量，默认为克',
  `preview_status` int(1) DEFAULT NULL COMMENT '是否为预告商品：0->不是；1->是',
  `service_ids` varchar(64) DEFAULT NULL COMMENT '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮',
  `keywords` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `album_pics` varchar(255) DEFAULT NULL COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
  `detail_title` varchar(255) DEFAULT NULL,
  `detail_desc` text,
  `detail_html` text COMMENT '产品详情网页内容',
  `detail_mobile_html` text COMMENT '移动端网页详情',
  `promotion_start_time` datetime DEFAULT NULL COMMENT '促销开始时间',
  `promotion_end_time` datetime DEFAULT NULL COMMENT '促销结束时间',
  `promotion_per_limit` int(11) DEFAULT NULL COMMENT '活动限购数量',
  `promotion_type` int(1) DEFAULT NULL COMMENT '促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `product_category_name` varchar(255) DEFAULT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='商品信息';

/*Data for the table `pms_product` */

insert  into `pms_product`(`id`,`brand_id`,`product_category_id`,`feight_template_id`,`product_attribute_category_id`,`name`,`pic`,`product_sn`,`delete_status`,`publish_status`,`new_status`,`recommand_status`,`verify_status`,`sort`,`sale`,`price`,`promotion_price`,`gift_growth`,`gift_point`,`use_point_limit`,`sub_title`,`description`,`original_price`,`stock`,`low_stock`,`unit`,`weight`,`preview_status`,`service_ids`,`keywords`,`note`,`album_pics`,`detail_title`,`detail_desc`,`detail_html`,`detail_mobile_html`,`promotion_start_time`,`promotion_end_time`,`promotion_per_limit`,`promotion_type`,`brand_name`,`product_category_name`) values (1,49,7,0,0,'银色星芒刺绣网纱底裤','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,1,1,1,1,100,0,'100.00',NULL,0,100,NULL,'111','111','120.00',100,20,'件','1000.00',0,NULL,'银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤',NULL,'银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤',NULL,NULL,NULL,0,'七匹狼','外套'),(2,49,7,0,0,'银色星芒刺绣网纱底裤2','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86578',1,1,1,1,1,1,0,'100.00',NULL,0,100,NULL,'111','111','120.00',100,20,'件','1000.00',0,NULL,'银色星芒刺绣网纱底裤2','银色星芒刺绣网纱底裤',NULL,'银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','<p>银色星芒刺绣网纱底裤</p>','<p>银色星芒刺绣网纱底裤</p>',NULL,NULL,NULL,0,'七匹狼','外套'),(3,1,7,0,0,'银色星芒刺绣网纱底裤3','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86579',1,1,1,1,1,1,0,'100.00',NULL,0,100,NULL,'111','111','120.00',100,20,'件','1000.00',0,NULL,'银色星芒刺绣网纱底裤3','银色星芒刺绣网纱底裤',NULL,'银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤',NULL,NULL,NULL,0,'万和','外套'),(4,1,7,0,0,'银色星芒刺绣网纱底裤4','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86580',1,1,1,1,1,1,0,'100.00',NULL,0,100,NULL,'111','111','120.00',100,20,'件','1000.00',0,NULL,'银色星芒刺绣网纱底裤4','银色星芒刺绣网纱底裤',NULL,'银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤',NULL,NULL,NULL,0,'万和','外套'),(5,1,7,0,0,'银色星芒刺绣网纱底裤5','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86581',1,0,1,1,1,1,0,'100.00',NULL,0,100,NULL,'111','111','120.00',100,20,'件','1000.00',0,NULL,'银色星芒刺绣网纱底裤5','银色星芒刺绣网纱底裤',NULL,'银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤',NULL,NULL,NULL,0,'万和','外套'),(6,1,7,0,0,'银色星芒刺绣网纱底裤6','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86582',1,1,1,1,1,1,0,'100.00',NULL,0,100,NULL,'111','111','120.00',100,20,'件','1000.00',0,NULL,'银色星芒刺绣网纱底裤6','银色星芒刺绣网纱底裤',NULL,'银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤','银色星芒刺绣网纱底裤',NULL,NULL,NULL,0,'万和','外套'),(7,1,7,0,1,'女式超柔软拉毛运动开衫','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,0,0,0,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',100,0,'件','0.00',0,'string','女式超柔软拉毛运动开衫','string','string','string','string','string','string','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(8,1,7,0,1,'女式超柔软拉毛运动开衫1','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,0,0,0,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',100,0,'件','0.00',0,'string','女式超柔软拉毛运动开衫','string','string','string','string','string','string','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(9,1,7,0,1,'女式超柔软拉毛运动开衫1','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,0,0,0,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',100,0,'件','0.00',0,'string','女式超柔软拉毛运动开衫','string','string','string','string','string','string','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(10,1,7,0,1,'女式超柔软拉毛运动开衫1','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,0,0,0,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',100,0,'件','0.00',0,'string','女式超柔软拉毛运动开衫','string','string','string','string','string','string','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(11,1,7,0,1,'女式超柔软拉毛运动开衫1','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,1,0,1,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',100,0,'件','0.00',0,'string','女式超柔软拉毛运动开衫','string','string','string','string','string','string','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(12,1,7,0,1,'女式超柔软拉毛运动开衫2','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,1,0,1,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',100,0,'件','0.00',0,'string','女式超柔软拉毛运动开衫','string','string','string','string','string','string','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(13,1,7,0,1,'女式超柔软拉毛运动开衫3','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,1,0,1,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',100,0,'件','0.00',0,'string','女式超柔软拉毛运动开衫','string','string','string','string','string','string','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(14,1,7,0,1,'女式超柔软拉毛运动开衫3','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,0,0,1,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',100,0,'件','0.00',0,'string','女式超柔软拉毛运动开衫','string','string','string','string','string','string','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(18,1,7,0,1,'女式超柔软拉毛运动开衫3','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png','No86577',1,0,0,1,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',100,0,'件','0.00',0,'string','女式超柔软拉毛运动开衫','string','string','string','string','string','string','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(22,6,7,0,1,'test','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg','',1,1,0,0,0,0,0,'0.00',NULL,0,0,0,'test','','0.00',100,0,'','0.00',1,'1,2','','','','','','','',NULL,NULL,0,0,'小米','外套'),(23,6,19,0,1,'毛衫测试','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg','NO.1098',1,1,1,1,0,0,0,'99.00',NULL,99,99,1000,'毛衫测试11','xxx','109.00',100,0,'件','1000.00',1,'1,2,3','毛衫测试','毛衫测试','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg','毛衫测试','毛衫测试','<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/155x54.bmp\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/APP登录bg1080.jpg\" width=\"500\" height=\"500\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/APP登录界面.jpg\" width=\"500\" height=\"500\" /></p>','',NULL,NULL,0,2,'小米','手机通讯'),(24,6,7,0,NULL,'xxx','','',1,0,0,0,0,0,0,'0.00',NULL,0,0,0,'xxx','','0.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'小米','外套'),(26,3,53,0,3,'华为 HUAWEI P20 ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','6946605',0,0,1,1,0,100,0,'3788.00',NULL,3788,3788,0,'AI智慧全面屏 6GB +64GB 亮黑色 全网通版 移动联通电信4G手机 双卡双待手机 双卡双待','','4288.00',1000,0,'件','0.00',1,'2,3,1','','','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg','','','<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44f1cNf51f3bb0.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44fa8Nfcf71c10.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44fa9N40e78ee0.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad457f4N1c94bdda.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad457f5Nd30de41d.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5b10fb0eN0eb053fb.jpg\" /></p>','',NULL,NULL,0,0,'华为','999'),(27,6,19,0,3,'小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','7437788',0,1,1,1,0,0,0,'2699.00',NULL,2699,2699,0,'骁龙845处理器，红外人脸解锁，AI变焦双摄，AI语音助手小米6X低至1299，点击抢购','小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待','2699.00',100,0,'','0.00',0,'','','','','','','<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b2254e8N414e6d3a.jpg\" width=\"500\" /></p>','',NULL,NULL,0,3,'小米','手机通讯'),(28,6,19,0,3,'小米 红米5A 全网通版 3GB+32GB 香槟金 移动联通电信4G手机 双卡双待','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','7437789',0,1,1,1,0,0,0,'649.00',NULL,649,649,0,'8天超长待机，137g轻巧机身，高通骁龙处理器小米6X低至1299，点击抢购','','649.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,4,'小米','手机通讯'),(29,51,19,0,3,'Apple iPhone 8 Plus 64GB 红色特别版 移动联通电信4G手机','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','7437799',0,1,1,1,0,0,0,'5499.00',NULL,5499,5499,0,'【限时限量抢购】Apple产品年中狂欢节，好物尽享，美在智慧！速来 >> 勾选[保障服务][原厂保2年]，获得AppleCare+全方位服务计划，原厂延保售后无忧。','','5499.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'苹果','手机通讯'),(30,50,8,0,1,'HLA海澜之家简约动物印花短袖T恤','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg','HNTBJ2E042A',0,1,1,1,0,0,0,'98.00',NULL,0,0,0,'2018夏季新品微弹舒适新款短T男生 6月6日-6月20日，满300减30，参与互动赢百元礼券，立即分享赢大奖','','98.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'海澜之家','T恤'),(31,50,8,0,1,'HLA海澜之家蓝灰花纹圆领针织布短袖T恤','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ac98b64N70acd82f.jpg!cc_350x449.jpg','HNTBJ2E080A',0,1,0,0,0,0,0,'98.00',NULL,0,0,0,'2018夏季新品短袖T恤男HNTBJ2E080A 蓝灰花纹80 175/92A/L80A 蓝灰花纹80 175/92A/L','','98.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'海澜之家','T恤'),(32,50,8,0,NULL,'HLA海澜之家短袖T恤男基础款','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a51eb88Na4797877.jpg','HNTBJ2E153A',0,1,0,0,0,0,0,'68.00',NULL,0,0,0,'HLA海澜之家短袖T恤男基础款简约圆领HNTBJ2E153A藏青(F3)175/92A(50)','','68.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'海澜之家','T恤'),(33,6,35,0,NULL,'小米（MI）小米电视4A ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg','4609652',0,1,0,0,0,0,0,'2499.00',NULL,0,0,0,'小米（MI）小米电视4A 55英寸 L55M5-AZ/L55M5-AD 2GB+8GB HDR 4K超高清 人工智能网络液晶平板电视','','2499.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'小米','手机数码'),(34,6,35,0,NULL,'小米（MI）小米电视4A 65英寸','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b028530N51eee7d4.jpg','4609660',0,1,0,0,0,0,0,'3999.00',NULL,0,0,0,' L65M5-AZ/L65M5-AD 2GB+8GB HDR 4K超高清 人工智能网络液晶平板电视','','3999.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'小米','手机数码'),(35,58,29,0,NULL,'耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b235bb9Nf606460b.jpg','6799342',1,1,0,0,0,0,0,'369.00',NULL,0,0,0,'耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码','','369.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'NIKE','男鞋'),(36,58,29,0,NULL,'耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg','6799345',1,1,1,1,0,0,0,'499.00',NULL,0,0,0,'耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码','','499.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'NIKE','男鞋'),(37,6,19,0,3,'dffds','','32424',1,0,0,0,0,0,0,'12.00',NULL,0,0,0,'fdsafsda','fdsafsa','22.00',333,0,'2','22.00',0,'','','','','','','<p>dfdsafdsafdsafdsa</p>','<p>4422323</p>',NULL,NULL,0,0,'小米',''),(38,2,19,0,3,'rewrew','http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/u=3610738457,119243852&fm=21&gp=0.jpg','rewqr',0,0,0,0,0,0,0,'0.00',NULL,0,0,0,'rewqrwe','rewqrweq','0.00',0,0,'','0.00',0,'','','','http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/3.jpg,http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/1.jpg','','','<p><img class=\"wscnph\" src=\"http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/xiaofeixia.jpg\" width=\"500\" /><img class=\"wscnph\" src=\"http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/图哈片.jpg\" /></p>','<p>发放第三方</p>\n<p><img class=\"wscnph\" src=\"http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/smtp&amp;pop3.png\" /></p>',NULL,NULL,0,0,'三星',''),(39,49,7,0,10,'fdsfds','','',1,0,0,0,0,0,0,'0.00',NULL,0,0,0,'2323','fdsfds','0.00',0,0,'','0.00',0,'1,2,3','','','','','','','',NULL,NULL,0,0,'七匹狼','外套'),(40,5,53,0,NULL,'weqq','','ewq',1,0,0,0,0,0,0,'0.00',NULL,0,0,0,'ewqe','eqw','0.00',0,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'方太','999'),(41,2,53,0,NULL,'fsaf','','fds',1,0,0,0,0,0,0,'0.00',NULL,0,0,0,'fdsf','fdsfs','0.00',0,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'三星','999'),(42,4,53,0,1,'dsf','','fds',1,0,0,0,0,0,0,'0.00',NULL,0,0,0,'fds','fsa','0.00',0,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'格力','999'),(43,49,35,0,1,'热热我去','','323',1,0,0,0,0,0,0,'0.00',NULL,0,0,0,'发范德萨','发顺丰','0.00',0,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'七匹狼','家用电器,电视');

/*Table structure for table `pms_product_attribute` */

DROP TABLE IF EXISTS `pms_product_attribute`;

CREATE TABLE `pms_product_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_attribute_category_id` bigint(20) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `select_type` int(1) DEFAULT NULL COMMENT '属性选择类型：0->唯一；1->单选；2->多选',
  `input_type` int(1) DEFAULT NULL COMMENT '属性录入方式：0->手工录入；1->从列表中选取',
  `input_list` varchar(255) DEFAULT NULL COMMENT '可选值列表，以逗号隔开',
  `sort` int(11) DEFAULT NULL COMMENT '排序字段：最高的可以单独上传图片',
  `filter_type` int(1) DEFAULT NULL COMMENT '分类筛选样式：1->普通；1->颜色',
  `search_type` int(1) DEFAULT NULL COMMENT '检索类型；0->不需要进行检索；1->关键字检索；2->范围检索',
  `related_status` int(1) DEFAULT NULL COMMENT '相同属性产品是否关联；0->不关联；1->关联',
  `hand_add_status` int(1) DEFAULT NULL COMMENT '是否支持手动新增；0->不支持；1->支持',
  `type` int(1) DEFAULT NULL COMMENT '属性的类型；0->规格；1->参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='商品属性参数表';

/*Data for the table `pms_product_attribute` */

insert  into `pms_product_attribute`(`id`,`product_attribute_category_id`,`name`,`select_type`,`input_type`,`input_list`,`sort`,`filter_type`,`search_type`,`related_status`,`hand_add_status`,`type`) values (1,1,'尺寸',2,1,'M,X,XL,2XL,3XL,4XL',0,0,0,0,0,0),(7,1,'颜色',0,1,'黑色,红色,白色,粉色',100,0,0,0,1,0),(13,0,'上市年份',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(14,0,'上市年份1',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(15,0,'上市年份2',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(16,0,'上市年份3',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(17,0,'上市年份4',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(18,0,'上市年份5',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(19,0,'适用对象',1,1,'青年女性,中年女性',0,0,0,0,0,1),(20,0,'适用对象1',2,1,'青年女性,中年女性',0,0,0,0,0,1),(21,0,'适用对象3',2,1,'青年女性,中年女性',0,0,0,0,0,1),(24,1,'商品编号',1,0,'',0,0,0,0,0,1),(25,1,'适用季节',1,1,'春季,夏季,秋季,冬季',0,0,0,0,0,1),(32,2,'适用人群',0,1,'老年,青年,中年',0,0,0,0,0,1),(33,2,'风格',0,1,'嘻哈风格,基础大众,商务正装',0,0,0,0,0,1),(35,2,'颜色',0,0,'',100,0,0,0,1,0),(37,1,'适用人群',1,1,'儿童,青年,中年,老年',0,0,0,0,0,1),(38,1,'上市时间',1,1,'2017年秋,2017年冬,2018年春,2018年夏',0,0,0,0,0,1),(39,1,'袖长',1,1,'短袖,长袖,中袖',0,0,0,0,0,1),(40,2,'尺码',0,1,'29,30,31,32,33,34',0,0,0,0,0,0),(41,2,'适用场景',0,1,'居家,运动,正装',0,0,0,0,0,1),(42,2,'上市时间',0,1,'2018年春,2018年夏',0,0,0,0,0,1),(43,3,'颜色',0,0,'',100,0,0,0,1,0),(44,3,'容量',1,1,'16G,32G,64G,128G',0,0,0,0,0,0),(45,3,'屏幕尺寸',0,0,'',0,0,0,0,0,1),(46,3,'网络',0,1,'3G,4G',0,0,0,0,0,1),(47,3,'系统',0,1,'Android,IOS',0,0,0,0,0,1),(48,3,'电池容量',0,0,'',0,0,0,0,0,1),(49,2,'eee',0,0,'',0,1,0,0,1,0),(50,10,'dsdsa',0,0,'',0,0,0,0,0,0),(51,10,'de33',1,0,'',0,1,1,1,0,0),(52,10,'d33323',2,0,'ffff',0,0,0,0,0,0),(53,10,'4444343',0,0,'',0,0,0,0,0,0),(54,1,'dsfgdf',0,0,'',0,0,0,0,0,1),(55,10,'fdsfdsgfds',0,0,'',0,0,0,0,0,1);

/*Table structure for table `pms_product_attribute_category` */

DROP TABLE IF EXISTS `pms_product_attribute_category`;

CREATE TABLE `pms_product_attribute_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `attribute_count` int(11) DEFAULT '0' COMMENT '属性数量',
  `param_count` int(11) DEFAULT '0' COMMENT '参数数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='产品属性分类表';

/*Data for the table `pms_product_attribute_category` */

insert  into `pms_product_attribute_category`(`id`,`name`,`attribute_count`,`param_count`) values (1,'服装-T恤',2,6),(2,'服装-裤装',3,4),(3,'手机数码-手机通讯',2,4),(4,'配件',0,0),(5,'居家',0,0),(6,'洗护',0,0),(10,'测试分类',4,1);

/*Table structure for table `pms_product_attribute_value` */

DROP TABLE IF EXISTS `pms_product_attribute_value`;

CREATE TABLE `pms_product_attribute_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `product_attribute_id` bigint(20) DEFAULT NULL,
  `value` varchar(64) DEFAULT NULL COMMENT '手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=utf8 COMMENT='存储产品参数信息的表';

/*Data for the table `pms_product_attribute_value` */

insert  into `pms_product_attribute_value`(`id`,`product_id`,`product_attribute_id`,`value`) values (1,9,1,'X'),(2,10,1,'X'),(3,11,1,'X'),(4,12,1,'X'),(5,13,1,'X'),(6,14,1,'X'),(7,18,1,'X'),(8,7,1,'X'),(9,7,1,'XL'),(10,7,1,'XXL'),(11,22,7,'x,xx'),(12,22,24,'no110'),(13,22,25,'春季'),(14,22,37,'青年'),(15,22,38,'2018年春'),(16,22,39,'长袖'),(124,23,7,'米白色,浅黄色'),(125,23,24,'no1098'),(126,23,25,'春季'),(127,23,37,'青年'),(128,23,38,'2018年春'),(129,23,39,'长袖'),(130,1,13,NULL),(131,1,14,NULL),(132,1,15,NULL),(133,1,16,NULL),(134,1,17,NULL),(135,1,18,NULL),(136,1,19,NULL),(137,1,20,NULL),(138,1,21,NULL),(139,2,13,NULL),(140,2,14,NULL),(141,2,15,NULL),(142,2,16,NULL),(143,2,17,NULL),(144,2,18,NULL),(145,2,19,NULL),(146,2,20,NULL),(147,2,21,NULL),(183,31,24,NULL),(184,31,25,'夏季'),(185,31,37,'青年'),(186,31,38,'2018年夏'),(187,31,39,'短袖'),(198,30,24,NULL),(199,30,25,'夏季'),(200,30,37,'青年'),(201,30,38,'2018年夏'),(202,30,39,'短袖'),(213,27,43,'黑色,蓝色'),(214,27,45,'5.8'),(215,27,46,'4G'),(216,27,47,'Android'),(217,27,48,'3000ml'),(218,28,43,'金色,银色'),(219,28,45,'5.0'),(220,28,46,'4G'),(221,28,47,'Android'),(222,28,48,'2800ml'),(223,29,43,'金色,银色'),(224,29,45,'4.7'),(225,29,46,'4G'),(226,29,47,'IOS'),(227,29,48,'1960ml'),(238,37,43,'6色,7色,5光10色'),(239,37,45,'111'),(240,37,46,'3G'),(241,37,47,'IOS'),(242,37,48,'3333'),(243,38,43,'黑色,无色,花色'),(244,38,45,'22'),(245,38,46,'3G'),(246,38,47,'Android'),(247,38,48,'3330'),(248,39,55,'fdfs'),(249,42,24,NULL),(250,42,25,NULL),(251,42,37,NULL),(252,42,38,NULL),(253,42,39,NULL),(254,42,54,NULL),(265,26,43,'金色'),(266,26,45,'5.0'),(267,26,46,'4G'),(268,26,47,'Android'),(269,26,48,'3000'),(270,43,24,NULL),(271,43,25,NULL),(272,43,37,NULL),(273,43,38,NULL),(274,43,39,NULL),(275,43,54,NULL);

/*Table structure for table `pms_product_category` */

DROP TABLE IF EXISTS `pms_product_category`;

CREATE TABLE `pms_product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上机分类的编号：0表示一级分类',
  `name` varchar(64) DEFAULT NULL,
  `level` int(1) DEFAULT NULL COMMENT '分类级别：0->1级；1->2级',
  `product_count` int(11) DEFAULT NULL,
  `product_unit` varchar(64) DEFAULT NULL,
  `nav_status` int(1) DEFAULT NULL COMMENT '是否显示在导航栏：0->不显示；1->显示',
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
  `sort` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `keywords` varchar(255) DEFAULT NULL,
  `description` text COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='产品分类';

/*Data for the table `pms_product_category` */

insert  into `pms_product_category`(`id`,`parent_id`,`name`,`level`,`product_count`,`product_unit`,`nav_status`,`show_status`,`sort`,`icon`,`keywords`,`description`) values (1,0,'服装',0,100,'件',1,1,1,NULL,'服装','服装分类'),(2,0,'手机数码',0,100,'件',1,1,1,NULL,'手机数码','手机数码'),(3,0,'家用电器',0,100,'件',1,1,1,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/subject_cate_jiadian.png','家用电器','家用电器'),(4,0,'家具家装',0,100,'件',1,1,1,NULL,'家具家装','家具家装'),(5,0,'汽车用品',0,100,'件',1,1,1,NULL,'汽车用品','汽车用品'),(7,1,'外套',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_waitao.png','外套','外套'),(8,1,'T恤',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_tshirt.png','T恤','T恤'),(9,1,'休闲裤',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_xiuxianku.png','休闲裤','休闲裤'),(10,1,'牛仔裤',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_niuzaiku.png','牛仔裤','牛仔裤'),(11,1,'衬衫',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_chenshan.png','衬衫','衬衫分类'),(13,12,'家电子分类1',1,1,'string',0,1,0,'string','string','string'),(14,12,'家电子分类2',1,1,'string',0,1,0,'string','string','string'),(19,2,'手机通讯',1,0,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_shouji.png','手机通讯','手机通讯'),(29,1,'男鞋',1,0,'',0,0,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_xie.png','',''),(30,2,'手机配件',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_peijian.png','手机配件','手机配件'),(31,2,'摄影摄像',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_sheying.png','',''),(32,2,'影音娱乐',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_yule.png','',''),(33,2,'数码配件',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_yule.png','',''),(34,2,'智能设备',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_zhineng.png','',''),(35,3,'电视',1,0,'',1,1,0,'','',''),(36,3,'空调',1,0,'',1,1,0,'','',''),(37,3,'洗衣机',1,0,'',1,1,0,'','',''),(38,3,'冰箱',1,0,'',1,1,0,'','',''),(39,3,'厨卫大电',1,0,'',1,1,0,'','',''),(40,3,'厨房小电',1,0,'',0,0,0,'','',''),(41,3,'生活电器',1,0,'',0,0,0,'','',''),(42,3,'个护健康',1,0,'',0,0,0,'','',''),(43,4,'厨房卫浴',1,0,'',1,1,0,'','',''),(44,4,'灯饰照明',1,0,'',1,1,0,'','',''),(45,4,'五金工具',1,0,'',1,1,0,'','',''),(46,4,'卧室家具',1,0,'',1,1,0,'','',''),(47,4,'客厅家具',1,0,'',1,1,0,'','',''),(48,5,'全新整车',1,0,'',1,1,0,'','',''),(49,5,'车载电器',1,0,'',1,1,0,'','',''),(50,5,'维修保养',1,0,'',1,1,0,'','',''),(51,5,'汽车装饰',1,0,'',1,1,0,'','',''),(52,0,'图书/音响',0,0,'件',1,1,0,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/图哈片.jpg','',''),(53,7,'999',2,0,'e',1,1,0,NULL,NULL,NULL),(54,2,'手机配件',1,0,'',1,1,0,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190313/图哈片.jpg','啊',''),(55,7,'fggfdssg',2,0,'gfdsfds',0,0,0,'','',''),(56,0,'工业品',0,0,'',0,0,0,'','',''),(57,56,'工具',1,0,'',0,0,0,'','',''),(58,57,'手动工具',2,0,'',0,0,0,'','','');

/*Table structure for table `pms_product_category_attribute_relation` */

DROP TABLE IF EXISTS `pms_product_category_attribute_relation`;

CREATE TABLE `pms_product_category_attribute_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_category_id` bigint(20) DEFAULT NULL,
  `product_attribute_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='产品的分类和属性的关系表，用于设置分类筛选条件';

/*Data for the table `pms_product_category_attribute_relation` */

insert  into `pms_product_category_attribute_relation`(`id`,`product_category_id`,`product_attribute_id`) values (1,54,47),(2,55,32),(3,55,55);

/*Table structure for table `pms_product_full_reduction` */

DROP TABLE IF EXISTS `pms_product_full_reduction`;

CREATE TABLE `pms_product_full_reduction` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `full_price` decimal(10,2) DEFAULT NULL,
  `reduce_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='产品满减表(只针对同商品)';

/*Data for the table `pms_product_full_reduction` */

insert  into `pms_product_full_reduction`(`id`,`product_id`,`full_price`,`reduce_price`) values (1,7,'100.00','20.00'),(2,8,'100.00','20.00'),(3,9,'100.00','20.00'),(4,10,'100.00','20.00'),(5,11,'100.00','20.00'),(6,12,'100.00','20.00'),(7,13,'100.00','20.00'),(8,14,'100.00','20.00'),(9,18,'100.00','20.00'),(10,7,'200.00','50.00'),(11,7,'300.00','100.00'),(14,22,'0.00','0.00'),(16,24,'0.00','0.00'),(34,23,'0.00','0.00'),(44,31,'0.00','0.00'),(46,32,'0.00','0.00'),(48,33,'0.00','0.00'),(53,36,'0.00','0.00'),(54,35,'0.00','0.00'),(55,34,'0.00','0.00'),(56,30,'0.00','0.00'),(59,27,'0.00','0.00'),(60,28,'500.00','50.00'),(61,28,'1000.00','120.00'),(62,29,'0.00','0.00'),(65,37,'0.00','0.00'),(66,38,'0.00','0.00'),(67,39,'0.00','0.00'),(68,40,'0.00','0.00'),(69,41,'0.00','0.00'),(70,42,'0.00','0.00'),(73,26,'0.00','0.00'),(74,43,'0.00','0.00');

/*Table structure for table `pms_product_ladder` */

DROP TABLE IF EXISTS `pms_product_ladder`;

CREATE TABLE `pms_product_ladder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL COMMENT '满足的商品数量',
  `discount` decimal(10,2) DEFAULT NULL COMMENT '折扣',
  `price` decimal(10,2) DEFAULT NULL COMMENT '折后价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='产品阶梯价格表(只针对同商品)';

/*Data for the table `pms_product_ladder` */

insert  into `pms_product_ladder`(`id`,`product_id`,`count`,`discount`,`price`) values (1,7,3,'0.70','0.00'),(2,8,3,'0.70','0.00'),(3,9,3,'0.70','0.00'),(4,10,3,'0.70','0.00'),(5,11,3,'0.70','0.00'),(6,12,3,'0.70','0.00'),(7,13,3,'0.70','0.00'),(8,14,3,'0.70','0.00'),(12,18,3,'0.70','0.00'),(14,7,4,'0.60','0.00'),(15,7,5,'0.50','0.00'),(18,22,0,'0.00','0.00'),(20,24,0,'0.00','0.00'),(38,23,0,'0.00','0.00'),(48,31,0,'0.00','0.00'),(50,32,0,'0.00','0.00'),(52,33,0,'0.00','0.00'),(57,36,0,'0.00','0.00'),(58,35,0,'0.00','0.00'),(59,34,0,'0.00','0.00'),(60,30,0,'0.00','0.00'),(64,27,2,'0.80','0.00'),(65,27,3,'0.75','0.00'),(66,28,0,'0.00','0.00'),(67,29,0,'0.00','0.00'),(70,37,0,'0.00','0.00'),(71,38,0,'0.00','0.00'),(72,39,0,'0.00','0.00'),(73,40,0,'0.00','0.00'),(74,41,0,'0.00','0.00'),(75,42,0,'0.00','0.00'),(78,26,0,'0.00','0.00'),(79,43,0,'0.00','0.00');

/*Table structure for table `pms_product_operate_log` */

DROP TABLE IF EXISTS `pms_product_operate_log`;

CREATE TABLE `pms_product_operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `price_old` decimal(10,2) DEFAULT NULL,
  `price_new` decimal(10,2) DEFAULT NULL,
  `sale_price_old` decimal(10,2) DEFAULT NULL,
  `sale_price_new` decimal(10,2) DEFAULT NULL,
  `gift_point_old` int(11) DEFAULT NULL COMMENT '赠送的积分',
  `gift_point_new` int(11) DEFAULT NULL,
  `use_point_limit_old` int(11) DEFAULT NULL,
  `use_point_limit_new` int(11) DEFAULT NULL,
  `operate_man` varchar(64) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pms_product_operate_log` */

/*Table structure for table `pms_product_vertify_record` */

DROP TABLE IF EXISTS `pms_product_vertify_record`;

CREATE TABLE `pms_product_vertify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `vertify_man` varchar(64) DEFAULT NULL COMMENT '审核人',
  `status` int(1) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL COMMENT '反馈详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品审核记录';

/*Data for the table `pms_product_vertify_record` */

insert  into `pms_product_vertify_record`(`id`,`product_id`,`create_time`,`vertify_man`,`status`,`detail`) values (1,1,'2018-04-27 16:36:41','test',1,'验证通过'),(2,2,'2018-04-27 16:36:41','test',1,'验证通过');

/*Table structure for table `pms_sku_stock` */

DROP TABLE IF EXISTS `pms_sku_stock`;

CREATE TABLE `pms_sku_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `sku_code` varchar(64) NOT NULL COMMENT 'sku编码',
  `price` decimal(10,2) DEFAULT NULL,
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `low_stock` int(11) DEFAULT NULL COMMENT '预警库存',
  `sp1` varchar(64) DEFAULT NULL COMMENT '销售属性1',
  `sp2` varchar(64) DEFAULT NULL,
  `sp3` varchar(64) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL COMMENT '展示图片',
  `sale` int(11) DEFAULT NULL COMMENT '销量',
  `promotion_price` decimal(10,2) DEFAULT NULL COMMENT '单品促销价格',
  `lock_stock` int(11) DEFAULT '0' COMMENT '锁定库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COMMENT='sku的库存';

/*Data for the table `pms_sku_stock` */

insert  into `pms_sku_stock`(`id`,`product_id`,`sku_code`,`price`,`stock`,`low_stock`,`sp1`,`sp2`,`sp3`,`pic`,`sale`,`promotion_price`,`lock_stock`) values (1,7,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(2,8,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(3,9,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(4,10,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(5,11,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(6,12,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(7,13,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(8,14,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(9,18,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(10,7,'string','0.00',0,0,'string','string','sp3','string',0,NULL,0),(11,7,'string','0.00',0,0,'string','string','sp33','string',0,NULL,0),(12,22,'111','99.00',0,NULL,'x','M',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg',NULL,NULL,0),(13,22,'112','99.00',0,NULL,'xx','M',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/2018032614134591_20180326141345650 (4).png',NULL,NULL,0),(78,23,'201806070023001','99.00',0,NULL,'米白色','M',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg',NULL,NULL,0),(79,23,'201806070023002','99.00',0,NULL,'米白色','X',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg',NULL,NULL,0),(80,23,'201806070023003','99.00',0,NULL,'浅黄色','M',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/2017091716493787_20170917164937650 (1).png',NULL,NULL,0),(81,23,'201806070023004','99.00',0,NULL,'浅黄色','X',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/2017091716493787_20170917164937650 (1).png',NULL,NULL,0),(98,27,'201808270027001','2699.00',97,NULL,'黑色','32G',NULL,NULL,NULL,NULL,-24),(99,27,'201808270027002','2999.00',100,NULL,'黑色','64G',NULL,NULL,NULL,NULL,0),(100,27,'201808270027003','2699.00',100,NULL,'蓝色','32G',NULL,NULL,NULL,NULL,0),(101,27,'201808270027004','2999.00',100,NULL,'蓝色','64G',NULL,NULL,NULL,NULL,0),(102,28,'201808270028001','649.00',99,NULL,'金色','16G',NULL,NULL,NULL,NULL,-8),(103,28,'201808270028002','699.00',99,NULL,'金色','32G',NULL,NULL,NULL,NULL,-8),(104,28,'201808270028003','649.00',100,NULL,'银色','16G',NULL,NULL,NULL,NULL,0),(105,28,'201808270028004','699.00',100,NULL,'银色','32G',NULL,NULL,NULL,NULL,0),(106,29,'201808270029001','5499.00',99,NULL,'金色','32G',NULL,NULL,NULL,NULL,-8),(107,29,'201808270029002','6299.00',100,NULL,'金色','64G',NULL,NULL,NULL,NULL,0),(108,29,'201808270029003','5499.00',100,NULL,'银色','32G',NULL,NULL,NULL,NULL,0),(109,29,'201808270029004','6299.00',100,NULL,'银色','64G',NULL,NULL,NULL,NULL,0),(118,37,'t1','1.00',1,1,'6色','32G',NULL,NULL,NULL,NULL,0),(119,37,'t2','2.00',1,1,'6色','64G',NULL,NULL,NULL,NULL,0),(120,37,'t3','3.00',1,1,'6色','128G',NULL,NULL,NULL,NULL,0),(121,37,'t4','4.00',1,1,'7色','32G',NULL,NULL,NULL,NULL,0),(122,37,'t5','5.00',1,1,'7色','64G',NULL,NULL,NULL,NULL,0),(123,37,'t6','6.00',1,1,'7色','128G',NULL,NULL,NULL,NULL,0),(124,38,'201903120038001','111.00',NULL,NULL,'花色','16G',NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/5.jpg',NULL,NULL,0),(125,38,'201903120038002','111.00',NULL,NULL,'花色','64G',NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/5.jpg',NULL,NULL,0),(126,38,'201903120038003','111.00',NULL,NULL,'花色','128G',NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/5.jpg',NULL,NULL,0),(127,38,'201903120038004','111.00',NULL,NULL,'无色','16G',NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/6.jpg',NULL,NULL,0),(128,38,'201903120038005','111.00',NULL,NULL,'无色','64G',NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/6.jpg',NULL,NULL,0),(129,38,'201903120038006','111.00',NULL,NULL,'无色','128G',NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/6.jpg',NULL,NULL,0),(130,38,'201903120038007',NULL,NULL,NULL,'黑色','16G',NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/7.jpg',NULL,NULL,0),(131,38,'201903120038008',NULL,NULL,NULL,'黑色','64G',NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/7.jpg',NULL,NULL,0),(132,38,'201903120038009',NULL,NULL,NULL,'黑色','128G',NULL,'http://leifengyang.oss-cn-beijing.aliyuncs.com/gmall/images/20190312/7.jpg',NULL,NULL,0),(133,39,'201903120039001',NULL,NULL,NULL,'','','ffff',NULL,NULL,NULL,0),(142,26,'1323','1.00',1,1,'金色','16G',NULL,NULL,NULL,NULL,0),(143,26,'234w','1.00',1,1,'金色','32G',NULL,NULL,NULL,NULL,0),(144,26,'3eee','1.00',1,1,'银色','16G',NULL,NULL,NULL,NULL,0),(145,26,'4443','1.00',1,1,'银色','32G',NULL,NULL,NULL,NULL,0);
