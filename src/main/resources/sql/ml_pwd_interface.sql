/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mloa

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-23 18:31:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ml_pwd_interface
-- ----------------------------
DROP TABLE IF EXISTS `ml_pwd_interface`;
CREATE TABLE `ml_pwd_interface` (
  `user_id` varchar(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_py` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ml_pwd_interface
-- ----------------------------
INSERT INTO `ml_pwd_interface` VALUES ('DL0001', '李欣琰', 'lixinyan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('DL0006', '李申', 'lishen', 'siwangbana');
INSERT INTO `ml_pwd_interface` VALUES ('DL0011', '王维', 'wangwei1', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('DL0018', '张巍', 'zhangwei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('DL0030', '石芮铭', 'shiruiming', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('DL0060', '尹伯韬', 'yinbotao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('DL0114', '王鹏', 'wangpeng', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('DL0118', '谢超', 'xiechao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('DL0150', '于亮', 'yuliang', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('DLDL80', '黄选帅', 'huangxuanshuai', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('DLL093', '刘明', 'liuming', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0000', 'admin', 'admin', 'New24You');
INSERT INTO `ml_pwd_interface` VALUES ('SH0001', '苏方', 'sufang', 'SuChe000210');
INSERT INTO `ml_pwd_interface` VALUES ('SH0004', '徐臻', 'xuzhen', 'tjml');
INSERT INTO `ml_pwd_interface` VALUES ('SH0041', '倪歌', 'nige', 'iori@912');
INSERT INTO `ml_pwd_interface` VALUES ('SH0083', '郭丛', 'guocong', 'Minliu123');
INSERT INTO `ml_pwd_interface` VALUES ('SH0090', '朱欣苗', 'zhuxinmiao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0148', '林涛', 'lintao', '58893169');
INSERT INTO `ml_pwd_interface` VALUES ('SH0209', '汪峥', 'wangzheng', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0269', '宗瑾', 'zongjin', 'EricaWang995518');
INSERT INTO `ml_pwd_interface` VALUES ('SH0374', '周飚', 'zhoubiao', 'zb123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0431', '郭栋', 'guodong', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0482', '陈富泓', 'chenfuhong', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0491', '俞顺捷', 'yushunjie', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0521', '管雍雍', 'guanyongyong', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0522', '周爽', 'zhoushuang', 'Eldridge3');
INSERT INTO `ml_pwd_interface` VALUES ('SH0608', '徐明辉', 'xuminghui', 'lishalisha');
INSERT INTO `ml_pwd_interface` VALUES ('SH0611', '蔡倪婷', 'cainiting', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0642', '向鹏举', 'xiangpengju', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0643', '赵煜', 'zhaoyu', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0648', '陈睿峰', 'chenruifeng', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0650', '施嘉祺', 'shijiaqi', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0656', '郑甘泉', 'zhengganquan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0683', '夏超', 'xiachao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0711', '杨庆庆', 'yangqingqing', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0715', '房样兵', 'fangyangbing', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0754', '奚佳', 'xijia', 'xixi6913');
INSERT INTO `ml_pwd_interface` VALUES ('SH0762', '熊丹', 'xiongdan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0772', '林茹', 'linru', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0774', '马云峰', 'mayunfeng', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0780', '徐金平', 'xujinping', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0783', '仓潇伟', 'cangxiaowei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0789', '沈莹', 'shenying', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0796', '蔡黄程', 'caihuangcheng', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0830', '杭历辰', 'hanglichen', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0837', '王媛', 'wangyuan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0841', '张宾', 'zhangbin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0860', '易海涛', 'yihaitao', '060422119');
INSERT INTO `ml_pwd_interface` VALUES ('SH0863', '郑峙寒', 'zhengzhihan', 'z65699989');
INSERT INTO `ml_pwd_interface` VALUES ('SH0866', '唐晓君', 'tangxiaojun', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0868', '周金鑫', 'zhoujinxin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0882', '李勃言', 'liboyan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0888', '武峰', 'wufeng', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0889', '吴维多', 'wuweiduo', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0892', '饶光鼎', 'raoguangding', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0895', '李兆龙', 'lizhaolong', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0901', '王伟_GD', 'wangwei_GD', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0903', '王娟', 'wangjuan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0904', '殷巍', 'yinwei', '2113002144');
INSERT INTO `ml_pwd_interface` VALUES ('SH0905', '宗珊', 'zongshan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0906', '陆文斌', 'luwenbin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0912', '杨俊琰', 'yangjunyan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0916', '蒋琴', 'jiangqin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0922', '李东敬', 'lidongjing', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0925', '王国亮', 'wangguoliang', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0928', '杨帆', 'yangfan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0932', '施赵', 'shizhao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0933', '于智杰', 'yuzhijie', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0936', '邓小欢', 'dengxiaohuan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0937', '郭诚炜', 'guochengwei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0940', '陶晨超', 'taochenchao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0946', '王晓君', 'wangxiaojun', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0948', '刘亚超', 'liuyachao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0951', '丁翰之', 'dinghanzhi', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0952', '钱晓', 'qianxiao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0956', '邵一晋', 'shaoyijin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0964', '朱泽南', 'zhuzenan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0973', '易栩妃', 'yixufei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0974', '齐玉杰', 'qiyujie', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0976', '王涵彬', 'wanghanbin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0981', '朱惠杰', 'zhuhuijie', 'zhj123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0984', '周子安', 'zhouzian', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0985', '王元明', 'wangyuanming', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0986', '梁鹭', 'lianglu', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0989', '柯达', 'keda', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0991', '叶贤众', 'yexianzhong', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0992', '黄丽洁', 'huanglijie', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0993', '冯佳琳', 'fengjialin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH0996', '吴凯灵', 'wukailing', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1001', '任安', 'renan', '654321');
INSERT INTO `ml_pwd_interface` VALUES ('SH1006', '宋涛', 'songtao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1008', '朱心坤', 'zhuxinkun', 'zzw20050221');
INSERT INTO `ml_pwd_interface` VALUES ('SH1009', '周立伟', 'zhouliwei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1011', '郁舒挺', 'yushuting', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1013', '程枭', 'chengxiao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1017', '殷鑫', 'yinxin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1020', '孙鲁杰', 'sunlujie', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1021', '胡晓杰', 'huxiaojie', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1024', '刘梦', 'liumeng', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1029', '沈思渊', 'shensiyuan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1033', '曹圣哲', 'caoshengzhe', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1034', '覃欣', 'qinxin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1035', '张强', 'zhangqiang', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1036', '韩腾', 'hanteng', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1038', '刘盼', 'liupan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1039', '张洋洋', 'zhangyangyang', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1040', '高语谦', 'gaoyuqian', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1042', '潘红莉', 'panhongli', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1043', '苏然', 'suran', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1045', '王舒凡', 'Wang Shufan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1049', '孔瑶梦', 'kongyaomeng', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1050', '向仕杰', 'xiangshijie', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1051', '黄佳栋', 'huangjiadong', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1053', '宋子豪', 'songzihao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1054', '周晨骅', 'zhouchenhua', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1056', '杨玺明', 'yangximing', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1057', '花建伟', 'huajianwei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1058', '邵宇轩', 'shaoyuxuan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1060', '奚奇', 'xiqi', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1061', '符昊', 'fuhao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1062', '田金川', 'tianjinchuan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1064', '唐仁', 'tangren', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1065', '张敦洋', 'zhangdunyang', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1066', '曹志伟', 'caozhiwei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1068', '李莹莹', 'liyingying', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1070', '张影', 'zhangying', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1071', '吕双娜', 'lvshuangna', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1072', '孙艺飞', 'sunyifei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1073', '殷璐燕', 'yinluyan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1078', '何慧娜', 'hehuina', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1079', '林丽', 'linli', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1081', '李晖', 'lihui', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1084', '邓灵波', 'denglingbo', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1085', '车晓博', 'chexiaobo', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1086', '陈静', 'chenjing', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1088', '周骁', 'zhouxiao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1089', '梁宇晨', 'liangyuchen', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1091', '梅亚', 'meiya', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1092', '李辉_3d', 'lihui_3d', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1093', '赵师洋', 'zhaoshiyang', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1094', '邵圣捷', 'shaoshengjie', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1095', '石教铵', 'shijiaoan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1096', '武亚飞', 'wuyafei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1097', '盛佳', 'shengjia', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1114', '韩一飞', 'hanyifei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1115', '汤国镇', 'tangguozhen', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1116', '申晓彤', 'shenxiaotong', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1117', '褚雨琴', 'chuyuqin', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1118', '井倩', 'jingqian', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1128', '陈俞', 'chenyu', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH1129', '崔燕萍', 'cuiyanping', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SH9999', '刘秀', 'liuxiu', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('SHO745', '关文浩', 'guanwenhao', 'buzhidao');
INSERT INTO `ml_pwd_interface` VALUES ('TJ0004', '王威', 'wangwei', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('TJ0016', '张艳艳', 'zhangyanyan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('TJ0022', '王辉', 'wanghui', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('TJ0023', '胡海江', 'huhaijiang', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('TJ0043', '赵严', 'zhaoyan', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('TJ0067', '袁涛', 'yuantao', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('TJ0240', '杨梦茹', 'yangmengru', '123456');
INSERT INTO `ml_pwd_interface` VALUES ('TJ0279', '刘紫微', 'liuziwei', '123456');
