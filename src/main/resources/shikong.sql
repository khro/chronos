/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : shikong

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-07-03 15:02:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shikong_important
-- ----------------------------
DROP TABLE IF EXISTS `shikong_important`;
CREATE TABLE `shikong_important` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `alipay` varchar(20) DEFAULT NULL COMMENT '支付宝',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shikong_important
-- ----------------------------

-- ----------------------------
-- Table structure for shikong_user
-- ----------------------------
DROP TABLE IF EXISTS `shikong_user`;
CREATE TABLE `shikong_user` (
  `id` varchar(255) NOT NULL COMMENT 'ID主键',
  `alisa` varchar(50) NOT NULL COMMENT '别名，网名',
  `month` int(2) DEFAULT NULL COMMENT '月',
  `year` int(4) DEFAULT NULL COMMENT '年',
  `day` int(2) DEFAULT NULL COMMENT '日',
  `zodiac` varchar(20) DEFAULT NULL COMMENT '星座',
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `phone` varchar(50) NOT NULL COMMENT '电话',
  `addr_province` varchar(100) DEFAULT NULL COMMENT '省/直辖市',
  `addr_city` varchar(100) DEFAULT NULL COMMENT '市',
  `addr_county` varchar(100) DEFAULT NULL COMMENT '县/县级市',
  `addr_detail` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `is_del` int(3) DEFAULT '0' COMMENT '是否删除  0 未删除  -1 已删除  1 已冻结',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` varchar(255) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后操作时间',
  `status` varchar(11) DEFAULT '0' COMMENT '账号状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shikong_user
-- ----------------------------
INSERT INTO `shikong_user` VALUES ('1', '1', '1', '1', '1', '1', null, '1', '1', '1', '1', '1', '0', '1', '1', '2017-06-16 04:03:18', null);
INSERT INTO `shikong_user` VALUES ('F00D6C67D7704AE2B9163945EC56BCC4', '15869007707', null, null, null, null, '$2a$10$moH.rl5HEtf11lL5MGRv1elV4xMp7tQKqRdvJrOTgcqzGK9tIbuXO', '15869007707', null, null, null, null, '0', null, '2017-07-03 12:02:54.027', '2017-07-03 12:02:54', '0');

-- ----------------------------
-- Table structure for shikong_user_realname
-- ----------------------------
DROP TABLE IF EXISTS `shikong_user_realname`;
CREATE TABLE `shikong_user_realname` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `user_id` varchar(255) NOT NULL COMMENT 'UserId ',
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证',
  `name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `is_real` int(1) DEFAULT '1' COMMENT '是否实名 1 未实名 0 已实名 ',
  `real_channel` varchar(20) DEFAULT NULL COMMENT '实名渠道',
  `real_name_time` varchar(50) DEFAULT NULL COMMENT '实名时间',
  `is_del` int(1) DEFAULT '0' COMMENT '0 未删除 1 已删除  ',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shikong_user_realname
-- ----------------------------
