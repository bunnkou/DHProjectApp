/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mloa

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-23 18:32:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dh_fdbk_user_role
-- ----------------------------
DROP TABLE IF EXISTS `dh_fdbk_user_role`;
CREATE TABLE `dh_fdbk_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dh_fdbk_user_role
-- ----------------------------
INSERT INTO `dh_fdbk_user_role` VALUES ('4', 'SHO745', '2');
INSERT INTO `dh_fdbk_user_role` VALUES ('6', 'SH0000', '1');
INSERT INTO `dh_fdbk_user_role` VALUES ('8', 'SH0981', '1');
INSERT INTO `dh_fdbk_user_role` VALUES ('9', 'SH0650', '1');
INSERT INTO `dh_fdbk_user_role` VALUES ('10', 'SH0888', '1');
INSERT INTO `dh_fdbk_user_role` VALUES ('11', 'SH1029', '1');
INSERT INTO `dh_fdbk_user_role` VALUES ('12', 'SH0860', '1');
INSERT INTO `dh_fdbk_user_role` VALUES ('15', 'SHO745', '1');
