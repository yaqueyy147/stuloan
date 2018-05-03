/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50634
Source Host           : localhost:3307
Source Database       : stuloanidentity

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2018-05-03 17:33:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for creditscore
-- ----------------------------
DROP TABLE IF EXISTS `creditscore`;
CREATE TABLE `creditscore` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `userid` varchar(64) DEFAULT '',
  `alipayname` varchar(100) DEFAULT '' COMMENT '用户id',
  `creditscoretotal` decimal(18,2) DEFAULT '0.00' COMMENT '信用总分',
  `identityscore` decimal(18,2) DEFAULT '0.00',
  `performscore` decimal(18,2) DEFAULT '0.00' COMMENT '履约分',
  `historyscore` decimal(18,2) DEFAULT '0.00' COMMENT '历史信用分',
  `peoplekeepscore` decimal(18,2) DEFAULT '0.00' COMMENT '人脉分',
  `behaviorscore` decimal(18,2) DEFAULT '0.00' COMMENT '行为分',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  `state` char(1) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of creditscore
-- ----------------------------
INSERT INTO `creditscore` VALUES ('1', '4f0fde82c40e443195449ef366295422', '', '1000.00', '200.00', '200.00', '200.00', '200.00', '200.00', null, '', '0');

-- ----------------------------
-- Table structure for studentroll
-- ----------------------------
DROP TABLE IF EXISTS `studentroll`;
CREATE TABLE `studentroll` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `userid` varchar(64) DEFAULT '',
  `stuname` varchar(255) DEFAULT '' COMMENT '姓名',
  `stusex` char(1) DEFAULT '0' COMMENT '性别：0、女；1、男',
  `stuidcard` varchar(18) DEFAULT '' COMMENT '身份号',
  `school` varchar(500) DEFAULT '' COMMENT '学习',
  `edulevel` varchar(20) DEFAULT '' COMMENT '学历层次：专科、本科、硕士、博士',
  `major` varchar(255) DEFAULT '' COMMENT '专业',
  `edusystem` varchar(10) DEFAULT '' COMMENT '学制',
  `edutype` varchar(50) DEFAULT '' COMMENT '学历类别',
  `edumode` varchar(255) DEFAULT '' COMMENT '学习形式：如普通全日制',
  `branch` varchar(255) DEFAULT '' COMMENT '分院',
  `classgrade` varchar(50) DEFAULT '' COMMENT '班级',
  `stunum` varchar(50) DEFAULT '' COMMENT '学号',
  `admissiondate` date DEFAULT NULL COMMENT '入学时间',
  `leavedate` date DEFAULT NULL COMMENT '离校时间',
  `stustate` char(1) DEFAULT '0' COMMENT '学籍状态：0、不在籍；1、在籍...',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  `isstuidentity` char(1) DEFAULT '0' COMMENT '是否已通过学生认证:1、已认证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentroll
-- ----------------------------
INSERT INTO `studentroll` VALUES ('1', '', '测试01', '1', '111111111111111111', '重庆邮电大学', '本科', '测控技术与仪器', '4', '普通', '普通全日制', '自动化学院', '0820803', '08210312', '2008-09-01', '2012-07-01', '0', '', '0');
INSERT INTO `studentroll` VALUES ('2', '', '测试02', '1', '111111111111111111', '重庆大学', '本科', '自动化', '4', '普通', '普通全日制', '自动化学院', '0820804', '08210322', '2008-09-01', '2012-07-01', '0', '', '0');
