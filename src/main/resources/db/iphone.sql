/*
 Navicat Premium Data Transfer

 Source Server         : mySql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : iphone

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 28/10/2020 00:11:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of model
-- ----------------------------
BEGIN;
INSERT INTO `model` VALUES (16, 'MGL93CH/A', 'iPhone 12 Pro-128G-黑色', 8499.00);
INSERT INTO `model` VALUES (17, 'MGLA3CH/A', 'iPhone 12 Pro-128G-白色', 8499.00);
INSERT INTO `model` VALUES (18, 'MGLC3CH/A', 'iPhone 12 Pro-128G-金色', 8499.00);
INSERT INTO `model` VALUES (19, 'MGLD3CH/A', 'iPhone 12 Pro-128G-蓝色', 8499.00);
INSERT INTO `model` VALUES (20, 'MGLE3CH/A', 'iPhone 12 Pro-256G-黑色', 9299.00);
INSERT INTO `model` VALUES (21, 'MGLF3CH/A', 'iPhone 12 Pro-256G-白色', 9299.00);
INSERT INTO `model` VALUES (22, 'MGLG3CH/A', 'iPhone 12 Pro-256G-金色', 9299.00);
INSERT INTO `model` VALUES (23, 'MGLH3CH/A', 'iPhone 12 Pro-256G-蓝色', 9299.00);
INSERT INTO `model` VALUES (24, 'MGLJ3CH/A', 'iPhone 12 Pro-512G-黑色', 11099.00);
INSERT INTO `model` VALUES (25, 'MGLK3CH/A', 'iPhone 12 Pro-512G-白色', 11099.00);
INSERT INTO `model` VALUES (26, 'MGLL3CH/A', 'iPhone 12 Pro-512G-金色', 11099.00);
INSERT INTO `model` VALUES (27, 'MGLM3CH/A', 'iPhone 12 Pro-512G-蓝色', 11099.00);
COMMIT;

-- ----------------------------
-- Table structure for stores
-- ----------------------------
DROP TABLE IF EXISTS `stores`;
CREATE TABLE `stores` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `storeNumber` varchar(10) DEFAULT NULL COMMENT '门店编号',
  `city` varchar(50) DEFAULT NULL COMMENT '所在城市',
  `storeName` varchar(50) DEFAULT NULL COMMENT '门店名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stores
-- ----------------------------
BEGIN;
INSERT INTO `stores` VALUES (1, 'R705', '上海', '七宝');
INSERT INTO `stores` VALUES (2, 'R320', '北京', '三里屯');
INSERT INTO `stores` VALUES (3, 'R401', '上海', '上海环贸 iapm');
INSERT INTO `stores` VALUES (4, 'R534', '沈阳', '中街大悦城');
INSERT INTO `stores` VALUES (5, 'R581', '上海', '五角场');
INSERT INTO `stores` VALUES (6, 'R479', '北京', '华贸购物中心');
INSERT INTO `stores` VALUES (7, 'R359', '上海', '南京东路');
INSERT INTO `stores` VALUES (8, 'R493', '南京', '南京艾尚天地');
INSERT INTO `stores` VALUES (9, 'R703', '南京', '南京金茂汇');
INSERT INTO `stores` VALUES (10, 'R571', '南宁', '南宁万象城');
INSERT INTO `stores` VALUES (11, 'R644', '厦门', '厦门新生活广场');
INSERT INTO `stores` VALUES (12, 'R609', '大连', '大连恒隆广场');
INSERT INTO `stores` VALUES (13, 'R531', '宁波', '天一广场');
INSERT INTO `stores` VALUES (14, 'R638', '天津', '天津万象城');
INSERT INTO `stores` VALUES (15, 'R637', '天津', '天津大悦城');
INSERT INTO `stores` VALUES (16, 'R579', '天津', '天津恒隆广场');
INSERT INTO `stores` VALUES (17, 'R577', '广州', '天环广场');
INSERT INTO `stores` VALUES (18, 'R502', '成都', '成都万象城');
INSERT INTO `stores` VALUES (19, 'R580', '成都', '成都太古里');
INSERT INTO `stores` VALUES (20, 'R574', '无锡', '无锡恒隆广场');
INSERT INTO `stores` VALUES (21, 'R670', '昆明', '昆明');
INSERT INTO `stores` VALUES (22, 'R645', '北京', '朝阳大悦城');
INSERT INTO `stores` VALUES (23, 'R532', '杭州', '杭州万象城');
INSERT INTO `stores` VALUES (24, 'R576', '沈阳', '沈阳万象城');
INSERT INTO `stores` VALUES (25, 'R646', '福州', '泰禾广场');
INSERT INTO `stores` VALUES (26, 'R648', '济南', '济南恒隆广场');
INSERT INTO `stores` VALUES (27, 'R389', '上海', '浦东');
INSERT INTO `stores` VALUES (28, 'R484', '深圳', '深圳益田假日广场');
INSERT INTO `stores` VALUES (29, 'R448', '北京', '王府井');
INSERT INTO `stores` VALUES (30, 'R683', '上海', '环球港');
INSERT INTO `stores` VALUES (31, 'R639', '广州', '珠江新城');
INSERT INTO `stores` VALUES (32, 'R478', '大连', '百年城');
INSERT INTO `stores` VALUES (33, 'R688', '苏州', '苏州');
INSERT INTO `stores` VALUES (34, 'R643', '南京', '虹悦城');
INSERT INTO `stores` VALUES (35, 'R388', '北京', '西单大悦城');
INSERT INTO `stores` VALUES (36, 'R471', '杭州', '西湖');
INSERT INTO `stores` VALUES (37, 'R480', '重庆', '解放碑');
INSERT INTO `stores` VALUES (38, 'R572', '郑州', '郑州万象城');
INSERT INTO `stores` VALUES (39, 'R573', '重庆', '重庆万象城');
INSERT INTO `stores` VALUES (40, 'R476', '重庆', '重庆北城天街');
INSERT INTO `stores` VALUES (41, 'R557', '青岛', '青岛万象城');
INSERT INTO `stores` VALUES (42, 'R390', '上海', '香港广场');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` int(2) DEFAULT NULL COMMENT '型号ID',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `status` int(2) DEFAULT '0' COMMENT '状态 1取消 0正常',
  `creationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 21, 'liujiguijava@163.com', '广州', 0, '2020-10-27 21:18:18');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
