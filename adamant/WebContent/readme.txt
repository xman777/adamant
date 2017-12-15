http://localhost:8080/adamant/userController/xtest.do?param1=param1&param2=param2

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(50) NOT NULL COMMENT '菜单ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `menu_url` varchar(1000) DEFAULT NULL COMMENT '菜单链接地址',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `level` int(10) DEFAULT NULL COMMENT '菜单等级',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '菜单父ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('x', '用户管理', '#', '000000', '1', null);
INSERT INTO `menu` VALUES ('xx', '角色菜单管理', '#', '000000', '1', null);
INSERT INTO `menu` VALUES ('xxx', '菜单管理', '#', '000000', '1', null);
INSERT INTO `menu` VALUES ('xxxxx', '角色管理', '#', '000000', '1', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('000000', '超级管理员', '管理系统用户相关信息');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `name` varchar(100) DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机号码',
  `status` int(10) NOT NULL DEFAULT '0' COMMENT '状态，0：未生效 1：生效',
  `role_id` varchar(50) DEFAULT '' COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('xxxxxxxx', 'admin', '111111', '13500000000', 1, '000000');

DROP TABLE IF EXISTS `excel_table`;
CREATE TABLE `excel_table` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='excel信息表';