/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : shikong

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-07-11 19:49:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chronos_comment
-- ----------------------------
DROP TABLE IF EXISTS `chronos_comment`;
CREATE TABLE `chronos_comment` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `question_id` varchar(64) DEFAULT NULL COMMENT '问题ID',
  `comment` text COMMENT '评论内容',
  `create_time` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(64) DEFAULT NULL COMMENT '创建用户',
  `is_del` int(1) DEFAULT '0',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后操作时间',
  `comment_id` varchar(64) DEFAULT NULL COMMENT '上级评论ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Records of chronos_comment
-- ----------------------------
INSERT INTO `chronos_comment` VALUES ('1', '0E9F0D5DD79A4AD9BE14DB125592861C', '这是评论内容', '', '1', '0', '2017-07-07 17:14:15', '');
INSERT INTO `chronos_comment` VALUES ('2', '', '这是评论内容Comment', '', '1', '0', '2017-07-07 17:16:59', '1');

-- ----------------------------
-- Table structure for chronos_question
-- ----------------------------
DROP TABLE IF EXISTS `chronos_question`;
CREATE TABLE `chronos_question` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `question` longtext COMMENT '问题内容',
  `user_id` varchar(255) DEFAULT NULL COMMENT '发现者',
  `create_time` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `sort` int(1) DEFAULT NULL COMMENT '排序',
  `is_review` varchar(2) DEFAULT '0' COMMENT '是否通过审核 0 未审核 10 已通过 -1未通过  1搁置',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `is_del` int(1) DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题表';

-- ----------------------------
-- Records of chronos_question
-- ----------------------------
INSERT INTO `chronos_question` VALUES ('0E9F0D5DD79A4AD9BE14DB125592861C', '问题内容', '1', '2017-07-04 16:56:12.240', '2017-07-11 19:44:10', '0', '0', '1', '0');
INSERT INTO `chronos_question` VALUES ('1CAB7AB2A27E45F694A9D39925E0DDAC', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 16:14:29.114', '2017-07-11 19:44:10', '1', '0', 'mockMvc???????', '0');
INSERT INTO `chronos_question` VALUES ('1DCF0989E90043B99A54AE8B67E81B9A', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 16:17:13.673', '2017-07-11 16:17:13', null, '0', 'mockMvc????', '0');
INSERT INTO `chronos_question` VALUES ('27A055FDA97F42AAAA43E2D98840AD6F', 'mockMvc review????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 19:44:33.598', '2017-07-11 19:44:33', null, '0', 'mockMvc review????', '0');
INSERT INTO `chronos_question` VALUES ('3184B85DDD38480D9B91FCAB08508DE7', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 16:15:42.188', '2017-07-11 16:15:42', null, '0', 'mockMvc?', '0');
INSERT INTO `chronos_question` VALUES ('4EBBDDAAA7944694BF8D42162627D0EA', '111', '1', '2017-07-04 16:54:35.606', '2017-07-04 16:54:35', null, '0', '1', '0');
INSERT INTO `chronos_question` VALUES ('52CC6A4DB66248A7AFFD1D9770C2B332', 'mockMvc review????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 16:39:12.260', '2017-07-11 19:44:33', null, '10', 'mockMvc review????', '0');
INSERT INTO `chronos_question` VALUES ('5604F646EEB34F64AA0FDCBED1C1B93C', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 16:09:00.014', '2017-07-11 16:09:00', null, '0', 'mockMvc????', '0');
INSERT INTO `chronos_question` VALUES ('578E3A6D8EBB4C28A0E7BBEF20C046C4', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 19:43:44.176', '2017-07-11 19:43:44', null, '0', 'mockMvc????', '0');
INSERT INTO `chronos_question` VALUES ('793AAEB5EC414DE497B12E75CBD8DB79', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 16:16:15.862', '2017-07-11 16:16:15', null, '0', 'mockMvc????', '0');
INSERT INTO `chronos_question` VALUES ('D3E9A33CE4FE4DADB7FAB69D453C5D7A', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 16:12:57.996', '2017-07-11 16:12:58', null, '0', 'mockMvc????', '0');
INSERT INTO `chronos_question` VALUES ('D90662A66B4743ED8DA464B4C99CDBA8', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 19:44:33.358', '2017-07-11 19:44:33', null, '0', 'mockMvc????', '0');
INSERT INTO `chronos_question` VALUES ('DE999986D9224D0F826FB94666D03FD7', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 16:30:27.823', '2017-07-11 16:30:27', null, '0', 'mockMvc????', '0');
INSERT INTO `chronos_question` VALUES ('FCE58EB78D394F63AD1DC050C54E68C4', 'mockMvc????', 'F00D6C67D7704AE2B9163945EC56BCC4', '2017-07-11 16:13:56.318', '2017-07-11 16:13:56', null, '0', 'mockMvc????', '0');

-- ----------------------------
-- Table structure for chronos_review
-- ----------------------------
DROP TABLE IF EXISTS `chronos_review`;
CREATE TABLE `chronos_review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `action` varchar(64) DEFAULT NULL COMMENT '审核操作  同意，拒绝，搁置等',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_del` int(2) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `admin_id` varchar(64) DEFAULT NULL,
  `question_id` varchar(64) DEFAULT NULL COMMENT '问题ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='审核记录';

-- ----------------------------
-- Records of chronos_review
-- ----------------------------
INSERT INTO `chronos_review` VALUES ('1', 'F00D6C67D7704AE2B9163945EC56BCC4', null, '2017-07-11 19:44:21', '2017-07-11 19:44:21', '0', '10', null, '52CC6A4DB66248A7AFFD1D9770C2B332');
INSERT INTO `chronos_review` VALUES ('2', 'F00D6C67D7704AE2B9163945EC56BCC4', null, null, null, '0', '0', null, '27A055FDA97F42AAAA43E2D98840AD6F');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户重要信息';

-- ----------------------------
-- Records of shikong_important
-- ----------------------------

-- ----------------------------
-- Table structure for shikong_user
-- ----------------------------
DROP TABLE IF EXISTS `shikong_user`;
CREATE TABLE `shikong_user` (
  `id` varchar(64) NOT NULL COMMENT 'ID主键',
  `alisa` varchar(128) NOT NULL COMMENT '别名，网名',
  `month` int(2) DEFAULT NULL COMMENT '月',
  `year` int(4) DEFAULT NULL COMMENT '年',
  `day` int(2) DEFAULT NULL COMMENT '日',
  `zodiac` varchar(20) DEFAULT NULL COMMENT '星座',
  `password` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `phone` varchar(50) NOT NULL COMMENT '电话',
  `addr_province` varchar(100) DEFAULT NULL COMMENT '省/直辖市',
  `addr_city` varchar(100) DEFAULT NULL COMMENT '市',
  `addr_county` varchar(100) DEFAULT NULL COMMENT '县/县级市',
  `addr_detail` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `is_del` int(3) DEFAULT '0' COMMENT '是否删除  0 未删除  -1 已删除  1 已冻结',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` varchar(255) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后操作时间',
  `status` int(2) DEFAULT '0' COMMENT '账号状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of shikong_user
-- ----------------------------
INSERT INTO `shikong_user` VALUES ('1', '1', '1', '1', '1', '1', null, '1', '1', '1', '1', '1', '0', '1', '1', '2017-06-16 04:03:18', null);
INSERT INTO `shikong_user` VALUES ('B206D10CEEAE419A9BBE516C7DFD68B9', '15869007701', null, null, null, null, '$2a$10$fgDC427dAwEElEdxeolyVuN.3tfnVNNPNvsBLYW/q9XXZg/9Xr7Eu', '15869007701', null, null, null, null, '0', null, '2017-07-04 14:48:59.015', '2017-07-04 14:48:59', '0');
INSERT INTO `shikong_user` VALUES ('F00D6C67D7704AE2B9163945EC56BCC4', '15869007707', null, null, null, null, '$2a$10$moH.rl5HEtf11lL5MGRv1elV4xMp7tQKqRdvJrOTgcqzGK9tIbuXO', '15869007707', null, null, null, null, '0', null, '2017-07-03 12:02:54.027', '2017-07-03 12:02:54', '10');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户实名表';

-- ----------------------------
-- Records of shikong_user_realname
-- ----------------------------
