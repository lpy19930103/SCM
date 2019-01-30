/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : scm

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-01-30 15:48:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_category
-- ----------------------------
DROP TABLE IF EXISTS `base_category`;
CREATE TABLE `base_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL COMMENT '品类名称',
  `category_unit` varchar(255) DEFAULT NULL COMMENT '计量单位',
  `material` varchar(255) DEFAULT NULL COMMENT '材质',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父品类',
  `creater` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_category
-- ----------------------------

-- ----------------------------
-- Table structure for base_depot
-- ----------------------------
DROP TABLE IF EXISTS `base_depot`;
CREATE TABLE `base_depot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `depot_name` varchar(50) DEFAULT NULL COMMENT '仓库名',
  `depot_address` varchar(70) DEFAULT NULL COMMENT '仓库地址',
  `depot_code` bigint(20) DEFAULT NULL COMMENT '仓库编码',
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员id',
  `creater` varchar(30) DEFAULT NULL COMMENT '创建人',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `depot_des` varchar(255) DEFAULT NULL COMMENT '仓库描述',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_depot
-- ----------------------------

-- ----------------------------
-- Table structure for base_employee
-- ----------------------------
DROP TABLE IF EXISTS `base_employee`;
CREATE TABLE `base_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '工号',
  `emp_name` varchar(255) DEFAULT NULL,
  `emp_id_card` varchar(255) DEFAULT NULL,
  `emp_phone` varchar(255) DEFAULT NULL,
  `emp_email` varchar(255) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL COMMENT '1男 2女',
  `birthday` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `emp_type` tinyint(4) DEFAULT NULL,
  `creater` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_employee
-- ----------------------------
INSERT INTO `base_employee` VALUES ('2', null, null, '18519121233', null, null, null, null, null, null, null, null, null, '201901301526150000000001009001');
INSERT INTO `base_employee` VALUES ('4', null, null, '18519121233', null, null, null, null, null, null, null, null, null, '201901301545070000000001011001');

-- ----------------------------
-- Table structure for base_goods
-- ----------------------------
DROP TABLE IF EXISTS `base_goods`;
CREATE TABLE `base_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `goods_code` varchar(30) DEFAULT NULL COMMENT '商品编码',
  `goods_category` varchar(50) DEFAULT NULL COMMENT '商品品类',
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `unit` varchar(20) DEFAULT NULL COMMENT '计量单位',
  `goods_color` varchar(20) DEFAULT NULL COMMENT '颜色',
  `material` varchar(255) DEFAULT NULL COMMENT '材质',
  `purchase_price` bigint(20) DEFAULT NULL COMMENT '进价',
  `sale_price` bigint(20) DEFAULT NULL COMMENT '售价',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(11) DEFAULT NULL COMMENT '商品状态0:可售1:不可售2:缺货',
  `creater` varchar(30) DEFAULT NULL COMMENT '创建人',
  `goods_image` varchar(255) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
-- Records of base_goods
-- ----------------------------

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `goods_unit` varchar(255) DEFAULT NULL,
  `goods_price` bigint(10) DEFAULT NULL,
  `goods_image` varchar(255) DEFAULT NULL,
  `goods_num` int(11) DEFAULT NULL,
  `goods_total` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE `purchase_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(20) DEFAULT NULL COMMENT '供货商id',
  `supplier_name` varchar(50) DEFAULT NULL COMMENT '供货商名',
  `depot_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `emp_id` bigint(20) DEFAULT NULL COMMENT '员工id',
  `purchase_des` varchar(255) DEFAULT NULL COMMENT '进货描述',
  `review_status` tinyint(4) DEFAULT NULL COMMENT '1审核通过，2审核失败审核状态',
  `review_name` varchar(255) DEFAULT NULL COMMENT '审核人',
  `review_at` datetime DEFAULT NULL COMMENT '审核时间',
  `storage_at` datetime DEFAULT NULL COMMENT '入库时间',
  `goods_total` bigint(255) DEFAULT NULL COMMENT '商品总价',
  `status` tinyint(4) DEFAULT NULL COMMENT '订单状态',
  `order_type` tinyint(4) DEFAULT NULL COMMENT '订单类型',
  `creater` varchar(20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `supplier_phone` varchar(50) DEFAULT NULL COMMENT '供货商电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase_order
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_supplier
-- ----------------------------
DROP TABLE IF EXISTS `purchase_supplier`;
CREATE TABLE `purchase_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sup_name` varchar(255) DEFAULT NULL COMMENT '供应商名称',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `contact_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `contact_name` varchar(255) DEFAULT NULL COMMENT '联系人',
  `sup_des` varchar(255) DEFAULT NULL COMMENT '供应商描述',
  `status` tinyint(4) DEFAULT NULL,
  `creater` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for sale_customer
-- ----------------------------
DROP TABLE IF EXISTS `sale_customer`;
CREATE TABLE `sale_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cus_name` varchar(255) DEFAULT NULL COMMENT '客户名',
  `contact_name` varchar(255) DEFAULT NULL COMMENT '联系人',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `contact_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `cus_des` varchar(255) DEFAULT NULL COMMENT '客户描述',
  `status` varchar(255) DEFAULT NULL,
  `creater` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_customer
-- ----------------------------

-- ----------------------------
-- Table structure for sale_order
-- ----------------------------
DROP TABLE IF EXISTS `sale_order`;
CREATE TABLE `sale_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_total` bigint(20) DEFAULT NULL COMMENT '商品总价',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `customer_name` varchar(20) DEFAULT NULL COMMENT '客户名',
  `emp_id` bigint(20) DEFAULT NULL COMMENT '员工id',
  `sale_des` varchar(255) DEFAULT NULL,
  `review_status` int(11) DEFAULT NULL COMMENT '审核状态',
  `review_name` varchar(20) DEFAULT NULL COMMENT '审核人',
  `review_at` datetime DEFAULT NULL COMMENT '审核时间',
  `out_time` datetime DEFAULT NULL COMMENT '出库时间',
  `status` int(11) DEFAULT NULL,
  `order_type` int(11) DEFAULT NULL,
  `creater` varchar(20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `depot_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `customer_phone` varchar(50) DEFAULT NULL COMMENT '客户电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_order
-- ----------------------------

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `depot_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `depot_name` varchar(255) DEFAULT NULL COMMENT '仓库名称',
  `stock_num` int(11) DEFAULT NULL COMMENT '库存余量',
  `sale_num` int(11) DEFAULT NULL COMMENT '售出量',
  `sale_price` bigint(20) DEFAULT NULL COMMENT '售出价格',
  `purchase_price` bigint(20) DEFAULT NULL COMMENT '进价',
  `goods_image` varchar(255) DEFAULT NULL COMMENT '商品图',
  `stock_total` int(11) DEFAULT NULL COMMENT '库存总量',
  `creater` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `system_config_id` varchar(50) NOT NULL,
  `master_key` varchar(50) DEFAULT NULL COMMENT '主Key',
  `sub_key` varchar(50) DEFAULT NULL COMMENT '子Key',
  `config_key` varchar(50) DEFAULT NULL COMMENT 'Key',
  `key_description` varchar(50) DEFAULT NULL COMMENT 'Key描述',
  `config_value` varchar(50) DEFAULT NULL COMMENT '值',
  `need_cache` varchar(10) DEFAULT NULL COMMENT '是否需要加入redis缓存',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`system_config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('20190126140946000001001001', 'SYS_ID_scm', 'sys_user', '0001', '生成用户id', '012000', 'false', null, null, 'root');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_parent` bigint(20) DEFAULT NULL COMMENT '菜单父类',
  `is_child` tinyint(4) DEFAULT NULL COMMENT '是否是子节点0非',
  `status` int(11) DEFAULT NULL COMMENT '状态：0：可用 1：不可用',
  `creater` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '分类管理', '0', '0', '1', 'admin', '2019-01-29 13:38:50', null);
INSERT INTO `sys_menu` VALUES ('2', '商品管理', '0', '0', '1', 'admin', '2019-01-29 13:39:10', null);
INSERT INTO `sys_menu` VALUES ('3', '进货管理', '0', '0', '1', 'admin', '2019-01-29 13:43:06', null);
INSERT INTO `sys_menu` VALUES ('4', '出货管理', '0', '0', '1', 'admin', '2019-01-29 13:43:32', null);
INSERT INTO `sys_menu` VALUES ('5', '统计管理', '0', '0', '1', 'admin', '2019-01-29 13:43:32', null);
INSERT INTO `sys_menu` VALUES ('6', '账号管理', '0', '0', '1', 'admin', '2019-01-29 13:43:32', null);
INSERT INTO `sys_menu` VALUES ('7', '会员管理', '0', '0', '1', 'admin', '2019-01-29 13:43:32', null);
INSERT INTO `sys_menu` VALUES ('8', '系统管理', '0', '0', '1', 'admin', '2019-01-29 13:43:32', null);
INSERT INTO `sys_menu` VALUES ('9', '权限管理', '8', '1', '1', 'admin', '2019-01-29 13:47:11', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_des` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `menu_ids` varchar(255) DEFAULT NULL COMMENT '菜单id',
  `menu_names` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creater` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '超级管理员，拥有所有权限', null, null, '1', 'admin', '2019-01-29 13:03:39', null);
INSERT INTO `sys_role` VALUES ('2', '系统管理员', '系统管理员', '1,2,3,4,5,6,7', '分类管理,商品管理,进货管理,出货管理,统计管理,账号管理,会员管理', '1', 'admin', '2019-01-29 13:49:28', null);
INSERT INTO `sys_role` VALUES ('3', '商品管理员', '商品管理员', '1,2,3,4,5', '分类管理,商品管理,进货管理,出货管理,统计管理', '1', 'admin', '2019-01-29 13:50:43', null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `user_pwd` varchar(40) DEFAULT NULL COMMENT '用户密码',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `role_name` varchar(40) DEFAULT NULL COMMENT '角色名',
  `emp_id` bigint(20) DEFAULT NULL COMMENT '工号',
  `creater` varchar(40) DEFAULT NULL COMMENT '创建人',
  `create_at` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `is_locked` tinyint(4) DEFAULT NULL COMMENT '是否锁定 1未锁定',
  `locked_to` datetime DEFAULT NULL COMMENT '锁定至',
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('201901261409460000000001001001', 'root', 'root', '1', '超级管理员', '2', 'lpy', '2019-01-26 14:31:38', '2', '0', null, '2019-01-29 08:08:21');
INSERT INTO `sys_user` VALUES ('201901301526150000000001009001', 'lpy1993', '123456', '3', '商品管理员', '2', 'admin', '2019-01-30 07:26:16', '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('201901301545070000000001011001', 'lpy', '123456', '2', '系统管理员', '4', 'admin', '2019-01-30 07:45:08', '1', '0', null, null);
