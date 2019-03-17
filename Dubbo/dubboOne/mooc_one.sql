DROP TABLE IF EXISTS `item_info`;

CREATE TABLE `item_info`
(
  `id`          int(11)   NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code`        varchar(255)   DEFAULT NULL COMMENT '商品编码',
  `name`        varchar(255)   DEFAULT NULL COMMENT '名称',
  `price`       decimal(15, 2) DEFAULT NULL COMMENT '销售价',
  `is_active`   int(11)        DEFAULT '1' COMMENT '是否有效（1=是；0=否）',
  `create_time` datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`) USING BTREE COMMENT '商品编码唯一'
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8 COMMENT ='商品信息表';

-- ----------------------------
-- Records of item_info
-- ----------------------------
INSERT INTO `item_info` (`id`, `code`, `name`, `price`, `is_active`)
VALUES (1, '10010', 'Java编程思想', 58.50, 1),
       (2, '10011', '由浅入深实战分布式中间件', 45.50, 1),
       (3, '10012', 'Dubbo实战教程', 68.00, 1),
       (4, '10013', 'JVM深入实战指南', 38.50, 1),
       (5, '10014', 'SpringBoot实战', 56.50, 1),
       (6, '10015', 'SpringCloud实战', 64.00, 1),
       (7, '10017', 'Mysql实战指南', 75.50, 1);
