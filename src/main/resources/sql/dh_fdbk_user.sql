/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mloa

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-23 18:32:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dh_fdbk_user
-- ----------------------------
DROP TABLE IF EXISTS `dh_fdbk_user`;
CREATE TABLE `dh_fdbk_user` (
  `user_id` varchar(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_py` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dh_fdbk_user
-- ----------------------------
INSERT INTO `dh_fdbk_user` VALUES ('SH0000', 'admin', 'Qian Bin');
INSERT INTO `dh_fdbk_user` VALUES ('SH0981', '朱惠杰', 'Zhu Huijie');
INSERT INTO `dh_fdbk_user` VALUES ('SHO745', '关文浩', 'Guan Wenhao');
