/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mloa

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-23 18:32:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dh_fdbk_role
-- ----------------------------
DROP TABLE IF EXISTS `dh_fdbk_role`;
CREATE TABLE `dh_fdbk_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  `roleTitle` varchar(50) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dh_fdbk_role
-- ----------------------------
INSERT INTO `dh_fdbk_role` VALUES ('1', 'admin', '组长', '2017-06-23 10:43:01');
INSERT INTO `dh_fdbk_role` VALUES ('2', 'user', '普通用户', '2017-06-23 09:43:10');
INSERT INTO `dh_fdbk_role` VALUES ('3', 'system', '系统管理员', '2017-06-23 10:43:12');
