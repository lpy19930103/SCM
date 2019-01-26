CREATE TABLE `sys_menu` (
`id` bigint NOT NULL AUTO_INCREMENT,
`menu_name` varchar(255) NULL COMMENT '菜单名称',
`menu_parent` bigint NULL COMMENT '菜单父类',
`is_child` tinyint NULL COMMENT '是否是子节点0非',
`status` int NULL COMMENT '状态：0：可用 1：不可用',
`creater` varchar(255) NULL COMMENT '创建人',
`create_at` datetime NULL,
`update_at` datetime NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `sys_user` (
`id` bigint NOT NULL,
`user_name` varchar(20) NULL COMMENT '用户名',
`user_pwd` varchar(40) NULL COMMENT '用户密码',
`role_id` bigint NULL COMMENT '角色id',
`role_name` varchar(40) NULL COMMENT '角色名',
`emp_id` bigint NULL COMMENT '工号',
`creater` varchar(40) NULL COMMENT '创建人',
`create_at` datetime NULL,
`status` int NULL,
`is_locked` tinyint NULL COMMENT '是否锁定 1未锁定',
`locked_to` datetime NULL COMMENT '锁定至',
`update_at` datetime NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `sys_role` (
`id` bigint NOT NULL AUTO_INCREMENT,
`role_name` varchar(255) NULL COMMENT '角色名称',
`role_des` varchar(255) NULL COMMENT '角色描述',
`menu_ids` varchar(255) NULL COMMENT '菜单id',
`menu_names` varchar(255) NULL,
`status` int NULL,
`creater` varchar(255) NULL,
`create_at` datetime NULL,
`update_at` datetime NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `base_goods` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
`goods_name` varchar(50) CHARACTER SET utf8 NULL COMMENT '商品名称',
`goods_code` varchar(30) NULL COMMENT '商品编码',
`goods_category` varchar(50) NULL COMMENT '商品品类',
`brand` varchar(50) NULL COMMENT '品牌',
`unit` varchar(20) NULL COMMENT '计量单位',
`goods_color` varchar(20) NULL COMMENT '颜色',
`material` varchar(255) NULL COMMENT '材质',
`purchase_price` bigint NULL COMMENT '进价',
`sale_price` bigint NULL COMMENT '售价',
`create_at` datetime NULL COMMENT '创建时间',
`update_at` datetime NULL COMMENT '更新时间',
`status` int NULL COMMENT '商品状态0:可售1:不可售2:缺货',
`creater` varchar(30) NULL COMMENT '创建人',
`goods_image` varchar(255) NULL COMMENT '商品图片',
PRIMARY KEY (`id`) 
)
COMMENT='商品';

CREATE TABLE `base_category` (
`id` bigint NOT NULL AUTO_INCREMENT,
`category_name` varchar(255) NULL COMMENT '品类名称',
`category_unit` varchar(255) NULL COMMENT '计量单位',
`material` varchar(255) NULL COMMENT '材质',
`parent_id` bigint NULL COMMENT '父品类',
`creater` varchar(255) NULL,
`create_at` datetime NULL,
`update_at` datetime NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `base_depot` (
`id` bigint NOT NULL AUTO_INCREMENT,
`depot_name` varchar(50) NULL COMMENT '仓库名',
`depot_address` varchar(70) NULL COMMENT '仓库地址',
`depot_code` bigint NULL COMMENT '仓库编码',
`admin_id` int NULL COMMENT '管理员id',
`creater` varchar(30) NULL COMMENT '创建人',
`create_at` datetime NULL,
`update_at` datetime NULL,
`depot_des` varchar(255) NULL COMMENT '仓库描述',
`status` int NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `base_employee` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '工号',
`emp_name` varchar(255) NULL,
`emp_id_card` varchar(255) NULL,
`emp_phone` varchar(255) NULL,
`emp_email` varchar(255) NULL,
`sex` tinyint NULL COMMENT '1男 2女',
`birthday` date NULL,
`address` varchar(255) NULL,
`emp_type` tinyint NULL,
`creater` varchar(255) NULL,
`create_at` datetime NULL,
`update_at` datetime NULL,
`status` int NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `purchase_order` (
`id` bigint NOT NULL AUTO_INCREMENT,
`supplier_id` bigint NULL COMMENT '供货商id',
`supplier_name` varchar(50) NULL COMMENT '供货商名',
`depot_id` bigint NULL COMMENT '仓库id',
`emp_id` bigint NULL COMMENT '员工id',
`purchase_des` varchar(255) NULL COMMENT '进货描述',
`review_status` tinyint NULL COMMENT '1审核通过，2审核失败审核状态',
`review_name` varchar(255) NULL COMMENT '审核人',
`review_at` datetime NULL COMMENT '审核时间',
`storage_at` datetime NULL COMMENT '入库时间',
`goods_total` bigint(255) NULL COMMENT '商品总价',
`status` tinyint NULL COMMENT '订单状态',
`order_type` tinyint NULL COMMENT '订单类型',
`creater` varchar(20) NULL,
`create_at` datetime NULL,
`update_at` datetime NULL,
`supplier_phone` varchar(50) NULL COMMENT '供货商电话',
PRIMARY KEY (`id`) 
);

CREATE TABLE `purchase_supplier` (
`id` bigint NOT NULL AUTO_INCREMENT,
`sup_name` varchar(255) NULL COMMENT '供应商名称',
`contact_address` varchar(255) NULL COMMENT '联系地址',
`contact_phone` varchar(255) NULL COMMENT '联系电话',
`contact_name` varchar(255) NULL COMMENT '联系人',
`sup_des` varchar(255) NULL COMMENT '供应商描述',
`status` tinyint NULL,
`creater` varchar(255) NULL,
`create_at` datetime NULL,
`update_at` datetime NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `sale_customer` (
`id` bigint NOT NULL AUTO_INCREMENT,
`cus_name` varchar(255) NULL COMMENT '客户名',
`contact_name` varchar(255) NULL COMMENT '联系人',
`contact_address` varchar(255) NULL COMMENT '联系地址',
`contact_phone` varchar(255) NULL COMMENT '联系电话',
`cus_des` varchar(255) NULL COMMENT '客户描述',
`status` varchar(255) NULL,
`creater` varchar(255) NULL,
`create_at` datetime NULL,
`update_at` datetime NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `sale_order` (
`id` bigint NOT NULL AUTO_INCREMENT,
`goods_total` bigint NULL COMMENT '商品总价',
`customer_id` bigint NULL COMMENT '客户id',
`customer_name` varchar(20) NULL COMMENT '客户名',
`emp_id` bigint NULL COMMENT '员工id',
`sale_des` varchar(255) NULL,
`review_status` int NULL COMMENT '审核状态',
`review_name` varchar(20) NULL COMMENT '审核人',
`review_at` datetime NULL COMMENT '审核时间',
`out_time` datetime NULL COMMENT '出库时间',
`status` int NULL,
`order_type` int NULL,
`creater` varchar(20) NULL,
`create_at` datetime NULL,
`update_at` datetime NULL,
`depot_id` bigint NULL COMMENT '仓库id',
`customer_phone` varchar(50) NULL COMMENT '客户电话',
PRIMARY KEY (`id`) 
);

CREATE TABLE `stock` (
`id` bigint NOT NULL AUTO_INCREMENT,
`depot_id` bigint NULL COMMENT '仓库id',
`goods_id` bigint NULL COMMENT '商品id',
`goods_name` varchar(255) NULL COMMENT '商品名称',
`depot_name` varchar(255) NULL COMMENT '仓库名称',
`stock_num` int NULL COMMENT '库存余量',
`sale_num` int NULL COMMENT '售出量',
`sale_price` bigint NULL COMMENT '售出价格',
`purchase_price` bigint NULL COMMENT '进价',
`goods_image` varchar(255) NULL COMMENT '商品图',
`stock_total` int NULL COMMENT '库存总量',
`creater` varchar(255) NULL,
`create_at` datetime NULL,
`update_at` datetime NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `order_item` (
`id` bigint NOT NULL AUTO_INCREMENT,
`order_id` bigint NULL,
`goods_id` bigint NULL,
`goods_name` varchar(255) NULL,
`goods_unit` varchar(255) NULL,
`goods_price` bigint(10) NULL,
`goods_image` varchar(255) NULL,
`goods_num` int NULL,
`goods_total` bigint(255) NULL,
PRIMARY KEY (`id`) 
);

