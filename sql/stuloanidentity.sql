/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50635
Source Host           : 127.0.0.1:3307
Source Database       : stuloanidentity

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-04-24 23:50:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for creditscore
-- ----------------------------
DROP TABLE IF EXISTS `creditscore`;
CREATE TABLE `creditscore` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `userid` varchar(64) DEFAULT '',
  `stuidcard` varchar(64) DEFAULT '' COMMENT '用户id',
  `creditscoretotal` decimal(18,2) DEFAULT '0.00' COMMENT '信用总分',
  `identityscore` decimal(18,2) DEFAULT '0.00',
  `performscore` decimal(18,2) DEFAULT '0.00' COMMENT '履约分',
  `historyscore` decimal(18,2) DEFAULT '0.00' COMMENT '历史信用分',
  `peoplekeepscore` decimal(18,2) DEFAULT '0.00' COMMENT '人脉分',
  `behaviorscore` decimal(18,2) DEFAULT '0.00' COMMENT '行为分',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of creditscore
-- ----------------------------

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
  `class` varchar(50) DEFAULT '' COMMENT '班级',
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
