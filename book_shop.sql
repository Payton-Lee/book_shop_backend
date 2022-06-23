/*
 Navicat Premium Data Transfer

 Source Server         : study
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : book_shop

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 21/06/2022 22:16:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sp_address
-- ----------------------------
DROP TABLE IF EXISTS `sp_address`;
CREATE TABLE `sp_address`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(0) NOT NULL COMMENT '外键用户id',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '市',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区（县）',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细地址',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人姓名',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人电话',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_sp_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_sp_user_id` FOREIGN KEY (`user_id`) REFERENCES `sp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sp_address
-- ----------------------------
INSERT INTO `sp_address` VALUES (1, 1, '贵州省', '安顺市', '西秀区', '万象花园', 'xiaomei', '18083399022');

-- ----------------------------
-- Table structure for sp_book
-- ----------------------------
DROP TABLE IF EXISTS `sp_book`;
CREATE TABLE `sp_book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图书名',
  `price` decimal(9, 2) NOT NULL COMMENT '价格',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `ISBN` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '国际标准书号',
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出版社',
  `in_stock` int(0) NOT NULL COMMENT '库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sp_book
-- ----------------------------
INSERT INTO `sp_book` VALUES (1, '北纬78°', 15.00, '陈丹燕', '9787513911139', '浙江文艺出版社', 200);
INSERT INTO `sp_book` VALUES (2, '100の东京大人味发现', 50.00, '吴东龙', '9787513911139', '民主与建设出版社', 3000);
INSERT INTO `sp_book` VALUES (3, '别走，万一好笑呢', 35.00, '银教授', '9787535477101', '长江文艺出版社', 200);
INSERT INTO `sp_book` VALUES (4, '此刻花开', 68.00, '徐静', '9787550019775', '百花洲文艺出版社', 160);

-- ----------------------------
-- Table structure for sp_book_image
-- ----------------------------
DROP TABLE IF EXISTS `sp_book_image`;
CREATE TABLE `sp_book_image`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `book_id` int(0) NOT NULL COMMENT '外键图书id',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图书图片名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_book_image_id`(`book_id`) USING BTREE,
  CONSTRAINT `fk_book_image_id` FOREIGN KEY (`book_id`) REFERENCES `sp_book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sp_book_image
-- ----------------------------
INSERT INTO `sp_book_image` VALUES (1, 1, 'beiwei78.jpg');
INSERT INTO `sp_book_image` VALUES (2, 2, '100dongjindarenweifaxian.jpg');
INSERT INTO `sp_book_image` VALUES (3, 3, 'biezouwanyihaoxiaone.jpg');
INSERT INTO `sp_book_image` VALUES (4, 4, 'cikehuakai.jpg');

-- ----------------------------
-- Table structure for sp_order
-- ----------------------------
DROP TABLE IF EXISTS `sp_order`;
CREATE TABLE `sp_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(0) NOT NULL COMMENT '下单的用户id',
  `order_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '下单的订单编号',
  `pay_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '支付编号',
  `book_id` int(0) NOT NULL COMMENT '购买商品的id',
  `count` int(0) UNSIGNED NOT NULL COMMENT '当前订单购买的商品数量',
  `order_price` decimal(9, 2) NOT NULL COMMENT '订单金额',
  `order_pay` tinyint(0) NOT NULL COMMENT '支付方式：0支付宝，1微信，2银行卡',
  `pay_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '订单付款情况，0未付款，1已付款',
  `is_send` tinyint(0) NOT NULL DEFAULT 0 COMMENT '0未发货，1已发货',
  `address_id` int(0) NOT NULL COMMENT '收货人id',
  `create_time` datetime(0) NOT NULL COMMENT '订单生成时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '订单修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_order_user_id`(`user_id`) USING BTREE,
  INDEX `fk_book_id`(`book_id`) USING BTREE,
  INDEX `fk_address_id`(`address_id`) USING BTREE,
  CONSTRAINT `fk_address_id` FOREIGN KEY (`address_id`) REFERENCES `sp_address` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_book_id` FOREIGN KEY (`book_id`) REFERENCES `sp_book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `sp_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sp_order
-- ----------------------------
INSERT INTO `sp_order` VALUES (1, 1, '12022062119481968591992950034853', '220621074810109', 1, 1, 15.00, 1, 2, 2, 1, '2022-06-20 22:11:08', NULL);
INSERT INTO `sp_order` VALUES (2, 2, '12022062119481968668063165472762', '220621074815141', 2, 1, 67.00, 1, 0, 0, 1, '2022-06-21 08:21:42', NULL);
INSERT INTO `sp_order` VALUES (3, 1, '4233280432', '220621074889057', 3, 1, 78.00, 1, 0, 0, 1, '2022-06-21 08:22:30', NULL);

-- ----------------------------
-- Table structure for sp_user
-- ----------------------------
DROP TABLE IF EXISTS `sp_user`;
CREATE TABLE `sp_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sp_user
-- ----------------------------
INSERT INTO `sp_user` VALUES (1, 'xiaoxiao', '$2a$10$CSocmr36ilu6DLyMrCf71.jsJsZY/DWbBFn4hkvCCjcFmYm/ttgqy', '20203243@qq.com', '女', '18083399022');
INSERT INTO `sp_user` VALUES (2, 'xiaoming', '$2a$10$3w9Usp9uXkm7wb.FWuthCOhyb81kLg0r7MQR1AwS8INGM8Psmjre2', '202847290@qq.com', '男', '12836712012');

SET FOREIGN_KEY_CHECKS = 1;
