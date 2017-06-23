/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mloa

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-23 18:32:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dh_fdbk_permission
-- ----------------------------
DROP TABLE IF EXISTS `dh_fdbk_permission`;
CREATE TABLE `dh_fdbk_permission` (
  `id` int(11) NOT NULL,
  `permissionName` varchar(255) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dh_fdbk_permission
-- ----------------------------
INSERT INTO `dh_fdbk_permission` VALUES ('1', 'fdbk:create', '1');
INSERT INTO `dh_fdbk_permission` VALUES ('2', 'fdbk:edit', '1');
INSERT INTO `dh_fdbk_permission` VALUES ('3', 'fdbk:delete', '1');
INSERT INTO `dh_fdbk_permission` VALUES ('4', 'fdbk:view', '1');
