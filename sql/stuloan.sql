/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50634
Source Host           : localhost:3307
Source Database       : stuloan

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2018-05-07 19:53:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blacklist
-- ----------------------------
DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `userid` varchar(64) NOT NULL DEFAULT '' COMMENT '用户id',
  `overduecount` int(11) DEFAULT '0' COMMENT '逾期次数',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `createid` varchar(64) DEFAULT '' COMMENT '创建人id',
  `createname` varchar(100) DEFAULT '' COMMENT '创建人账号',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blacklist
-- ----------------------------

-- ----------------------------
-- Table structure for creditmoney
-- ----------------------------
DROP TABLE IF EXISTS `creditmoney`;
CREATE TABLE `creditmoney` (
  `id` varchar(64) NOT NULL,
  `creditlevel` int(11) DEFAULT '0' COMMENT '信用分等级',
  `thresholdscore` decimal(18,2) DEFAULT '0.00' COMMENT '信用分阈值',
  `loanmoney` decimal(18,2) DEFAULT '0.00' COMMENT '最大可贷款金额',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of creditmoney
-- ----------------------------
INSERT INTO `creditmoney` VALUES ('67fd630bc3d04c2cafe0088d09dda824', '1', '500.00', '50000.00', null);

-- ----------------------------
-- Table structure for creditscore
-- ----------------------------
DROP TABLE IF EXISTS `creditscore`;
CREATE TABLE `creditscore` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `userid` varchar(64) DEFAULT '' COMMENT '用户id',
  `alipayname` varchar(100) DEFAULT '',
  `creditscoretotal` decimal(18,2) DEFAULT '0.00' COMMENT '信用总分',
  `identityscore` decimal(18,2) DEFAULT '0.00',
  `performscore` decimal(18,2) DEFAULT '0.00' COMMENT '履约分',
  `historyscore` decimal(18,2) DEFAULT '0.00' COMMENT '历史信用分',
  `peoplekeepscore` decimal(18,2) DEFAULT '0.00' COMMENT '人脉分',
  `behaviorscore` decimal(18,2) DEFAULT '0.00' COMMENT '行为分',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  `state` char(1) DEFAULT '0' COMMENT '状态：1、已通过认证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of creditscore
-- ----------------------------
INSERT INTO `creditscore` VALUES ('1', '4f0fde82c40e443195449ef366295422', '', '1000.00', '200.00', '200.00', '200.00', '200.00', '200.00', null, '', '0');

-- ----------------------------
-- Table structure for loan
-- ----------------------------
DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `userid` varchar(64) DEFAULT '' COMMENT '用户id,学生id',
  `loanamount` decimal(18,2) DEFAULT '0.00' COMMENT '贷款金额',
  `isstage` char(1) DEFAULT '0' COMMENT '是否分期',
  `repayreal` decimal(18,2) DEFAULT '0.00' COMMENT '实际要还款总额',
  `stagenum` int(11) DEFAULT '0' COMMENT '分期期数',
  `repayyet` decimal(18,2) DEFAULT '0.00' COMMENT '已还款金额',
  `stagenumyet` int(11) DEFAULT '0' COMMENT '已还款期数',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间，贷款时间',
  `updatedate` datetime DEFAULT NULL COMMENT '修改时间，最后还款时间',
  `state` char(1) DEFAULT '0' COMMENT '放款状态：0、待审核；1、已同意放款；2、不同意',
  `auditid` varchar(64) DEFAULT '' COMMENT '审核人id',
  `auditman` varchar(255) DEFAULT '' COMMENT '审核人name',
  `auditdate` datetime DEFAULT NULL COMMENT '审核时间',
  `auditmsg` varchar(255) DEFAULT '' COMMENT '审核意见',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  `loanpurpose` varchar(255) DEFAULT '' COMMENT '贷款用途',
  `loanage` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '贷款期限：月数',
  `ispayoff` char(1) DEFAULT '0' COMMENT '是否已还清：1、是',
  `orderno` varchar(100) DEFAULT '' COMMENT '订单号，关联订单表orderno',
  `loanoutdate` datetime DEFAULT NULL COMMENT '放款时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loan
-- ----------------------------
INSERT INTO `loan` VALUES ('0adea96807d14ad4a3a27c544e211fc4', '4f0fde82c40e443195449ef366295422', '1000.00', '1', '1030.00', '1', '0.00', null, '2018-05-07 15:10:17', null, '0', null, null, null, null, null, '其味无穷', '00000000001', null, null, null);
INSERT INTO `loan` VALUES ('1b12c57b437244d08eb9419c10806984', '4f0fde82c40e443195449ef366295422', '11111.00', '1', '11666.55', '5', '0.00', null, '2018-04-22 16:28:40', null, '1', '999', '系统管理员', '2018-04-22 18:04:32', '同意贷款', null, '买包包', '00000000005', null, '', '2018-04-22 18:04:32');
INSERT INTO `loan` VALUES ('30541cf1be774cf5a595ce033ca329a2', '4f0fde82c40e443195449ef366295422', '1000.00', '1', '1030.00', '1', '0.00', null, '2018-05-07 15:03:17', null, '0', null, null, null, null, null, '啛啛喳喳', '00000000001', null, null, null);
INSERT INTO `loan` VALUES ('c25b5bdd22c84ed888faf12f66ca7873', '4f0fde82c40e443195449ef366295422', '11111.00', '1', '11666.55', '5', '0.00', null, '2018-04-22 16:28:02', null, '1', '999', '系统管理员', '2018-05-05 15:28:33', '同意贷款,待放款', null, '买包包', '00000000005', null, 'loan_aed55efd71f440679fd8d424266118ef', '2018-05-05 17:50:03');
INSERT INTO `loan` VALUES ('d1710ce208d9423c9ea0aa0ad3b54bff', '4f0fde82c40e443195449ef366295422', '11111.00', '1', '11777.66', '9', '9160.41', '7', '2018-04-22 16:22:58', '2018-04-22 17:44:58', '5', '999', '系统管理员', '2018-04-22 17:07:01', '已同意，待放款', null, '买手机', '00000000009', null, '', '2018-05-07 10:50:09');

-- ----------------------------
-- Table structure for loanorder
-- ----------------------------
DROP TABLE IF EXISTS `loanorder`;
CREATE TABLE `loanorder` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `orderno` varchar(100) DEFAULT '' COMMENT '放款时生成的支付宝订单号',
  `orderqrimage` varchar(500) DEFAULT '' COMMENT '订单支付二维码',
  `orderstate` char(1) DEFAULT '0' COMMENT '支付状态：1、已支付',
  `paydate` datetime DEFAULT NULL COMMENT '支付时间',
  `createdate` datetime DEFAULT NULL COMMENT '订单生成时间',
  `orderdesc` varchar(500) DEFAULT '' COMMENT '订单说明',
  `ordertitle` varchar(255) DEFAULT '' COMMENT '订单标题',
  `totalamount` decimal(18,2) DEFAULT '0.00' COMMENT '订单金额',
  `timeoutexpress` varchar(100) DEFAULT '',
  `sellerid` varchar(50) DEFAULT '',
  `operatorid` varchar(50) DEFAULT '',
  `storeid` varchar(200) DEFAULT '',
  `undiscountableamount` varchar(255) DEFAULT '',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loanorder
-- ----------------------------
INSERT INTO `loanorder` VALUES ('2288cd81ea7148228eccddf23310f360', 'loan_2288cd81ea7148228eccddf23310f360', '/src/main/webapp/static/alipay/loan/qr-%s.png', '0', null, '2018-05-01 18:20:49', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('397ca24c56ff4e1ba864f4c6b4cb59b6', 'loan_397ca24c56ff4e1ba864f4c6b4cb59b6', '', '0', null, '2018-05-01 17:14:18', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('3be86f312d1b4846a021fe427871ab1b', 'loan_3be86f312d1b4846a021fe427871ab1b', '', '0', null, '2018-05-01 17:23:11', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('58065066372645ce81e367c7793f5287', 'loan_58065066372645ce81e367c7793f5287', '', '0', null, '2018-05-01 17:21:55', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('5824671a24404f9981dbe50759c67824', 'loan_5824671a24404f9981dbe50759c67824', '', '0', null, '2018-05-01 17:10:38', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('691639749a6445a1ae3b2104d738bbf4', 'loan_691639749a6445a1ae3b2104d738bbf4', '', '0', null, '2018-05-01 17:30:26', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('81290f93b4f24bdd8c893b0204694f93', 'loan_81290f93b4f24bdd8c893b0204694f93', '/static/alipay/loan/qr-loan_81290f93b4f24bdd8c893b0204694f93.png', '0', null, '2018-05-03 23:04:53', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('89fe3219bc0445128dee6de9b01836b3', 'loan_89fe3219bc0445128dee6de9b01836b3', '', '0', null, '2018-05-01 17:19:53', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('8a868e7ce7b74fbfaa291ebaf51c93ab', 'loan_8a868e7ce7b74fbfaa291ebaf51c93ab', '/static/alipay/loan/qr-loan_8a868e7ce7b74fbfaa291ebaf51c93ab.png', '0', null, '2018-05-03 09:49:55', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('8fd36519142a4669a039a42d651dd898', 'loan_8fd36519142a4669a039a42d651dd898', '/static/alipay/loan/qr-loan_8fd36519142a4669a039a42d651dd898.png', '0', null, '2018-05-03 23:03:22', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('9de0682842e645e0879e537c39977d30', 'loan_9de0682842e645e0879e537c39977d30', '/src/main/webapp/static/alipay/loan/qr-loan_9de0682842e645e0879e537c39977d30.png', '0', null, '2018-05-01 18:28:15', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('a2b8c90ed056434e9724bc4e4c5c8b71', 'loan_a2b8c90ed056434e9724bc4e4c5c8b71', '', '0', null, '2018-05-01 18:16:22', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('aed55efd71f440679fd8d424266118ef', 'loan_aed55efd71f440679fd8d424266118ef', '/static/alipay/loan/qr-loan_aed55efd71f440679fd8d424266118ef.png', '0', null, '2018-05-05 15:28:33', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('af89db8602894342b3658f707f553201', 'loan_af89db8602894342b3658f707f553201', '/static/alipay/loan/qr-loan_af89db8602894342b3658f707f553201.png', '0', null, '2018-05-01 18:29:00', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('d835fa4cd51347e88bfb640d21488a5c', 'loan_d835fa4cd51347e88bfb640d21488a5c', '', '0', null, '2018-05-01 17:35:40', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('e59fa2da8d45400a897dfd7a4ff3c100', 'loan_e59fa2da8d45400a897dfd7a4ff3c100', '', '0', null, '2018-05-01 17:24:17', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');
INSERT INTO `loanorder` VALUES ('ea289e6d69de4af8888d05b056debf9d', 'loan_ea289e6d69de4af8888d05b056debf9d', '', '0', null, '2018-05-01 17:35:40', '测试01的贷款,贷款金额(11111.0),用途:买包包', 'XXX校园贷扫码放款', '11111.00', '', '', '', '', '', '');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `orderno` varchar(100) DEFAULT '' COMMENT '放款时生成的支付宝订单号',
  `orderqrimage` varchar(500) DEFAULT '' COMMENT '订单支付二维码',
  `orderstate` char(1) DEFAULT '0' COMMENT '支付状态：1、已支付',
  `paydate` datetime DEFAULT NULL COMMENT '支付时间',
  `createdate` datetime DEFAULT NULL COMMENT '订单生成时间',
  `orderdesc` varchar(500) DEFAULT '' COMMENT '订单说明',
  `ordertitle` varchar(255) DEFAULT '' COMMENT '订单标题',
  `totalamount` decimal(18,2) DEFAULT '0.00' COMMENT '订单金额',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for repaydetail
-- ----------------------------
DROP TABLE IF EXISTS `repaydetail`;
CREATE TABLE `repaydetail` (
  `id` varchar(64) NOT NULL,
  `loanid` varchar(64) DEFAULT '' COMMENT '贷款id',
  `stagenum` int(11) DEFAULT '0' COMMENT '还款期次，第几期还款',
  `repaymoney` decimal(18,2) DEFAULT '0.00' COMMENT '还款金额',
  `repaydateplan` datetime DEFAULT NULL COMMENT '最后还款时间/预计还款时间',
  `repaydatereal` datetime DEFAULT NULL COMMENT '实际还款时间',
  `isrepay` char(1) DEFAULT '0' COMMENT '是否已还本期：1、是',
  `issendmsg` char(1) DEFAULT '0' COMMENT '是否已发短信提醒',
  `state` char(1) DEFAULT '0' COMMENT '状态：是否生效：1、是',
  `isoverdue` char(1) DEFAULT '' COMMENT '是否逾期：1、是',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  `orderno` varchar(100) DEFAULT '' COMMENT '订单号，关联订单表orderno',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repaydetail
-- ----------------------------
INSERT INTO `repaydetail` VALUES ('037a62bdbac440c1b95bb7e6e691c8ac', '0adea96807d14ad4a3a27c544e211fc4', '1', '1030.00', '2018-06-07 15:10:17', null, null, null, '0', null, null, null);
INSERT INTO `repaydetail` VALUES ('0d1ef2d9c44d4b0bb58203134ed42982', '1b12c57b437244d08eb9419c10806984', '3', '2333.31', '2018-07-22 16:28:40', null, null, null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('1a97f7f24fed4a39a60ab953f5c94a00', 'd1710ce208d9423c9ea0aa0ad3b54bff', '1', '1308.63', '2018-05-22 16:22:57', '2018-04-22 17:08:52', '1', null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('3964ee5a446b44b69ef2b20b7e6bc3ae', 'd1710ce208d9423c9ea0aa0ad3b54bff', '7', '1308.63', '2018-11-22 16:22:58', '2018-04-22 17:44:58', '1', null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('3be5d40725aa4da18dd0d46bff142809', 'd1710ce208d9423c9ea0aa0ad3b54bff', '8', '1308.63', '2018-12-22 16:22:58', null, null, null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('41e7a6ab6b0d4059895dc0aa0924440d', '1b12c57b437244d08eb9419c10806984', '2', '2333.31', '2018-06-22 16:28:40', null, null, null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('49a698a7459e4087b77abb81e108bb67', 'd1710ce208d9423c9ea0aa0ad3b54bff', '6', '1308.63', '2018-10-22 16:22:58', '2018-04-22 17:44:58', '1', null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('6cb74dde7202427292c3750980c969c1', 'd1710ce208d9423c9ea0aa0ad3b54bff', '9', '1308.63', '2019-01-22 16:22:58', null, null, null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('6f92a17512f04476a4d7f3f12481010b', '1b12c57b437244d08eb9419c10806984', '5', '2333.31', '2018-09-22 16:28:40', null, null, null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('85c0bb6f20834666a3155e5b44b792a7', 'd1710ce208d9423c9ea0aa0ad3b54bff', '4', '1308.63', '2018-08-22 16:22:57', '2018-04-22 17:43:48', '1', null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('98f113b8c7a246d5b79ecacb15c4b844', 'd1710ce208d9423c9ea0aa0ad3b54bff', '3', '1308.63', '2018-07-22 16:22:57', '2018-04-22 17:30:56', '1', null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('bfeff59a48294a2e9e9689a97c4f8e28', '1b12c57b437244d08eb9419c10806984', '4', '2333.31', '2018-08-22 16:28:40', null, null, null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('c57ff6fef4cc4eb2ac5dd5f3bc23bbfd', 'd1710ce208d9423c9ea0aa0ad3b54bff', '5', '1308.63', '2018-09-22 16:22:58', '2018-04-22 17:44:52', '1', null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('d592e8ddfff1461092e9fb2362d4141c', '30541cf1be774cf5a595ce033ca329a2', '1', '1030.00', '2018-06-07 15:03:16', null, null, null, '0', null, null, null);
INSERT INTO `repaydetail` VALUES ('d7db95e9b65f401db061f4234f75f1e2', '1b12c57b437244d08eb9419c10806984', '1', '2333.31', '2018-05-22 16:28:40', null, null, null, '1', '', null, '');
INSERT INTO `repaydetail` VALUES ('eb635d5824644e7bbbda37d25f510ba2', 'd1710ce208d9423c9ea0aa0ad3b54bff', '2', '1308.63', '2018-06-22 16:22:57', '2018-04-22 17:30:56', '1', null, '1', '', null, '');

-- ----------------------------
-- Table structure for repayorder
-- ----------------------------
DROP TABLE IF EXISTS `repayorder`;
CREATE TABLE `repayorder` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `orderno` varchar(100) DEFAULT '' COMMENT '还款时生成的支付宝订单号',
  `orderqrimage` varchar(500) DEFAULT '' COMMENT '订单支付二维码',
  `orderstate` char(1) DEFAULT '0' COMMENT '支付状态：1、已支付',
  `paydate` datetime DEFAULT NULL COMMENT '支付时间',
  `createdate` datetime DEFAULT NULL COMMENT '订单生成时间',
  `orderdesc` varchar(500) DEFAULT '' COMMENT '订单说明',
  `ordertitle` varchar(255) DEFAULT '' COMMENT '订单标题',
  `totalamount` decimal(18,2) DEFAULT '0.00' COMMENT '订单金额',
  `timeoutexpress` varchar(100) DEFAULT '',
  `sellerid` varchar(50) DEFAULT '',
  `operatorid` varchar(50) DEFAULT '',
  `storeid` varchar(200) DEFAULT '',
  `undiscountableamount` varchar(255) DEFAULT '',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repayorder
-- ----------------------------
INSERT INTO `repayorder` VALUES ('066b96b8893a4080a7ffaac41242ab15', 'repay_066b96b8893a4080a7ffaac41242ab15', '/static/alipay/loan/qr-repay_066b96b8893a4080a7ffaac41242ab15.png', '0', null, '2018-05-05 13:44:14', '测试01的还款,还款金额(14283.81)', 'XXX校园贷扫码放款', '14283.81', '', '', '', '', '', '');
INSERT INTO `repayorder` VALUES ('594ecf6e63114a8aa8fc7d500640209c', 'repay_594ecf6e63114a8aa8fc7d500640209c', '/static/alipay/repay/qr-repay_594ecf6e63114a8aa8fc7d500640209c.png', '0', null, '2018-05-01 18:31:29', '测试01的还款,还款金额(4666.62)', 'XXX校园贷扫码放款', '4666.62', '', '', '', '', '', '');
INSERT INTO `repayorder` VALUES ('c144e1cbc44d4389bd613d95c8a5e876', 'repay_c144e1cbc44d4389bd613d95c8a5e876', '/static/alipay/repay/qr-repay_c144e1cbc44d4389bd613d95c8a5e876.png', '0', null, '2018-05-03 22:18:35', '测试01的还款,还款金额(4666.62)', 'XXX校园贷扫码放款', '4666.62', '', '', '', '', '', '');
INSERT INTO `repayorder` VALUES ('c697f57c2b3445b690e91ba2668c023a', 'repay_c697f57c2b3445b690e91ba2668c023a', '/static/alipay/repay/qr-repay_c697f57c2b3445b690e91ba2668c023a.png', '0', null, '2018-05-03 22:18:17', '测试01的还款,还款金额(4666.62)', 'XXX校园贷扫码放款', '4666.62', '', '', '', '', '', '');
INSERT INTO `repayorder` VALUES ('c8ea8a888465488e9ee26f068c3c7f01', 'repay_c8ea8a888465488e9ee26f068c3c7f01', '/static/alipay/repay/qr-repay_c8ea8a888465488e9ee26f068c3c7f01.png', '0', null, '2018-05-01 18:43:53', '测试01的还款,还款金额(4666.62)', 'XXX校园贷扫码放款', '4666.62', '', '', '', '', '', '');
INSERT INTO `repayorder` VALUES ('cc44d8236ec944f89d64232f4d518011', 'repay_cc44d8236ec944f89d64232f4d518011', '/static/alipay/repay/qr-repay_cc44d8236ec944f89d64232f4d518011.png', '0', null, '2018-05-01 18:41:33', '测试01的还款,还款金额(4666.62)', 'XXX校园贷扫码放款', '4666.62', '', '', '', '', '', '');
INSERT INTO `repayorder` VALUES ('df1a1486ad314920b6a97ede0d1094a1', 'repay_df1a1486ad314920b6a97ede0d1094a1', '', '0', null, '2018-05-05 13:37:27', '测试01的还款,还款金额(14283.810000000001)', 'XXX校园贷扫码放款', '14283.81', '', '', '', '', '', '');
INSERT INTO `repayorder` VALUES ('ff761184a0e24fd7b01c7bd7f4f3d0fd', 'repay_ff761184a0e24fd7b01c7bd7f4f3d0fd', '', '0', null, '2018-05-05 13:41:49', '测试01的还款,还款金额(14283.810000000001)', 'XXX校园贷扫码放款', '14283.81', '', '', '', '', '', '');
INSERT INTO `repayorder` VALUES ('ff7d8705f1624ed6846c1b397d17365a', 'repay_ff7d8705f1624ed6846c1b397d17365a', '/static/alipay/repay/qr-repay_ff7d8705f1624ed6846c1b397d17365a.png', '0', null, '2018-05-01 18:42:10', '测试01的还款,还款金额(4666.62)', 'XXX校园贷扫码放款', '4666.62', '', '', '', '', '', '');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT 'id',
  `sourcecode` varchar(255) DEFAULT '' COMMENT 'resource编码',
  `sourcename` varchar(255) DEFAULT '' COMMENT '资源名称',
  `sourcedesc` varchar(255) DEFAULT '' COMMENT '资源说明',
  `sourcelevel` int(11) DEFAULT '0' COMMENT '资源等级',
  `sourcetype` int(11) DEFAULT '0' COMMENT '资源类别：待定。',
  `sourceurl` varchar(1000) DEFAULT '' COMMENT '资源链接',
  `parentsourceid` int(11) DEFAULT '0' COMMENT '上级资源id',
  `parentsourcecode` varchar(255) DEFAULT '' COMMENT '上级资源编码',
  `state` int(11) DEFAULT '0' COMMENT '状态：0、不可用，1、可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1000', null, '用户管理', '用户管理', '0', '0', '/', '0', null, '1');
INSERT INTO `resource` VALUES ('1001', null, '用户设置', '用户设置', '0', '0', '/consoles/user', '1000', null, '1');
INSERT INTO `resource` VALUES ('1002', null, '黑名单', '黑名单', '0', '0', '/consoles/blacklist', '1000', null, '1');
INSERT INTO `resource` VALUES ('2000', null, '认证管理', '认证管理', '0', '0', '/', '0', null, '1');
INSERT INTO `resource` VALUES ('2001', null, '学生认证', '学生认证', '0', '0', '/consoles/stuinfo', '2000', null, '1');
INSERT INTO `resource` VALUES ('2002', null, '信用认证', '信用认证', '0', '0', '/consoles/creditscore', '2000', null, '1');
INSERT INTO `resource` VALUES ('3000', null, '审核管理', '审核管理', '0', '0', '/', '0', null, '1');
INSERT INTO `resource` VALUES ('3001', null, '贷款审核', '贷款审核', '0', '0', '/consoles/loanaudit', '3000', null, '1');
INSERT INTO `resource` VALUES ('3002', null, '图片审核', '图片审核', '0', '0', '/consoles/photoaudit', '3000', null, '1');
INSERT INTO `resource` VALUES ('4000', null, '放款管理', '放款管理', '0', '0', '/', '0', null, '1');
INSERT INTO `resource` VALUES ('4001', null, '贷款放款', '贷款放款', '0', '0', '/consoles/loanout', '4000', null, '1');
INSERT INTO `resource` VALUES ('5000', null, '财务管理', '财务管理', '0', '0', '/', '0', null, '1');
INSERT INTO `resource` VALUES ('5001', null, '财务统计', '财务统计', '0', '0', '/consoles/moneystatistics', '5000', null, '1');
INSERT INTO `resource` VALUES ('5002', null, '贷款列表', '贷款列表', '0', '0', '/consoles/loan', '5000', null, '1');
INSERT INTO `resource` VALUES ('5003', null, '逾期管理', '逾期管理', '0', '0', '/consoles/overdue', '5000', null, '1');
INSERT INTO `resource` VALUES ('5004', null, '未还款列表', '未还款列表', '0', '0', '/consoles/notrepay', '5000', null, '1');
INSERT INTO `resource` VALUES ('99000', null, '系统管理', '系统管理', '0', '0', '/', '0', null, '1');
INSERT INTO `resource` VALUES ('99001', null, '费率设置', '费率设置', '0', '0', '/consoles/stagefee', '99000', null, '1');
INSERT INTO `resource` VALUES ('99002', null, '资源设置', '资源设置', '0', '0', '/consoles/resource', '99000', null, '1');
INSERT INTO `resource` VALUES ('99003', null, '额度等级设置', '额度等级设置', null, null, '/consoles/creditmoney', '99000', null, '1');

-- ----------------------------
-- Table structure for shortmessage
-- ----------------------------
DROP TABLE IF EXISTS `shortmessage`;
CREATE TABLE `shortmessage` (
  `id` varchar(64) NOT NULL,
  `userid` varchar(64) DEFAULT '' COMMENT '用户id',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `msgcontent` varchar(500) DEFAULT '' COMMENT '短信内容',
  `msgdate` datetime DEFAULT NULL COMMENT '发送时间',
  `issend` char(1) DEFAULT '0' COMMENT '是否已发送',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shortmessage
-- ----------------------------

-- ----------------------------
-- Table structure for sms
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `smstype` char(1) DEFAULT '1' COMMENT '短信类型：1、验证码，2、通知',
  `smsphone` varchar(500) DEFAULT '' COMMENT '短信接收号码',
  `smscode` varchar(10) DEFAULT '' COMMENT '验证码，验证码短信有用',
  `smscontent` varchar(500) DEFAULT '' COMMENT '短信内容',
  `smstime` datetime DEFAULT NULL COMMENT '短信发送时间',
  `validtime` datetime DEFAULT NULL COMMENT '有效时间，验证码短信有用',
  `state` char(1) DEFAULT '1' COMMENT '状态：1、已发送',
  `smsdesc` varchar(500) DEFAULT '' COMMENT '说明，描述',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  `signname` varchar(255) DEFAULT '' COMMENT '短信签名',
  `templatecode` varchar(255) DEFAULT '' COMMENT '短信模板代码',
  `templateparam` varchar(255) DEFAULT '' COMMENT '短信变量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms
-- ----------------------------
INSERT INTO `sms` VALUES ('07a2c51df5ad4756a2f485cb1b182a0c', '1', '15223653995', '3455', '', '2018-05-03 22:24:56', '2018-05-03 22:29:56', '1', '15223653995的注册验证码', '', '树丫丫的通知', 'SMS_134080286', '{\"code\":\"3455\"}');
INSERT INTO `sms` VALUES ('17e587bc374b41ecaaa8042e1a45b7fa', '1', '15223653995', '6944', '', '2018-05-03 15:31:22', '2018-05-03 15:36:22', '1', '15223653995的注册验证码', '', '树丫丫的通知', 'SMS_134080286', '{\"code\":\"6944\"}');
INSERT INTO `sms` VALUES ('1c424cd9568941cb9977ac3707a59916', '2', ',11111111111', '', '', '2018-05-07 10:50:09', null, '1', '短信提醒已放款', '', '树丫丫的通知', 'SMS_133973146', '');
INSERT INTO `sms` VALUES ('1c76004aa7d043c89550b8fc9b14ca2b', '1', '15223653995', '2371', '', '2018-05-03 15:35:06', '2018-05-03 15:40:06', '1', '15223653995的注册验证码', '', '树丫丫的通知', 'SMS_134080286', '{\"code\":\"2371\"}');
INSERT INTO `sms` VALUES ('2aae787ec321459c8d442f3c5f3e44d5', '2', ',11111111111', '', '', '2018-05-05 15:37:51', null, '1', '短信提醒已放款', '', '树丫丫的通知', 'SMS_133973146', '');
INSERT INTO `sms` VALUES ('352e4a00c07d4430a71d4d17f2fb248b', '2', ',11111111111', '', '', '2018-05-03 23:04:53', null, '1', '短信提示还款时间即将到期', '', '树丫丫的通知', 'SMS_133973146', '');
INSERT INTO `sms` VALUES ('5adf835cfc7044429e8fd36b4724adff', '2', ',11111111111', '', '', '2018-05-03 23:03:24', null, '1', '短信提示还款时间即将到期', '', '树丫丫的通知', 'SMS_133973146', '');
INSERT INTO `sms` VALUES ('643690d56550471683a0cbc2f912f07e', '1', '15223653995', '3080', '', '2018-05-03 15:29:16', '2018-05-03 15:34:16', '1', '15223653995的注册验证码', '', '树丫丫的通知', 'SMS_134080286', '{\"code\":\"3080\"}');
INSERT INTO `sms` VALUES ('9c9b8b76f43e48b88657cd7a896259e2', '2', ',11111111111', '', '', '2018-05-05 17:21:54', null, '1', '短信提醒已放款', '', '树丫丫的通知', 'SMS_133973146', '');
INSERT INTO `sms` VALUES ('9cd9514979d747ef9c582d73cb7fc847', '1', '15223653995', '2674', '', '2018-05-03 15:33:06', '2018-05-03 15:38:06', '1', '15223653995的注册验证码', '', '树丫丫的通知', 'SMS_134080286', '{\"code\":\"2674\"}');
INSERT INTO `sms` VALUES ('a6ccfc23c49b446abb0c72f93a75f318', '2', ',11111111111', '', '', '2018-05-05 17:50:03', null, '1', '短信提醒已放款', '', '树丫丫的通知', 'SMS_133973146', '');
INSERT INTO `sms` VALUES ('b780fb08f57f40309292ffeca6d7292b', '1', '15223653995', '1443', '', '2018-05-03 22:16:52', '2018-05-03 22:21:52', '1', '15223653995的注册验证码', '', '树丫丫的通知', 'SMS_134080286', '{\"code\":\"1443\"}');
INSERT INTO `sms` VALUES ('f713bf06a87e4eae907a6f0272f64a6b', '1', '15223653995', '4681', '', '2018-05-03 22:23:22', '2018-05-03 22:28:22', '1', '15223653995的注册验证码', '', '树丫丫的通知', 'SMS_134080286', '{\"code\":\"4681\"}');

-- ----------------------------
-- Table structure for stagefee
-- ----------------------------
DROP TABLE IF EXISTS `stagefee`;
CREATE TABLE `stagefee` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `stagenum` int(11) DEFAULT '0' COMMENT '分期期数',
  `feepercent` decimal(18,2) DEFAULT '0.00' COMMENT '手续费百分比，实际为字段值%100',
  `state` char(1) DEFAULT '0' COMMENT '状态：1、可用',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stagefee
-- ----------------------------
INSERT INTO `stagefee` VALUES ('1', '1', '3.00', '1', null);
INSERT INTO `stagefee` VALUES ('2', '2', '3.00', '1', null);
INSERT INTO `stagefee` VALUES ('4', '4', '5.00', '1', null);
INSERT INTO `stagefee` VALUES ('7', '7', '6.00', '1', null);
INSERT INTO `stagefee` VALUES ('8', '8', '6.00', '1', null);
INSERT INTO `stagefee` VALUES ('8a1dcc441b4f49c29ae87a46fc4384f5', '3', '3.00', '1', null);
INSERT INTO `stagefee` VALUES ('9', '9', '6.00', '1', null);
INSERT INTO `stagefee` VALUES ('9fcea4bfcc5e4c49890ed85dd5000c41', '6', '5.00', '1', null);
INSERT INTO `stagefee` VALUES ('d22593b3de9247b197227d718d1be067', '5', '5.00', '1', null);

-- ----------------------------
-- Table structure for studentinfo
-- ----------------------------
DROP TABLE IF EXISTS `studentinfo`;
CREATE TABLE `studentinfo` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `userid` varchar(64) DEFAULT '' COMMENT '用户id，关联baseuser表学生用户id',
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
-- Records of studentinfo
-- ----------------------------
INSERT INTO `studentinfo` VALUES ('1', '4f0fde82c40e443195449ef366295422', '测试01', '1', '111111111111111111', '重庆邮电大学', '本科', '测控技术与仪器', '4', '普通', '普通全日制', '自动化学院', '0820803', '08210312', '2008-09-01', '2012-07-01', '0', '', '1');

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键id',
  `username` varchar(255) DEFAULT '' COMMENT '昵称/真实姓名/展示姓名',
  `loginname` varchar(255) NOT NULL DEFAULT '' COMMENT '用户登录名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `idcard` varchar(18) DEFAULT '' COMMENT '身份证号',
  `phone` varchar(255) DEFAULT '' COMMENT '电话号码',
  `wechart` varchar(255) DEFAULT '' COMMENT '微信',
  `qqnum` varchar(255) DEFAULT '' COMMENT 'qq号',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  `createman` varchar(255) DEFAULT '' COMMENT '创建人',
  `createdate` varchar(255) DEFAULT '' COMMENT '注册时间',
  `province` varchar(255) DEFAULT '' COMMENT '省',
  `city` varchar(255) DEFAULT '' COMMENT '市',
  `district` varchar(255) DEFAULT '' COMMENT '区',
  `county` varchar(255) DEFAULT '' COMMENT '县',
  `town` varchar(255) DEFAULT '' COMMENT '镇',
  `detailaddr` varchar(1000) DEFAULT '' COMMENT '详细地址',
  `userphoto` varchar(2000) DEFAULT '' COMMENT '用户头像地址',
  `idcardphoto` varchar(2000) DEFAULT '' COMMENT '身份证图片地址',
  `state` int(11) DEFAULT '1' COMMENT '状态:1、可用',
  `isconsole` int(11) DEFAULT '0' COMMENT '是否可登录后台：1、是',
  `isfront` int(11) DEFAULT '0' COMMENT '是否可登陆前台：1、是',
  `userfrom` int(11) DEFAULT '0' COMMENT '用户类型：1、前台注册；2、后台管理员创建',
  `isstuidentity` char(1) DEFAULT '0' COMMENT '是否已通过学生认证:1、已认证',
  `loanlimit` decimal(18,2) DEFAULT '0.00' COMMENT '贷款额度',
  `iscreditidentity` char(1) DEFAULT '0' COMMENT '是否通过信用认证',
  `isfrozen` char(1) DEFAULT '0' COMMENT '是否冻结：1、是',
  `photostate` char(1) DEFAULT '0' COMMENT '图片状态：1、图片都合格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('10ab38914bc34a43813e86888e540b27', null, 'test02', '96e79218965eb72c92a549dd5a330112', null, '15223653995', null, null, null, 'test02', '2018-05-03 15:35:14', null, null, null, null, null, null, null, null, '1', null, '1', '1', null, null, null, '0', '0');
INSERT INTO `sysuser` VALUES ('42e4d707a11447b69c2ea8da0351ddee', null, 'testbak02', '96e79218965eb72c92a549dd5a330112', null, '12345678901', '', '123456789', null, 'admin', '2018-05-05 14:45:44', '重庆市', '重庆市市辖区', '江北区', null, null, null, '', '', '1', '1', '1', '2', null, null, null, '0', '0');
INSERT INTO `sysuser` VALUES ('4f0fde82c40e443195449ef366295422', '测试01', 'test01', '96e79218965eb72c92a549dd5a330112', '', '11111111111', '', '', '', 'test01', '2018-03-07 17:14:16', '', '', '', '', '', '', '', '', '1', '0', '1', '1', '1', '3000000.00', '1', '0', '1');
INSERT INTO `sysuser` VALUES ('8f414d83e03441b68ce615de593db44f', null, 'testbak01', '96e79218965eb72c92a549dd5a330112', null, '12345678901', '', '12345678', null, 'admin', '2018-05-05 14:41:10', '重庆市', '重庆市市辖区', '江北区', null, null, null, '', '', '1', '1', '1', '2', null, null, null, '0', '0');
INSERT INTO `sysuser` VALUES ('999', '系统管理员', 'admin', '96e79218965eb72c92a549dd5a330112', null, '11111111111', '', '11111111', null, '', '', '重庆市', '重庆市市辖区', '江北区', null, null, null, '', '', '1', '1', '0', '0', '0', '0.00', '0', '0', '0');
INSERT INTO `sysuser` VALUES ('a1b4e6ed0139428cb0016390a7795514', null, 'test03', '96e79218965eb72c92a549dd5a330112', null, '15223653995', null, null, null, 'test03', '2018-05-03 22:17:45', null, null, null, null, null, null, null, null, '1', null, '1', '1', null, null, null, '0', '0');

-- ----------------------------
-- Table structure for userphoto
-- ----------------------------
DROP TABLE IF EXISTS `userphoto`;
CREATE TABLE `userphoto` (
  `id` varchar(64) NOT NULL,
  `userid` varchar(64) NOT NULL,
  `headphoto` varchar(2000) DEFAULT '' COMMENT '头像照片地址',
  `headstate` char(1) DEFAULT '0' COMMENT '头像是否合格：1、是',
  `idcardphoto` varchar(2000) DEFAULT '' COMMENT '身份证照地址',
  `idcardstate` char(1) DEFAULT '0' COMMENT '身份证照是否合格：1、是',
  `stuidcardphoto` varchar(2000) DEFAULT '' COMMENT '学生证照片地址',
  `stuidcardstate` char(1) DEFAULT '0' COMMENT '学生证照片是否合格：1、是',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userphoto
-- ----------------------------
INSERT INTO `userphoto` VALUES ('3aad2faecadc4a4fb7277f4c54d0736a', '4f0fde82c40e443195449ef366295422', '/static/upload/userphoto/headphoto/85653714-8a95-49b5-ae67-5c2f7c37f918.png', '1', '/static/upload/userphoto/idcardphoto/ce7d123d-d340-49b5-bb76-1ce40bafb4aa.jpg', '1', '/static/upload/userphoto/stuidcardphoto/802ac745-0e55-40e0-bfda-dd0849646460.jpg', '1', '');

-- ----------------------------
-- Table structure for userresource
-- ----------------------------
DROP TABLE IF EXISTS `userresource`;
CREATE TABLE `userresource` (
  `id` varchar(64) NOT NULL COMMENT '自增长id',
  `userid` varchar(64) NOT NULL COMMENT '用户id',
  `resourceid` varchar(64) NOT NULL COMMENT '资源id',
  `remark` varchar(255) DEFAULT '' COMMENT '备注备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userresource
-- ----------------------------
INSERT INTO `userresource` VALUES ('ad0ffdecae8f4782a052b388c5576be9', '42e4d707a11447b69c2ea8da0351ddee', '4000', null);
INSERT INTO `userresource` VALUES ('c0810e0cd1e148d7a84e0401d1c6c371', '8f414d83e03441b68ce615de593db44f', '3000', null);
INSERT INTO `userresource` VALUES ('ce58f529cdef4c6a9c2a62e371b48e17', '8f414d83e03441b68ce615de593db44f', '3001', null);
INSERT INTO `userresource` VALUES ('e849e2e571274b99afaca05e2483610a', '42e4d707a11447b69c2ea8da0351ddee', '4001', null);
