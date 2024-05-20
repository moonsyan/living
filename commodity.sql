/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql
 Source Server Type    : MySQL
 Source Server Version : 80016 (8.0.16)
 Source Host           : 192.168.56.100:3306
 Source Schema         : hspliving_commodity

 Target Server Type    : MySQL
 Target Server Version : 80016 (8.0.16)
 File Encoding         : 65001

 Date: 20/05/2024 11:21:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commodity_attr
-- ----------------------------
DROP TABLE IF EXISTS `commodity_attr`;
CREATE TABLE `commodity_attr`  (
  `attr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '属性 id',
  `attr_name` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性名',
  `search_type` tinyint(4) NULL DEFAULT NULL COMMENT '是否需要检索[0-不需要，1-需要]',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `value_select` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '可选值列表[用逗号分隔]',
  `attr_type` tinyint(4) NULL DEFAULT NULL COMMENT '属性类型[0-销售属性，1-基本属性]',
  `ENABLE` bigint(20) NULL DEFAULT NULL COMMENT '启用状态[0 - 禁用，1 - 启用]',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分类',
  `show_desc` tinyint(4) NULL DEFAULT NULL COMMENT '快速展示【是否展示在介绍上；0-否 1-是】\r\n',
  PRIMARY KEY (`attr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_attr
-- ----------------------------
INSERT INTO `commodity_attr` VALUES (1, '是否触摸屏', 1, 'logo', '非触摸屏,触摸屏', 1, 1, 301, 1);
INSERT INTO `commodity_attr` VALUES (2, '显示类型', 1, 'logo', 'LED显示', 1, 1, 301, 1);
INSERT INTO `commodity_attr` VALUES (3, '型号', 1, 'logo', '55V1F-R', 1, 1, 301, 1);
INSERT INTO `commodity_attr` VALUES (4, '面积', NULL, 'logo', '100*100,200*300', 0, 1, 301, 1);
INSERT INTO `commodity_attr` VALUES (5, 'test', 1, '1', '1', 1, 1, 301, 1);
INSERT INTO `commodity_attr` VALUES (6, 'test2', 1, '1', '1', 1, 1, 301, 1);
INSERT INTO `commodity_attr` VALUES (7, '颜色', NULL, 'logo', '黑色,红色', 0, 1, 301, 1);

-- ----------------------------
-- Table structure for commodity_attr_attrgroup_relation
-- ----------------------------
DROP TABLE IF EXISTS `commodity_attr_attrgroup_relation`;
CREATE TABLE `commodity_attr_attrgroup_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attr_id` bigint(20) NULL DEFAULT NULL COMMENT '属性 id',
  `attr_group_id` bigint(20) NULL DEFAULT NULL COMMENT '属性分组 id',
  `attr_sort` int(11) NULL DEFAULT NULL COMMENT '属性组内排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性和商品属性组的关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_attr_attrgroup_relation
-- ----------------------------
INSERT INTO `commodity_attr_attrgroup_relation` VALUES (1, 1, 1, 0);
INSERT INTO `commodity_attr_attrgroup_relation` VALUES (13, 2, 2, NULL);
INSERT INTO `commodity_attr_attrgroup_relation` VALUES (14, 3, 3, NULL);
INSERT INTO `commodity_attr_attrgroup_relation` VALUES (15, 5, 1, NULL);
INSERT INTO `commodity_attr_attrgroup_relation` VALUES (16, 6, 1, NULL);

-- ----------------------------
-- Table structure for commodity_attrgroup
-- ----------------------------
DROP TABLE IF EXISTS `commodity_attrgroup`;
CREATE TABLE `commodity_attrgroup`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组名',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '说明',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组图标',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分类 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '家居商品属性分组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_attrgroup
-- ----------------------------
INSERT INTO `commodity_attrgroup` VALUES (1, '主体', 0, '主体说明', '', 301);
INSERT INTO `commodity_attrgroup` VALUES (2, '规格', 0, '规格说明', '', 301);
INSERT INTO `commodity_attrgroup` VALUES (3, '功能', 0, '功能说明', '', 301);
INSERT INTO `commodity_attrgroup` VALUES (5, '舒适性能', 0, '舒适性能', 'logo', 601);

-- ----------------------------
-- Table structure for commodity_brand
-- ----------------------------
DROP TABLE IF EXISTS `commodity_brand`;
CREATE TABLE `commodity_brand`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌名',
  `logo` varchar(1200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'logo',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '说明',
  `isShow` tinyint(4) NULL DEFAULT NULL COMMENT '显示',
  `first_letter` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '检索首字母',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '家居品牌' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_brand
-- ----------------------------
INSERT INTO `commodity_brand` VALUES (1, '华为', 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-03/fa0c32a1-e645-4208-855e-900ac099e6c5_heiya.jpg', '说明1', 1, 'H', 0);
INSERT INTO `commodity_brand` VALUES (2, '小米', 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-02/cd082464-6d9b-4244-8322-366a1625eaf6_drive.jpg', '小米', 1, 'M', 0);
INSERT INTO `commodity_brand` VALUES (6, 'oppo', 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-02/693591e1-adc0-4d0f-8f3d-62ca4d35fe1d_black.jpg', 'oppo', 1, 'o', 0);
INSERT INTO `commodity_brand` VALUES (7, 'view', 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-03/47db4d1a-3174-4f62-881c-a1c9a473f7b7_kawayi.jpg', 'vivo', 1, 'v', 0);

-- ----------------------------
-- Table structure for commodity_category
-- ----------------------------
DROP TABLE IF EXISTS `commodity_category`;
CREATE TABLE `commodity_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父分类 id',
  `cat_level` int(11) NOT NULL COMMENT '层级',
  `is_show` tinyint(4) NOT NULL COMMENT '0 不显示，1 显示]',
  `sort` int(11) NOT NULL COMMENT '排序',
  `icon` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图标',
  `pro_unit` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '统计单位',
  `pro_count` int(11) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 655 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_category
-- ----------------------------
INSERT INTO `commodity_category` VALUES (1, '家用电器', 0, 1, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (2, '家居家装', 0, 1, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (21, '大 家 电', 1, 2, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (22, '厨卫大电', 1, 2, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (41, '家纺', 2, 2, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (42, '灯具', 2, 2, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (201, '燃气灶', 22, 3, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (202, '油烟机', 22, 3, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (301, '平板电视', 21, 3, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (601, '桌布/罩件', 41, 3, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (602, '地毯地垫', 41, 3, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (651, '台灯', 42, 3, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (652, '节能灯', 42, 3, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (653, '小红灯', 42, 3, 1, 0, '图标1', '单位1', 0);
INSERT INTO `commodity_category` VALUES (654, '小明灯', 42, 3, 1, 0, '图标2', 'null', 0);

-- ----------------------------
-- Table structure for commodity_category_brand_relation
-- ----------------------------
DROP TABLE IF EXISTS `commodity_category_brand_relation`;
CREATE TABLE `commodity_category_brand_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌 id',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类 id',
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '品牌分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_category_brand_relation
-- ----------------------------
INSERT INTO `commodity_category_brand_relation` VALUES (1, 1, 301, '华为', '平板电视');
INSERT INTO `commodity_category_brand_relation` VALUES (2, 1, 201, '华为', '燃气灶');

-- ----------------------------
-- Table structure for commodity_product_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `commodity_product_attr_value`;
CREATE TABLE `commodity_product_attr_value`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `spu_id` bigint(20) NULL DEFAULT NULL COMMENT '商品 id',
  `attr_id` bigint(20) NULL DEFAULT NULL COMMENT '属性 id',
  `attr_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性名',
  `attr_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性值',
  `attr_sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `quick_show` tinyint(4) NULL DEFAULT NULL COMMENT '快速展示【是否展示在介绍上；0-否 1-是】\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'spu 基本属性值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_product_attr_value
-- ----------------------------
INSERT INTO `commodity_product_attr_value` VALUES (1, 6, 1, '是否触摸屏', '触摸屏', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (2, 6, 5, 'test', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (3, 6, 6, 'test2', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (4, 6, 2, '显示类型', 'LED显示', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (5, 6, 3, '型号', '55V1F-R', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (6, 7, 1, '是否触摸屏', '非触摸屏', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (7, 7, 5, 'test', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (8, 7, 6, 'test2', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (9, 7, 2, '显示类型', 'LED显示', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (10, 7, 3, '型号', '55V1F-R', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (11, 8, 1, '是否触摸屏', '非触摸屏', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (12, 8, 5, 'test', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (13, 8, 6, 'test2', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (14, 8, 2, '显示类型', 'LED显示', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (15, 8, 3, '型号', '55V1F-R', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (16, 9, 1, '是否触摸屏', '非触摸屏', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (17, 9, 5, 'test', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (18, 9, 6, 'test2', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (19, 9, 2, '显示类型', 'LED显示', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (20, 9, 3, '型号', '55V1F-R', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (21, 10, 1, '是否触摸屏', '非触摸屏', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (22, 10, 5, 'test', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (23, 10, 6, 'test2', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (24, 10, 2, '显示类型', 'LED显示', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (25, 10, 3, '型号', '55V1F-R', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (26, 11, 1, '是否触摸屏', '非触摸屏', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (27, 11, 5, 'test', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (28, 11, 6, 'test2', '1', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (29, 11, 2, '显示类型', 'LED显示', 0, 1);
INSERT INTO `commodity_product_attr_value` VALUES (30, 11, 3, '型号', '55V1F-R', 0, 1);

-- ----------------------------
-- Table structure for commodity_sku_images
-- ----------------------------
DROP TABLE IF EXISTS `commodity_sku_images`;
CREATE TABLE `commodity_sku_images`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT 'sku_id',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `img_sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `default_img` int(11) NULL DEFAULT NULL COMMENT '默认图[0 - 不是默认图，1 - 是默认图]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'sku 图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_sku_images
-- ----------------------------
INSERT INTO `commodity_sku_images` VALUES (1, 9, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (2, 9, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (3, 10, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (4, 10, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (5, 11, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (6, 11, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (7, 12, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (8, 12, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (9, 13, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/53b36d6a-0a3b-4915-8af5-5e228f0558c8_black.jpg', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (10, 13, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/5b7d7607-7598-4f40-8d09-83ade832677d_cat.jpg', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (11, 13, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/e9051421-0d09-4538-854b-919f5816b260_drive.jpg', 0, 1);
INSERT INTO `commodity_sku_images` VALUES (12, 13, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (13, 13, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (14, 13, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (15, 13, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (16, 13, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (17, 13, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (18, 14, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (19, 14, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (20, 14, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (21, 14, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (22, 14, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (23, 14, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (24, 14, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (25, 14, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (26, 14, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/e1bf149c-866c-47c0-8cfc-6a3398abcd2e_tuzi.jpg', 0, 1);
INSERT INTO `commodity_sku_images` VALUES (27, 15, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (28, 15, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (29, 15, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (30, 15, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (31, 15, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (32, 15, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (33, 15, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (34, 15, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/c6457f28-05ec-498e-822f-46e5ae621d53_qiang.jpg', 0, 1);
INSERT INTO `commodity_sku_images` VALUES (35, 15, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (36, 16, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (37, 16, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (38, 16, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (39, 16, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (40, 16, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/b1142c24-1f4b-417f-8869-3f602a51ef51_han.jpg', 0, 1);
INSERT INTO `commodity_sku_images` VALUES (41, 16, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (42, 16, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (43, 16, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (44, 16, '', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (45, 17, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/14020e4f-9cc1-4975-87c5-eb1687ba6981_keai.jpg', 0, 1);
INSERT INTO `commodity_sku_images` VALUES (46, 17, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/5c01a72f-6e1d-4d20-8c92-9fb19e5bb183_xie.jpg', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (47, 18, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/b2589630-b228-482c-85ce-52fe54f8e0c4_qiang.jpg', 0, 1);
INSERT INTO `commodity_sku_images` VALUES (48, 19, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/14020e4f-9cc1-4975-87c5-eb1687ba6981_keai.jpg', 0, 0);
INSERT INTO `commodity_sku_images` VALUES (49, 19, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/8216e69b-bd26-4e0f-8e87-9387d5a98e1d_drive.jpg', 0, 1);
INSERT INTO `commodity_sku_images` VALUES (50, 20, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/3dd4ebba-4647-4d82-8ae0-3538224d3d37_zhang.jpg', 0, 1);
INSERT INTO `commodity_sku_images` VALUES (51, 20, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/3da578ed-bb1d-4099-8b2d-ec515323ccea_xiong.jpg', 0, 0);

-- ----------------------------
-- Table structure for commodity_sku_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_sku_info`;
CREATE TABLE `commodity_sku_info`  (
  `sku_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'skuId',
  `spu_id` bigint(20) NULL DEFAULT NULL COMMENT 'spuId',
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'sku 名称',
  `sku_desc` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'sku 介绍描述',
  `catalog_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分类 id',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌 id',
  `sku_default_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '默认图片',
  `sku_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `sku_subtitle` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '副标题',
  `price` decimal(18, 4) NULL DEFAULT NULL COMMENT '价格',
  `sale_count` bigint(20) NULL DEFAULT NULL COMMENT '销量',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'sku 信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_sku_info
-- ----------------------------
INSERT INTO `commodity_sku_info` VALUES (1, 7, '华为电视06 100*100 红色', NULL, 301, 1, NULL, '华为电视06 100*100 红色', '副标题1', 10.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (2, 7, '华为电视06 100*100 黑色', NULL, 301, 1, NULL, '华为电视06 100*100 黑色', '副标题2', 20.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (3, 7, '华为电视06 200*300 红色', NULL, 301, 1, NULL, '华为电视06 200*300 红色', '副标题3', 30.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (4, 7, '华为电视06 200*300 黑色', NULL, 301, 1, NULL, '华为电视06 200*300 黑色', '副标题4', 40.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (5, 8, '华为电视07 200*300 黑色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg', '华为电视07 200*300 黑色', '副标题1', 10.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (6, 8, '华为电视07 200*300 红色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg', '华为电视07 200*300 红色', '副标题2', 20.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (7, 8, '华为电视07 100*100 黑色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg', '华为电视07 100*100 黑色', '副标题3', 30.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (8, 8, '华为电视07 100*100 红色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg', '华为电视07 100*100 红色', '副标题4', 40.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (9, 9, '华为电视08 200*300 黑色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg', '华为电视08 200*300 黑色', '副标题1', 10.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (10, 9, '华为电视08 200*300 红色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg', '华为电视08 200*300 红色', '副标题2', 20.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (11, 9, '华为电视08 100*100 黑色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg', '华为电视08 100*100 黑色', '副标题3', 330.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (12, 9, '华为电视08 100*100 红色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg', '华为电视08 100*100 红色', '副标题4', 40.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (13, 10, '华为电视11 200*300 黑色', NULL, 301, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/e9051421-0d09-4538-854b-919f5816b260_drive.jpg', '华为电视11 200*300 黑色', '副标题1', 10.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (14, 10, '华为电视11 200*300 红色', NULL, 301, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/e1bf149c-866c-47c0-8cfc-6a3398abcd2e_tuzi.jpg', '华为电视11 200*300 红色', '副标题2', 20.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (15, 10, '华为电视11 100*100 黑色', NULL, 301, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/c6457f28-05ec-498e-822f-46e5ae621d53_qiang.jpg', '华为电视11 100*100 黑色', '副标题3', 30.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (16, 10, '华为电视11 100*100 红色', NULL, 301, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/b1142c24-1f4b-417f-8869-3f602a51ef51_han.jpg', '华为电视11 100*100 红色', '副标题4', 40.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (17, 11, '华为电视test 100*100 黑色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/14020e4f-9cc1-4975-87c5-eb1687ba6981_keai.jpg', '华为电视test 100*100 黑色', '副标题1', 10.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (18, 11, '华为电视test 100*100 红色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/b2589630-b228-482c-85ce-52fe54f8e0c4_qiang.jpg', '华为电视test 100*100 红色', '副标题2', 20.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (19, 11, '华为电视test 200*300 黑色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/8216e69b-bd26-4e0f-8e87-9387d5a98e1d_drive.jpg', '华为电视test 200*300 黑色', '副标题3', 30.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (20, 11, '华为电视test 200*300 红色', NULL, 301, 1, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/3dd4ebba-4647-4d82-8ae0-3538224d3d37_zhang.jpg', '华为电视test 200*300 红色', '副标题4', 40.0000, 0);

-- ----------------------------
-- Table structure for commodity_sku_sale_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `commodity_sku_sale_attr_value`;
CREATE TABLE `commodity_sku_sale_attr_value`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT 'sku_id',
  `attr_id` bigint(20) NULL DEFAULT NULL COMMENT 'attr_id',
  `attr_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '销售属性名',
  `attr_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '销售属性值',
  `attr_sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'sku 的销售属性/值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_sku_sale_attr_value
-- ----------------------------
INSERT INTO `commodity_sku_sale_attr_value` VALUES (1, 17, 4, '面积', '100*100', 0);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (2, 17, 7, '颜色', '黑色', 0);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (3, 18, 4, '面积', '100*100', 0);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (4, 18, 7, '颜色', '红色', 0);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (5, 19, 4, '面积', '200*300', 0);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (6, 19, 7, '颜色', '黑色', 0);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (7, 20, 4, '面积', '200*300', 0);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (8, 20, 7, '颜色', '红色', 0);

-- ----------------------------
-- Table structure for commodity_spu_images
-- ----------------------------
DROP TABLE IF EXISTS `commodity_spu_images`;
CREATE TABLE `commodity_spu_images`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `spu_id` bigint(20) NULL DEFAULT NULL COMMENT 'spu_id',
  `img_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片名',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `img_sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `default_img` tinyint(4) NULL DEFAULT NULL COMMENT '是否默认图',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'spu 图片集' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_spu_images
-- ----------------------------
INSERT INTO `commodity_spu_images` VALUES (1, 4, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/4bf50d32-273e-4ca8-897e-3ed61c15ba05_1 (17).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (2, 4, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/6712e686-a1ff-49b1-8a2b-b8ddc9ce36d4_1 (16).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (3, 5, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg', NULL, 1);
INSERT INTO `commodity_spu_images` VALUES (4, 6, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/529489cc-5c82-446c-8ae6-ff6650d78a4b_1 (9).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (5, 6, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/a5b5c0cb-4b3b-40a4-8e7e-f4ab3b0bad93_1 (11).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (6, 7, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/335fbe45-9f5d-414f-885c-8da44e407164_1 (13).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (7, 7, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/f9a82603-b8a2-4026-86e0-d28c5211c732_1 (12).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (8, 8, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/2bd1ac3a-d30c-4e32-8eb8-224544656170_1 (57).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (9, 8, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/2ae6ed48-9067-49e7-82ed-9988eeccc10a_1 (37).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (10, 9, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/7b56663b-7ae0-4ca2-8ad9-2bddfd4d4141_1 (35).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (11, 9, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/f2f77a8b-567b-426a-8a01-3322f55de277_1 (34).jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (12, 10, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/53b36d6a-0a3b-4915-8af5-5e228f0558c8_black.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (13, 10, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/5b7d7607-7598-4f40-8d09-83ade832677d_cat.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (14, 10, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/e9051421-0d09-4538-854b-919f5816b260_drive.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (15, 10, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/477192b6-f33d-4f5d-8fef-da6f9d22a1fc_float.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (16, 10, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/b1142c24-1f4b-417f-8869-3f602a51ef51_han.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (17, 10, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/60e07dd4-c581-4e89-8283-fb5e478ba639_kawayi.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (18, 10, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/dc85dd90-e740-4632-8014-3945b83a6532_heiya.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (19, 10, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/c6457f28-05ec-498e-822f-46e5ae621d53_qiang.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (20, 10, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/e1bf149c-866c-47c0-8cfc-6a3398abcd2e_tuzi.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (21, 11, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/14020e4f-9cc1-4975-87c5-eb1687ba6981_keai.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (22, 11, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/b2589630-b228-482c-85ce-52fe54f8e0c4_qiang.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (23, 11, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/8216e69b-bd26-4e0f-8e87-9387d5a98e1d_drive.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (24, 11, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/3dd4ebba-4647-4d82-8ae0-3538224d3d37_zhang.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (25, 11, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/3da578ed-bb1d-4099-8b2d-ec515323ccea_xiong.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (26, 11, NULL, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/5c01a72f-6e1d-4d20-8c92-9fb19e5bb183_xie.jpg', NULL, NULL);

-- ----------------------------
-- Table structure for commodity_spu_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_spu_info`;
CREATE TABLE `commodity_spu_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品 id',
  `spu_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `spu_description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `catalog_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分类 id',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌 id',
  `weight` decimal(18, 4) NULL DEFAULT NULL,
  `publish_status` tinyint(4) NULL DEFAULT NULL COMMENT '上架状态[0 - 下架，1 - 上架]',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品 spu 信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_spu_info
-- ----------------------------
INSERT INTO `commodity_spu_info` VALUES (1, '华为电视01', '华为电视01描述', 301, 1, 1.0000, 1, '2024-05-13 15:31:32', '2024-05-14 00:40:32');
INSERT INTO `commodity_spu_info` VALUES (2, '华为电视02', '华为电视02描述', 301, 1, 0.3000, 2, '2024-05-13 15:49:03', '2024-05-14 00:47:47');
INSERT INTO `commodity_spu_info` VALUES (3, '华为电视03', '华为电视03', 301, 1, 0.8000, 1, '2024-05-13 15:52:02', '2024-05-13 15:52:02');
INSERT INTO `commodity_spu_info` VALUES (4, '华为电视04', '华为电视04', 301, 1, 0.7000, 1, '2024-05-13 16:10:50', '2024-05-13 16:10:50');
INSERT INTO `commodity_spu_info` VALUES (5, 'test', 'test', 301, NULL, 0.3000, 0, '2024-05-13 16:12:12', '2024-05-13 16:12:12');
INSERT INTO `commodity_spu_info` VALUES (6, '华为电视05', '华为电视05描述', 301, 1, 0.6000, 0, '2024-05-13 16:48:45', '2024-05-13 16:48:45');
INSERT INTO `commodity_spu_info` VALUES (7, '华为电视06', '华为电视06描述', 301, 1, 0.6000, 0, '2024-05-13 17:18:05', '2024-05-13 17:18:05');
INSERT INTO `commodity_spu_info` VALUES (8, '华为电视07', '华为电视07描述', 301, 1, 0.7000, 0, '2024-05-13 17:33:48', '2024-05-13 17:33:48');
INSERT INTO `commodity_spu_info` VALUES (9, '华为电视08', '华为电视08描述', 301, 1, 0.6000, 0, '2024-05-13 17:54:26', '2024-05-13 17:54:26');
INSERT INTO `commodity_spu_info` VALUES (10, '华为电视11', '华为电视11描述', 301, NULL, 0.3000, 1, '2024-05-13 17:58:20', '2024-05-13 17:58:20');
INSERT INTO `commodity_spu_info` VALUES (11, '华为电视test', '华为电视test描述', 301, 1, 0.4000, 1, '2024-05-13 18:19:46', '2024-05-13 18:19:46');

-- ----------------------------
-- Table structure for commodity_spu_info_desc
-- ----------------------------
DROP TABLE IF EXISTS `commodity_spu_info_desc`;
CREATE TABLE `commodity_spu_info_desc`  (
  `spu_id` bigint(20) NOT NULL COMMENT '商品 id',
  `decript` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '商品介绍图片',
  PRIMARY KEY (`spu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品 spu 信息介绍' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_spu_info_desc
-- ----------------------------
INSERT INTO `commodity_spu_info_desc` VALUES (3, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg');
INSERT INTO `commodity_spu_info_desc` VALUES (4, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg');
INSERT INTO `commodity_spu_info_desc` VALUES (5, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg');
INSERT INTO `commodity_spu_info_desc` VALUES (6, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/84c18228-6f54-45d9-824f-7de5ba0dda22_1 (1).jpg');
INSERT INTO `commodity_spu_info_desc` VALUES (7, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/831830c4-3ade-431e-88dd-1e50d7a5f463_1 (10).jpg');
INSERT INTO `commodity_spu_info_desc` VALUES (8, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/690a9e29-170f-472b-8747-1b085e3ffd36_1 (30).jpg');
INSERT INTO `commodity_spu_info_desc` VALUES (9, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/80e414be-5892-4b21-822f-54304547e90b_1 (32).jpg');
INSERT INTO `commodity_spu_info_desc` VALUES (10, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/92bd163e-b30c-4cb6-8f7f-097dd64ffc54_1 (37).jpg');
INSERT INTO `commodity_spu_info_desc` VALUES (11, 'https://hspliving-10001.oss-cn-beijing.aliyuncs.com/2024-05-13/6d3c6329-1332-4749-8fe6-67cbefd69090_wa.jpg');

SET FOREIGN_KEY_CHECKS = 1;
