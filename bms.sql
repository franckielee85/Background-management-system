CREATE TABLE `bms_teachers` (
  `id` char(19) NOT NULL COMMENT '老师ID',
  `name` varchar(20) NOT NULL COMMENT '老师姓名',
  `about` varchar(500) NOT NULL DEFAULT '' COMMENT '老师简介',
  `career` varchar(500) DEFAULT NULL COMMENT '老师经历',
  `level` int(10) unsigned NOT NULL COMMENT '头衔 1高级老师 2中级老师 3初级老师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '老师头像',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='老师';