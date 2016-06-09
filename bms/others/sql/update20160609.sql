/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : bms_test

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-06-09 22:59:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for metro_line
-- ----------------------------
DROP TABLE IF EXISTS `metro_line`;
CREATE TABLE `metro_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `loop` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of metro_line
-- ----------------------------
INSERT INTO `metro_line` VALUES ('1', 'metrolinefc54d8180f705c0f6263c01', '2016-06-05 18:52:55', '1号线', '#3c2b7c', '\0');
INSERT INTO `metro_line` VALUES ('2', 'metrolinefc54d8180f705c0f6263c02', '2016-06-05 18:52:55', '2号线', '#e26242', '\0');
INSERT INTO `metro_line` VALUES ('3', 'metrolinefc54d8180f705c0f6263c03', '2016-06-05 18:52:55', '3号线', '#d2246c', '\0');
INSERT INTO `metro_line` VALUES ('4', 'metrolinefc54d8180f705c0f6263c04', '2016-06-05 18:52:55', '4号线', '#38aa62', '\0');
INSERT INTO `metro_line` VALUES ('5', 'metrolinefc54d8180f705c0f6263c05', '2016-06-05 18:52:55', '5号线', '#a45691', '\0');
INSERT INTO `metro_line` VALUES ('6', 'metrolinefc54d8180f705c0f6263c06', '2016-06-05 18:52:55', '6号线', '#bd7734', '\0');
INSERT INTO `metro_line` VALUES ('7', 'metrolinefc54d8180f705c0f6263c07', '2016-06-05 18:52:55', '7号线', '#87c6d1', '');
INSERT INTO `metro_line` VALUES ('8', 'metrolinefc54d8180f705c0f6263c08', '2016-06-05 18:52:55', '8号线', '#9ec32e', '\0');
INSERT INTO `metro_line` VALUES ('9', 'metrolinefc54d8180f705c0f6263c09', '2016-06-05 18:52:55', '9号线', '#e9af20', '');
INSERT INTO `metro_line` VALUES ('10', 'metrolinefc54d8180f705c0f6263c10', '2016-06-05 18:52:55', '10号线', '#30599d', '\0');
INSERT INTO `metro_line` VALUES ('11', 'metrolinefc54d8180f705c0f6263c11', '2016-06-05 18:52:55', '11号线', '#8a7736', '\0');
INSERT INTO `metro_line` VALUES ('12', 'metrolinefc54d8180f705c0f6263c12', '2016-06-05 18:52:55', '12号线', '#752571', '\0');
INSERT INTO `metro_line` VALUES ('13', 'metrolinefc54d8180f705c0f6263c13', '2016-06-05 18:52:55', '13号线', '#c5afbc', '\0');
INSERT INTO `metro_line` VALUES ('14', 'metrolinefc54d8180f705c0f6263c14', '2016-06-05 18:52:55', '14号线', '#812e3d', '\0');
INSERT INTO `metro_line` VALUES ('15', 'metrolinefc54d8180f705c0f6263c15', '2016-06-05 18:52:55', '15号线', '#e9afbc', '\0');
INSERT INTO `metro_line` VALUES ('16', 'metrolinefc54d8180f705c0f6263c16', '2016-06-05 18:52:55', '16号线', '#0794c9', '\0');
INSERT INTO `metro_line` VALUES ('17', 'metrolinefc54d8180f705c0f6263c17', '2016-06-05 18:52:55', '17号线', '#93cea3', '\0');
INSERT INTO `metro_line` VALUES ('18', 'metrolinefc54d8180f705c0f6263c18', '2016-06-05 18:52:55', '18号线', '#276d73', '\0');
INSERT INTO `metro_line` VALUES ('19', 'metrolinefc54d8180f705c0f6263c19', '2016-06-05 18:52:55', '19号线', '#a2a3ca', '\0');
INSERT INTO `metro_line` VALUES ('20', 'metrolinefc54d8180f705c0f6263c20', '2016-06-05 18:52:55', '20号线', '#a9492b', '\0');





/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : bms_test

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-06-09 22:58:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `longitude` decimal(15,12) DEFAULT NULL,
  `latitude` decimal(15,12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES ('1', 'stationffc5649d98387489ac2c65001', '2016-06-05 20:13:26', '赖家店', '', null, null);
INSERT INTO `station` VALUES ('2', 'stationffc5649d98387489ac2c65002', '2016-06-05 20:13:26', '韦家碾', '', null, null);
INSERT INTO `station` VALUES ('3', 'stationffc5649d98387489ac2c65003', '2016-06-05 20:13:26', '升仙湖', 'SXH', '104.089841000000', '30.710665000000');
INSERT INTO `station` VALUES ('4', 'stationffc5649d98387489ac2c65004', '2016-06-05 20:13:26', '火车北站', 'HCBZ', '104.080489000000', '30.701631000000');
INSERT INTO `station` VALUES ('5', 'stationffc5649d98387489ac2c65005', '2016-06-05 20:13:26', '人民北路', 'RMBL', '104.079646000000', '30.691421000000');
INSERT INTO `station` VALUES ('6', 'stationffc5649d98387489ac2c65006', '2016-06-05 20:13:26', '文殊院', 'WSY', '104.074700000000', '30.678826000000');
INSERT INTO `station` VALUES ('7', 'stationffc5649d98387489ac2c65007', '2016-06-05 20:13:26', '骡马市', 'LMS', '104.072281000000', '30.672776000000');
INSERT INTO `station` VALUES ('8', 'stationffc5649d98387489ac2c65008', '2016-06-05 20:13:26', '天府广场', 'TFGC', '104.072227000000', '30.663456000000');
INSERT INTO `station` VALUES ('9', 'stationffc5649d98387489ac2c65009', '2016-06-05 20:13:26', '锦江宾馆', 'JJBG', '104.072507000000', '30.656241000000');
INSERT INTO `station` VALUES ('10', 'stationffc5649d98387489ac2c65010', '2016-06-05 20:13:26', '华西坝', 'HXB', '104.072743000000', '30.649044000000');
INSERT INTO `station` VALUES ('11', 'stationffc5649d98387489ac2c65011', '2016-06-05 20:13:26', '省体育馆', 'STYG', '104.072974000000', '30.639596000000');
INSERT INTO `station` VALUES ('12', 'stationffc5649d98387489ac2c65012', '2016-06-05 20:13:26', '倪家桥', 'NJQ', '104.073328000000', '30.631073000000');
INSERT INTO `station` VALUES ('13', 'stationffc5649d98387489ac2c65013', '2016-06-05 20:13:26', '桐梓林', 'TZL', '104.073642000000', '30.621375000000');
INSERT INTO `station` VALUES ('14', 'stationffc5649d98387489ac2c65014', '2016-06-05 20:13:26', '火车南站', 'HCNZ', '104.073785000000', '30.610840000000');
INSERT INTO `station` VALUES ('15', 'stationffc5649d98387489ac2c65015', '2016-06-05 20:13:26', '高新', 'GX', '104.070277000000', '30.600337000000');
INSERT INTO `station` VALUES ('16', 'stationffc5649d98387489ac2c65016', '2016-06-05 20:13:26', '金融城', 'JRC', '104.068517000000', '30.588411000000');
INSERT INTO `station` VALUES ('17', 'stationffc5649d98387489ac2c65017', '2016-06-05 20:13:26', '孵化园', 'FHY', '104.070815000000', '30.581795000000');
INSERT INTO `station` VALUES ('18', 'stationffc5649d98387489ac2c65018', '2016-06-05 20:13:26', '锦城广场', 'JCGC', '104.073413000000', '30.573732000000');
INSERT INTO `station` VALUES ('19', 'stationffc5649d98387489ac2c65019', '2016-06-05 20:13:26', '世纪城', 'SJC', '104.074947000000', '30.560878000000');
INSERT INTO `station` VALUES ('20', 'stationffc5649d98387489ac2c65020', '2016-06-05 20:13:26', '天府三街', 'TFSJ', '104.075175000000', '30.552622000000');
INSERT INTO `station` VALUES ('21', 'stationffc5649d98387489ac2c65021', '2016-06-05 20:13:26', '天府五街', 'TFWJ', '104.075470000000', '30.542862000000');
INSERT INTO `station` VALUES ('22', 'stationffc5649d98387489ac2c65022', '2016-06-05 20:13:26', '华府大道', 'HFDD', '104.075945000000', '30.531749000000');
INSERT INTO `station` VALUES ('23', 'stationffc5649d98387489ac2c65023', '2016-06-05 20:13:26', '四河', 'SH', '104.076243000000', '30.523343000000');
INSERT INTO `station` VALUES ('24', 'stationffc5649d98387489ac2c65024', '2016-06-05 20:13:26', '广都', 'GD', '104.083698000000', '30.516995000000');
INSERT INTO `station` VALUES ('25', 'stationffc5649d98387489ac2c65025', '2016-06-05 20:13:26', '五根松', '', null, null);
INSERT INTO `station` VALUES ('26', 'stationffc5649d98387489ac2c65026', '2016-06-05 20:13:26', '华阳', '', null, null);
INSERT INTO `station` VALUES ('27', 'stationffc5649d98387489ac2c65027', '2016-06-05 20:13:26', '牧华路', '', null, null);
INSERT INTO `station` VALUES ('28', 'stationffc5649d98387489ac2c65028', '2016-06-05 20:13:26', '广福', '', null, null);
INSERT INTO `station` VALUES ('29', 'stationffc5649d98387489ac2c65029', '2016-06-05 20:13:26', '香山', '', null, null);
INSERT INTO `station` VALUES ('30', 'stationffc5649d98387489ac2c65030', '2016-06-05 20:13:26', '段家山', '', null, null);
INSERT INTO `station` VALUES ('31', 'stationffc5649d98387489ac2c65031', '2016-06-05 20:13:26', '武汉路', '', null, null);
INSERT INTO `station` VALUES ('32', 'stationffc5649d98387489ac2c65032', '2016-06-05 20:13:26', '宁波路', '', null, null);
INSERT INTO `station` VALUES ('33', 'stationffc5649d98387489ac2c65033', '2016-06-05 20:13:26', '福州路', '', null, null);
INSERT INTO `station` VALUES ('34', 'stationffc5649d98387489ac2c65034', '2016-06-05 20:13:26', '广州路', '', null, null);
INSERT INTO `station` VALUES ('35', 'stationffc5649d98387489ac2c65035', '2016-06-05 20:13:26', '兴隆湖', '', null, null);
INSERT INTO `station` VALUES ('36', 'stationffc5649d98387489ac2c65036', '2016-06-05 20:13:26', '天府新站', '', null, null);
INSERT INTO `station` VALUES ('37', 'stationffc5649d98387489ac2c65037', '2016-06-05 20:13:26', '龙泉驿', 'LQY', '104.274536000000', '30.569028000000');
INSERT INTO `station` VALUES ('38', 'stationffc5649d98387489ac2c65038', '2016-06-05 20:13:26', '龙平路', 'LPL', '104.265310000000', '30.579152000000');
INSERT INTO `station` VALUES ('39', 'stationffc5649d98387489ac2c65039', '2016-06-05 20:13:26', '书房', 'SF', '104.248696000000', '30.582451000000');
INSERT INTO `station` VALUES ('40', 'stationffc5649d98387489ac2c65040', '2016-06-05 20:13:26', '界牌', 'JP', '104.231399000000', '30.588636000000');
INSERT INTO `station` VALUES ('41', 'stationffc5649d98387489ac2c65041', '2016-06-05 20:13:26', '连山坡', 'LSP', '104.219613000000', '30.593238000000');
INSERT INTO `station` VALUES ('42', 'stationffc5649d98387489ac2c65042', '2016-06-05 20:13:26', '大面铺', 'DMP', '104.207654000000', '30.598408000000');
INSERT INTO `station` VALUES ('44', 'stationffc5649d98387489ac2c65044', '2016-06-05 20:13:26', '成都行政学院', 'CDHZXY', '104.176885000000', '30.603527000000');
INSERT INTO `station` VALUES ('45', 'stationffc5649d98387489ac2c65045', '2016-06-05 20:13:26', '洪河', 'HH', '104.164147000000', '30.611102000000');
INSERT INTO `station` VALUES ('46', 'stationffc5649d98387489ac2c65046', '2016-06-05 20:13:26', '惠王陵', 'HWL', '104.158678000000', '30.618267000000');
INSERT INTO `station` VALUES ('47', 'stationffc5649d98387489ac2c65047', '2016-06-05 20:13:26', '成渝立交', 'CYLJ', '104.153650000000', '30.631289000000');
INSERT INTO `station` VALUES ('48', 'stationffc5649d98387489ac2c65048', '2016-06-05 20:13:26', '成都东客站', 'CDDKZ', '104.147422000000', '30.635348000000');
INSERT INTO `station` VALUES ('49', 'stationffc5649d98387489ac2c65049', '2016-06-05 20:13:26', '塔子山公园', 'TZSGY', '104.129491000000', '30.635471000000');
INSERT INTO `station` VALUES ('50', 'stationffc5649d98387489ac2c65050', '2016-06-05 20:13:26', '东大路', 'DDL', '104.118789000000', '30.639294000000');
INSERT INTO `station` VALUES ('51', 'stationffc5649d98387489ac2c65051', '2016-06-05 20:13:26', '牛市口', 'NSK', '104.114368000000', '30.643680000000');
INSERT INTO `station` VALUES ('52', 'stationffc5649d98387489ac2c65052', '2016-06-05 20:13:26', '牛王庙', 'NWM', '104.101116000000', '30.650728000000');
INSERT INTO `station` VALUES ('53', 'stationffc5649d98387489ac2c65053', '2016-06-05 20:13:26', '东门大桥', 'DMDQ', '104.093881000000', '30.654082000000');
INSERT INTO `station` VALUES ('54', 'stationffc5649d98387489ac2c65054', '2016-06-05 20:13:26', '春熙路', 'CXL', '104.086071000000', '30.659316000000');
INSERT INTO `station` VALUES ('55', 'stationffc5649d98387489ac2c65055', '2016-06-05 20:13:27', '人民公园', 'RMGY', '104.063036000000', '30.665544000000');
INSERT INTO `station` VALUES ('56', 'stationffc5649d98387489ac2c65056', '2016-06-05 20:13:27', '通惠门', 'THM', '104.054968000000', '30.668420000000');
INSERT INTO `station` VALUES ('57', 'stationffc5649d98387489ac2c65057', '2016-06-05 20:13:27', '中医大省医院', 'ZYDSYY', '104.047243000000', '30.672248000000');
INSERT INTO `station` VALUES ('58', 'stationffc5649d98387489ac2c65058', '2016-06-05 20:13:27', '白果林', 'BGL', '104.039619000000', '30.679958000000');
INSERT INTO `station` VALUES ('59', 'stationffc5649d98387489ac2c65059', '2016-06-05 20:13:27', '蜀汉路东', 'SHLD', '104.035524000000', '30.689167000000');
INSERT INTO `station` VALUES ('60', 'stationffc5649d98387489ac2c65060', '2016-06-05 20:13:27', '一品天下', 'YPTX', '104.026347000000', '30.695035000000');
INSERT INTO `station` VALUES ('61', 'stationffc5649d98387489ac2c65061', '2016-06-05 20:13:27', '羊犀立交', 'YXLJ', '104.018944000000', '30.700234000000');
INSERT INTO `station` VALUES ('62', 'stationffc5649d98387489ac2c65062', '2016-06-05 20:13:27', '茶店子客运站', 'CDZKYZ', '104.019632000000', '30.712269000000');
INSERT INTO `station` VALUES ('63', 'stationffc5649d98387489ac2c65063', '2016-06-05 20:13:27', '迎宾大道', 'YBDD', '104.020011000000', '30.719324000000');
INSERT INTO `station` VALUES ('64', 'stationffc5649d98387489ac2c65064', '2016-06-05 20:13:27', '金科北路', 'JKBL', '104.013327000000', '30.726628000000');
INSERT INTO `station` VALUES ('65', 'stationffc5649d98387489ac2c65065', '2016-06-05 20:13:27', '金周路', 'JZL', '104.002761000000', '30.727484000000');
INSERT INTO `station` VALUES ('66', 'stationffc5649d98387489ac2c65066', '2016-06-05 20:13:27', '百草路', 'BCL', '103.985905000000', '30.738800000000');
INSERT INTO `station` VALUES ('67', 'stationffc5649d98387489ac2c65067', '2016-06-05 20:13:27', '天河路', 'THL', '103.982891000000', '30.751585000000');
INSERT INTO `station` VALUES ('68', 'stationffc5649d98387489ac2c65068', '2016-06-05 20:13:27', '犀浦', 'XP', '103.978059000000', '30.763697000000');
INSERT INTO `station` VALUES ('69', 'stationffc5649d98387489ac2c65069', '2016-06-05 20:13:27', '双流西', '', null, null);
INSERT INTO `station` VALUES ('70', 'stationffc5649d98387489ac2c65070', '2016-06-05 20:13:27', '三里坝', '', null, null);
INSERT INTO `station` VALUES ('71', 'stationffc5649d98387489ac2c65071', '2016-06-05 20:13:27', '双流广场', '', null, null);
INSERT INTO `station` VALUES ('72', 'stationffc5649d98387489ac2c65072', '2016-06-05 20:13:27', '东升', '', null, null);
INSERT INTO `station` VALUES ('73', 'stationffc5649d98387489ac2c65073', '2016-06-05 20:13:27', '迎春桥', '', null, null);
INSERT INTO `station` VALUES ('74', 'stationffc5649d98387489ac2c65074', '2016-06-05 20:13:27', '航都大道', '', null, null);
INSERT INTO `station` VALUES ('75', 'stationffc5649d98387489ac2c65075', '2016-06-05 20:13:27', '龙桥路', '', null, null);
INSERT INTO `station` VALUES ('76', 'stationffc5649d98387489ac2c65076', '2016-06-05 20:13:27', '双凤桥', '', null, null);
INSERT INTO `station` VALUES ('77', 'stationffc5649d98387489ac2c65077', '2016-06-05 20:13:27', '武青南路', '', null, null);
INSERT INTO `station` VALUES ('78', 'stationffc5649d98387489ac2c65078', '2016-06-05 20:13:27', '武侯立交', '', null, null);
INSERT INTO `station` VALUES ('79', 'stationffc5649d98387489ac2c65079', '2016-06-05 20:13:27', '川藏立交', '', null, null);
INSERT INTO `station` VALUES ('80', 'stationffc5649d98387489ac2c65080', '2016-06-05 20:13:27', '太平园', 'TPY', '104.027275000000', '30.631102000000');
INSERT INTO `station` VALUES ('81', 'stationffc5649d98387489ac2c65081', '2016-06-05 20:13:27', '红牌楼', 'HPL', '104.037419000000', '30.641851000000');
INSERT INTO `station` VALUES ('82', 'stationffc5649d98387489ac2c65082', '2016-06-05 20:13:27', '高升桥', 'GSQ', '104.048592000000', '30.646708000000');
INSERT INTO `station` VALUES ('83', 'stationffc5649d98387489ac2c65083', '2016-06-05 20:13:27', '衣冠庙', 'YGM', '104.057836000000', '30.641364000000');
INSERT INTO `station` VALUES ('84', 'stationffc5649d98387489ac2c65084', '2016-06-05 20:13:27', '磨子桥', 'MZQ', '104.082916000000', '30.642987000000');
INSERT INTO `station` VALUES ('85', 'stationffc5649d98387489ac2c65085', '2016-06-05 20:13:27', '新南门', 'XNM', '104.081877000000', '30.650431000000');
INSERT INTO `station` VALUES ('86', 'stationffc5649d98387489ac2c65086', '2016-06-05 20:13:27', '市二医院', 'SEYY', '104.090635000000', '30.666661000000');
INSERT INTO `station` VALUES ('87', 'stationffc5649d98387489ac2c65087', '2016-06-05 20:13:27', '红星桥', 'HXQ', '104.097214000000', '30.676778000000');
INSERT INTO `station` VALUES ('88', 'stationffc5649d98387489ac2c65088', '2016-06-05 20:13:27', '前锋路', 'QFL', '104.098462000000', '30.684179000000');
INSERT INTO `station` VALUES ('89', 'stationffc5649d98387489ac2c65089', '2016-06-05 20:13:27', '李家沱', 'LJT', '104.101313000000', '30.695172000000');
INSERT INTO `station` VALUES ('90', 'stationffc5649d98387489ac2c65090', '2016-06-05 20:13:27', '驷马桥', 'SMQ', '104.100122000000', '30.700907000000');
INSERT INTO `station` VALUES ('91', 'stationffc5649d98387489ac2c65091', '2016-06-05 20:13:27', '昭觉寺南路', 'ZJSNL', '104.102954000000', '30.707640000000');
INSERT INTO `station` VALUES ('92', 'stationffc5649d98387489ac2c65092', '2016-06-05 20:13:27', '动物园', 'DWY', '104.109973000000', '30.719018000000');
INSERT INTO `station` VALUES ('93', 'stationffc5649d98387489ac2c65093', '2016-06-05 20:13:27', '熊猫大道', 'XMDD', '104.115458000000', '30.732507000000');
INSERT INTO `station` VALUES ('94', 'stationffc5649d98387489ac2c65094', '2016-06-05 20:13:27', '军区总医院', 'JQZYY', '104.120321000000', '30.750586000000');
INSERT INTO `station` VALUES ('95', 'stationffc5649d98387489ac2c65095', '2016-06-05 20:13:27', '植物园', '', null, null);
INSERT INTO `station` VALUES ('96', 'stationffc5649d98387489ac2c65096', '2016-06-05 20:13:27', '金华寺东路', '', null, null);
INSERT INTO `station` VALUES ('97', 'stationffc5649d98387489ac2c65097', '2016-06-05 20:13:27', '三河场', '', null, null);
INSERT INTO `station` VALUES ('98', 'stationffc5649d98387489ac2c65098', '2016-06-05 20:13:27', '锦水河', '', null, null);
INSERT INTO `station` VALUES ('99', 'stationffc5649d98387489ac2c65099', '2016-06-05 20:13:27', '团结新区', '', null, null);
INSERT INTO `station` VALUES ('100', 'stationffc5649d98387489ac2c65100', '2016-06-05 20:13:27', '马超西路', '', null, null);
INSERT INTO `station` VALUES ('101', 'stationffc5649d98387489ac2c65101', '2016-06-05 20:13:27', '钟楼', '', null, null);
INSERT INTO `station` VALUES ('102', 'stationffc5649d98387489ac2c65102', '2016-06-05 20:13:27', '石油大学', '', null, null);
INSERT INTO `station` VALUES ('103', 'stationffc5649d98387489ac2c65103', '2016-06-05 20:13:27', '成都医学院', '', null, null);
INSERT INTO `station` VALUES ('104', 'stationffc5649d98387489ac2c65104', '2016-06-05 20:13:27', '万盛', 'WS', '103.825309000000', '30.681533000000');
INSERT INTO `station` VALUES ('105', 'stationffc5649d98387489ac2c65105', '2016-06-05 20:13:27', '杨柳河', 'YLH', '103.836996000000', '30.686476000000');
INSERT INTO `station` VALUES ('106', 'stationffc5649d98387489ac2c65106', '2016-06-05 20:13:27', '凤溪', 'FX', '103.850602000000', '30.692424000000');
INSERT INTO `station` VALUES ('107', 'stationffc5649d98387489ac2c65107', '2016-06-05 20:13:27', '南熏大道', 'NXDD', '103.855345000000', '30.696337000000');
INSERT INTO `station` VALUES ('108', 'stationffc5649d98387489ac2c65108', '2016-06-05 20:13:27', '光华公园', 'GHGY', '103.867251000000', '30.690700000000');
INSERT INTO `station` VALUES ('109', 'stationffc5649d98387489ac2c65109', '2016-06-05 20:13:27', '涌泉', 'YQ', '103.878341000000', '30.684909000000');
INSERT INTO `station` VALUES ('110', 'stationffc5649d98387489ac2c65110', '2016-06-05 20:13:27', '凤凰大街', 'FHDJ', '103.897316000000', '30.683396000000');
INSERT INTO `station` VALUES ('111', 'stationffc5649d98387489ac2c65111', '2016-06-05 20:13:27', '马场坝', 'MCB', '103.912777000000', '30.684116000000');
INSERT INTO `station` VALUES ('112', 'stationffc5649d98387489ac2c65112', '2016-06-05 20:13:27', '非遗博览园', 'FYBLY', '103.929428000000', '30.680120000000');
INSERT INTO `station` VALUES ('113', 'stationffc5649d98387489ac2c65113', '2016-06-05 20:13:27', '蔡桥', 'CQ', '103.951193000000', '30.675962000000');
INSERT INTO `station` VALUES ('114', 'stationffc5649d98387489ac2c65114', '2016-06-05 20:13:27', '中坝', 'ZB', '103.970435000000', '30.679178000000');
INSERT INTO `station` VALUES ('115', 'stationffc5649d98387489ac2c65115', '2016-06-05 20:13:27', '成都西站', 'CDXZ', '103.984690000000', '30.689505000000');
INSERT INTO `station` VALUES ('116', 'stationffc5649d98387489ac2c65116', '2016-06-05 20:13:27', '清江西路', 'QJXL', '104.005227000000', '30.682350000000');
INSERT INTO `station` VALUES ('117', 'stationffc5649d98387489ac2c65117', '2016-06-05 20:13:27', '文化宫', 'WHG', '104.016188000000', '30.678259000000');
INSERT INTO `station` VALUES ('118', 'stationffc5649d98387489ac2c65118', '2016-06-05 20:13:27', '西南财大', 'XNCD', '104.027765000000', '30.676435000000');
INSERT INTO `station` VALUES ('119', 'stationffc5649d98387489ac2c65119', '2016-06-05 20:13:27', '草堂北路', 'CTBL', '104.035794000000', '30.675043000000');
INSERT INTO `station` VALUES ('120', 'stationffc5649d98387489ac2c65120', '2016-06-05 20:13:27', '宽窄巷子', 'KZXZ', '104.056619000000', '30.671502000000');
INSERT INTO `station` VALUES ('121', 'stationffc5649d98387489ac2c65121', '2016-06-05 20:13:27', '太升南路', 'TSNL', '104.083615000000', '30.670442000000');
INSERT INTO `station` VALUES ('122', 'stationffc5649d98387489ac2c65122', '2016-06-05 20:13:27', '市二医院', 'SEYY', '104.090635000000', '30.666661000000');
INSERT INTO `station` VALUES ('123', 'stationffc5649d98387489ac2c65123', '2016-06-05 20:13:27', '玉双路', 'YSL', '104.104871000000', '30.659860000000');
INSERT INTO `station` VALUES ('124', 'stationffc5649d98387489ac2c65124', '2016-06-05 20:13:27', '双桥路', 'SQL', '104.119384000000', '30.652975000000');
INSERT INTO `station` VALUES ('125', 'stationffc5649d98387489ac2c65125', '2016-06-05 20:13:27', '万年场', 'WNC', '104.125858000000', '30.650116000000');
INSERT INTO `station` VALUES ('126', 'stationffc5649d98387489ac2c65126', '2016-06-05 20:13:28', '槐树店', 'HSD', '104.145941000000', '30.654980000000');
INSERT INTO `station` VALUES ('127', 'stationffc5649d98387489ac2c65127', '2016-06-05 20:13:28', '来龙', 'LL', '104.168327000000', '30.653831000000');
INSERT INTO `station` VALUES ('128', 'stationffc5649d98387489ac2c65128', '2016-06-05 20:13:28', '十陵', 'SL', '104.179669000000', '30.652231000000');
INSERT INTO `station` VALUES ('129', 'stationffc5649d98387489ac2c65129', '2016-06-05 20:13:28', '成都大学', '', null, null);
INSERT INTO `station` VALUES ('130', 'stationffc5649d98387489ac2c65130', '2016-06-05 20:13:28', '明蜀王陵', '', null, null);
INSERT INTO `station` VALUES ('131', 'stationffc5649d98387489ac2c65131', '2016-06-05 20:13:28', '西河', '', null, null);
INSERT INTO `station` VALUES ('132', 'stationffc5649d98387489ac2c65132', '2016-06-05 20:13:28', '华桂路', '', null, null);
INSERT INTO `station` VALUES ('133', 'stationffc5649d98387489ac2c65133', '2016-06-05 20:13:28', '柏水场', '', null, null);
INSERT INTO `station` VALUES ('134', 'stationffc5649d98387489ac2c65134', '2016-06-05 20:13:28', '廖家湾', '', null, null);
INSERT INTO `station` VALUES ('135', 'stationffc5649d98387489ac2c65135', '2016-06-05 20:13:28', '北部商贸城', '', null, null);
INSERT INTO `station` VALUES ('136', 'stationffc5649d98387489ac2c65136', '2016-06-05 20:13:28', '幸福桥', '', null, null);
INSERT INTO `station` VALUES ('137', 'stationffc5649d98387489ac2c65137', '2016-06-05 20:13:28', '九道堰', '', null, null);
INSERT INTO `station` VALUES ('138', 'stationffc5649d98387489ac2c65138', '2016-06-05 20:13:28', '杜家碾', '', null, null);
INSERT INTO `station` VALUES ('139', 'stationffc5649d98387489ac2c65139', '2016-06-05 20:13:28', '大丰', '', null, null);
INSERT INTO `station` VALUES ('140', 'stationffc5649d98387489ac2c65140', '2016-06-05 20:13:28', '石犀公园', '', null, null);
INSERT INTO `station` VALUES ('141', 'stationffc5649d98387489ac2c65141', '2016-06-05 20:13:28', '皇花园', '', null, null);
INSERT INTO `station` VALUES ('142', 'stationffc5649d98387489ac2c65142', '2016-06-05 20:13:28', '古柏', '', null, null);
INSERT INTO `station` VALUES ('143', 'stationffc5649d98387489ac2c65143', '2016-06-05 20:13:28', '泉水路', '', null, null);
INSERT INTO `station` VALUES ('144', 'stationffc5649d98387489ac2c65144', '2016-06-05 20:13:28', '洞子口', '', null, null);
INSERT INTO `station` VALUES ('145', 'stationffc5649d98387489ac2c65145', '2016-06-05 20:13:28', '福宁路', '', null, null);
INSERT INTO `station` VALUES ('146', 'stationffc5649d98387489ac2c65146', '2016-06-05 20:13:28', '五块石', '', null, null);
INSERT INTO `station` VALUES ('147', 'stationffc5649d98387489ac2c65147', '2016-06-05 20:13:28', '赛云台', '', null, null);
INSERT INTO `station` VALUES ('148', 'stationffc5649d98387489ac2c65148', '2016-06-05 20:13:28', '西北桥', '', null, null);
INSERT INTO `station` VALUES ('149', 'stationffc5649d98387489ac2c65149', '2016-06-05 20:13:28', '花牌坊', '', null, null);
INSERT INTO `station` VALUES ('150', 'stationffc5649d98387489ac2c65150', '2016-06-05 20:13:28', '抚琴', '', null, null);
INSERT INTO `station` VALUES ('151', 'stationffc5649d98387489ac2c65151', '2016-06-05 20:13:28', '青羊宫', '', null, null);
INSERT INTO `station` VALUES ('152', 'stationffc5649d98387489ac2c65152', '2016-06-05 20:13:28', '省骨科医院', '', null, null);
INSERT INTO `station` VALUES ('153', 'stationffc5649d98387489ac2c65153', '2016-06-05 20:13:28', '肖家河', '', null, null);
INSERT INTO `station` VALUES ('154', 'stationffc5649d98387489ac2c65154', '2016-06-05 20:13:28', '九兴大道', '', null, null);
INSERT INTO `station` VALUES ('155', 'stationffc5649d98387489ac2c65155', '2016-06-05 20:13:28', '石羊立交', '', null, null);
INSERT INTO `station` VALUES ('156', 'stationffc5649d98387489ac2c65156', '2016-06-05 20:13:28', '市一医院', '', null, null);
INSERT INTO `station` VALUES ('157', 'stationffc5649d98387489ac2c65157', '2016-06-05 20:13:28', '交子大道', '', null, null);
INSERT INTO `station` VALUES ('158', 'stationffc5649d98387489ac2c65158', '2016-06-05 20:13:28', '锦城大道', '', null, null);
INSERT INTO `station` VALUES ('159', 'stationffc5649d98387489ac2c65159', '2016-06-05 20:13:28', '锦城湖', '', null, null);
INSERT INTO `station` VALUES ('160', 'stationffc5649d98387489ac2c65160', '2016-06-05 20:13:28', '大源', '', null, null);
INSERT INTO `station` VALUES ('161', 'stationffc5649d98387489ac2c65161', '2016-06-05 20:13:28', '民乐', '', null, null);
INSERT INTO `station` VALUES ('162', 'stationffc5649d98387489ac2c65162', '2016-06-05 20:13:28', '骑龙', '', null, null);
INSERT INTO `station` VALUES ('163', 'stationffc5649d98387489ac2c65163', '2016-06-05 20:13:28', '警官学院', '', null, null);
INSERT INTO `station` VALUES ('164', 'stationffc5649d98387489ac2c65164', '2016-06-05 20:13:28', '二江寺', '', null, null);
INSERT INTO `station` VALUES ('165', 'stationffc5649d98387489ac2c65165', '2016-06-05 20:13:28', '南湖立交', '', null, null);
INSERT INTO `station` VALUES ('166', 'stationffc5649d98387489ac2c65166', '2016-06-05 20:13:28', '高峰', '', null, null);
INSERT INTO `station` VALUES ('167', 'stationffc5649d98387489ac2c65167', '2016-06-05 20:13:28', '龙马路', '', null, null);
INSERT INTO `station` VALUES ('168', 'stationffc5649d98387489ac2c65168', '2016-06-05 20:13:28', '回龙路', '', null, null);
INSERT INTO `station` VALUES ('169', 'stationffc5649d98387489ac2c65169', '2016-06-09 22:14:59', '北站西二路', 'BZXEL', '104.072743000000', '30.700686000000');
INSERT INTO `station` VALUES ('170', 'stationffc5649d98387489ac2c65170', '2016-06-09 22:17:07', '八里小区', 'BLXQ', '104.114111000000', '30.700048000000');
INSERT INTO `station` VALUES ('171', 'stationffc5649d98387489ac2c65171', '2016-06-09 22:17:07', '东区医院', 'DQYY', '104.125442000000', '30.692299000000');
INSERT INTO `station` VALUES ('172', 'stationffc5649d98387489ac2c65172', '2016-06-09 22:17:07', '二仙桥', 'EXQ', '104.133068000000', '30.686601000000');
INSERT INTO `station` VALUES ('173', 'stationffc5649d98387489ac2c65173', '2016-06-09 22:17:07', '成都理工大学', 'CDLGDX', '104.143084000000', '30.677998000000');
INSERT INTO `station` VALUES ('174', 'stationffc5649d98387489ac2c65174', '2016-06-09 22:17:07', '成华大道', 'CHDD', '104.143158000000', '30.671524000000');
INSERT INTO `station` VALUES ('175', 'stationffc5649d98387489ac2c65175', '2016-06-09 22:17:07', '崔家店', 'CJD', '104.144963000000', '30.660961000000');
INSERT INTO `station` VALUES ('176', 'stationffc5649d98387489ac2c65176', '2016-06-09 22:17:07', '建材南路', 'JCNL', '104.146413000000', '30.645405000000');
INSERT INTO `station` VALUES ('177', 'stationffc5649d98387489ac2c65177', '2016-06-09 22:17:07', '沙河铺', 'SHP', '104.138917000000', '30.627837000000');
INSERT INTO `station` VALUES ('178', 'stationffc5649d98387489ac2c65178', '2016-06-09 22:17:07', '狮子山', 'SZS', '104.130549000000', '30.622109000000');
INSERT INTO `station` VALUES ('179', 'stationffc5649d98387489ac2c65179', '2016-06-09 22:17:07', '川师', 'CS', '104.121616000000', '30.615213000000');
INSERT INTO `station` VALUES ('180', 'stationffc5649d98387489ac2c65180', '2016-06-09 22:17:07', '琉璃场', 'LLC', '104.105622000000', '30.613094000000');
INSERT INTO `station` VALUES ('181', 'stationffc5649d98387489ac2c65181', '2016-06-09 22:17:07', '科华南路', 'KHNL', '104.083866000000', '30.610434000000');
INSERT INTO `station` VALUES ('182', 'stationffc5649d98387489ac2c65182', '2016-06-09 22:17:07', '神仙树', 'SXS', '104.055661000000', '30.615331000000');
INSERT INTO `station` VALUES ('183', 'stationffc5649d98387489ac2c65183', '2016-06-09 22:17:07', '神仙树西', 'SXSX', '104.040579000000', '30.622692000000');
INSERT INTO `station` VALUES ('184', 'stationffc5649d98387489ac2c65184', '2016-06-09 22:17:07', '武侯双楠', 'WHSN', '104.019942000000', '30.642273000000');
INSERT INTO `station` VALUES ('185', 'stationffc5649d98387489ac2c65185', '2016-06-09 22:17:07', '清水河大桥', 'QSHDQ', '104.014924000000', '30.658013000000');
INSERT INTO `station` VALUES ('186', 'stationffc5649d98387489ac2c65186', '2016-06-09 22:17:07', '光华村', 'GHC', '104.014635000000', '30.667439000000');
INSERT INTO `station` VALUES ('187', 'stationffc5649d98387489ac2c65187', '2016-06-09 22:17:07', '金沙遗址', 'JSYZ', '104.019720000000', '30.684665000000');
INSERT INTO `station` VALUES ('188', 'stationffc5649d98387489ac2c65188', '2016-06-09 22:17:07', '茶店子路', 'CDZL', '104.036551000000', '30.702029000000');
INSERT INTO `station` VALUES ('189', 'stationffc5649d98387489ac2c65189', '2016-06-09 22:17:08', '花照壁', 'HZB', '104.047873000000', '30.702586000000');
INSERT INTO `station` VALUES ('190', 'stationffc5649d98387489ac2c65190', '2016-06-09 22:17:08', '西南交大', 'XNJD', '104.056281000000', '30.701126000000');
INSERT INTO `station` VALUES ('191', 'stationffc5649d98387489ac2c65191', '2016-06-09 22:44:28', '九里堤路口', 'JLDLK', '104.063599000000', '30.700886000000');





/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : bms_test

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-06-09 22:59:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for metro_line_station
-- ----------------------------
DROP TABLE IF EXISTS `metro_line_station`;
CREATE TABLE `metro_line_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `metro_line_id` int(11) DEFAULT NULL,
  `station_id` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of metro_line_station
-- ----------------------------
INSERT INTO `metro_line_station` VALUES ('1', 'metrolinestationa8cd57a538e75001', '2016-06-05 20:08:51', '1', '1', '1', 'PLANNING');
INSERT INTO `metro_line_station` VALUES ('2', 'metrolinestationa8cd57a538e75002', '2016-06-05 20:08:51', '1', '2', '2', 'PLANNING');
INSERT INTO `metro_line_station` VALUES ('3', 'metrolinestationa8cd57a538e75003', '2016-06-05 20:08:51', '1', '3', '3', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('4', 'metrolinestationa8cd57a538e75004', '2016-06-05 20:08:51', '1', '4', '4', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('5', 'metrolinestationa8cd57a538e75005', '2016-06-05 20:08:51', '1', '5', '5', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('6', 'metrolinestationa8cd57a538e75006', '2016-06-05 20:08:51', '1', '6', '6', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('7', 'metrolinestationa8cd57a538e75007', '2016-06-05 20:08:51', '1', '7', '7', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('8', 'metrolinestationa8cd57a538e75008', '2016-06-05 20:08:51', '1', '8', '8', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('9', 'metrolinestationa8cd57a538e75009', '2016-06-05 20:08:51', '1', '9', '9', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('10', 'metrolinestationa8cd57a538e75010', '2016-06-05 20:08:51', '1', '10', '10', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('11', 'metrolinestationa8cd57a538e75011', '2016-06-05 20:08:51', '1', '11', '11', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('12', 'metrolinestationa8cd57a538e75012', '2016-06-05 20:08:51', '1', '12', '12', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('13', 'metrolinestationa8cd57a538e75013', '2016-06-05 20:08:51', '1', '13', '13', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('14', 'metrolinestationa8cd57a538e75014', '2016-06-05 20:08:51', '1', '14', '14', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('15', 'metrolinestationa8cd57a538e75015', '2016-06-05 20:08:52', '1', '15', '15', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('16', 'metrolinestationa8cd57a538e75016', '2016-06-05 20:08:52', '1', '16', '16', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('17', 'metrolinestationa8cd57a538e75017', '2016-06-05 20:08:52', '1', '17', '17', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('18', 'metrolinestationa8cd57a538e75018', '2016-06-05 20:08:52', '1', '18', '18', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('19', 'metrolinestationa8cd57a538e75019', '2016-06-05 20:08:52', '1', '19', '19', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('20', 'metrolinestationa8cd57a538e75020', '2016-06-05 20:08:52', '1', '20', '20', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('21', 'metrolinestationa8cd57a538e75021', '2016-06-05 20:08:52', '1', '21', '21', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('22', 'metrolinestationa8cd57a538e75022', '2016-06-05 20:08:52', '1', '22', '22', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('23', 'metrolinestationa8cd57a538e75023', '2016-06-05 20:08:52', '1', '23', '23', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('24', 'metrolinestationa8cd57a538e75024', '2016-06-05 20:08:52', '1', '24', '24', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('25', 'metrolinestationa8cd57a538e75025', '2016-06-05 20:08:52', '1', '25', '25', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('26', 'metrolinestationa8cd57a538e75026', '2016-06-05 20:08:52', '1', '26', '26', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('27', 'metrolinestationa8cd57a538e75027', '2016-06-05 20:08:52', '1', '27', '27', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('28', 'metrolinestationa8cd57a538e75028', '2016-06-05 20:08:52', '1', '28', '28', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('29', 'metrolinestationa8cd57a538e75029', '2016-06-05 20:08:52', '1', '29', '29', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('30', 'metrolinestationa8cd57a538e75030', '2016-06-05 20:08:52', '1', '30', '30', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('31', 'metrolinestationa8cd57a538e75031', '2016-06-05 20:08:52', '1', '31', '31', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('32', 'metrolinestationa8cd57a538e75032', '2016-06-05 20:08:52', '1', '32', '32', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('33', 'metrolinestationa8cd57a538e75033', '2016-06-05 20:08:52', '1', '33', '33', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('34', 'metrolinestationa8cd57a538e75034', '2016-06-05 20:08:52', '1', '34', '34', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('35', 'metrolinestationa8cd57a538e75035', '2016-06-05 20:08:52', '1', '35', '35', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('36', 'metrolinestationa8cd57a538e75036', '2016-06-05 20:08:52', '1', '36', '36', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('37', 'metrolinestationa8cd57a538e75037', '2016-06-05 20:08:52', '2', '37', '1', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('38', 'metrolinestationa8cd57a538e75038', '2016-06-05 20:08:52', '2', '38', '2', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('39', 'metrolinestationa8cd57a538e75039', '2016-06-05 20:08:52', '2', '39', '3', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('40', 'metrolinestationa8cd57a538e75040', '2016-06-05 20:08:52', '2', '40', '4', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('41', 'metrolinestationa8cd57a538e75041', '2016-06-05 20:08:52', '2', '41', '5', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('42', 'metrolinestationa8cd57a538e75042', '2016-06-05 20:08:52', '2', '42', '6', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('44', 'metrolinestationa8cd57a538e75044', '2016-06-05 20:08:52', '2', '44', '8', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('45', 'metrolinestationa8cd57a538e75045', '2016-06-05 20:08:52', '2', '45', '9', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('46', 'metrolinestationa8cd57a538e75046', '2016-06-05 20:08:52', '2', '46', '10', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('47', 'metrolinestationa8cd57a538e75047', '2016-06-05 20:08:52', '2', '47', '11', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('48', 'metrolinestationa8cd57a538e75048', '2016-06-05 20:08:52', '2', '48', '12', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('49', 'metrolinestationa8cd57a538e75049', '2016-06-05 20:08:52', '2', '49', '13', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('50', 'metrolinestationa8cd57a538e75050', '2016-06-05 20:08:52', '2', '50', '14', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('51', 'metrolinestationa8cd57a538e75051', '2016-06-05 20:08:52', '2', '51', '15', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('52', 'metrolinestationa8cd57a538e75052', '2016-06-05 20:08:52', '2', '52', '16', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('53', 'metrolinestationa8cd57a538e75053', '2016-06-05 20:08:52', '2', '53', '17', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('54', 'metrolinestationa8cd57a538e75054', '2016-06-05 20:08:52', '2', '54', '18', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('55', 'metrolinestationa8cd57a538e75055', '2016-06-05 20:08:52', '2', '8', '19', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('56', 'metrolinestationa8cd57a538e75056', '2016-06-05 20:08:52', '2', '55', '20', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('57', 'metrolinestationa8cd57a538e75057', '2016-06-05 20:08:52', '2', '56', '21', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('58', 'metrolinestationa8cd57a538e75058', '2016-06-05 20:08:52', '2', '57', '22', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('59', 'metrolinestationa8cd57a538e75059', '2016-06-05 20:08:52', '2', '58', '23', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('60', 'metrolinestationa8cd57a538e75060', '2016-06-05 20:08:52', '2', '59', '24', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('61', 'metrolinestationa8cd57a538e75061', '2016-06-05 20:08:52', '2', '60', '25', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('62', 'metrolinestationa8cd57a538e75062', '2016-06-05 20:08:52', '2', '61', '26', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('63', 'metrolinestationa8cd57a538e75063', '2016-06-05 20:08:52', '2', '62', '27', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('64', 'metrolinestationa8cd57a538e75064', '2016-06-05 20:08:52', '2', '63', '28', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('65', 'metrolinestationa8cd57a538e75065', '2016-06-05 20:08:52', '2', '64', '29', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('66', 'metrolinestationa8cd57a538e75066', '2016-06-05 20:08:52', '2', '65', '30', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('67', 'metrolinestationa8cd57a538e75067', '2016-06-05 20:08:52', '2', '66', '31', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('68', 'metrolinestationa8cd57a538e75068', '2016-06-05 20:08:52', '2', '67', '32', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('69', 'metrolinestationa8cd57a538e75069', '2016-06-05 20:08:52', '2', '68', '33', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('70', 'metrolinestationa8cd57a538e75070', '2016-06-05 20:08:52', '3', '69', '1', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('71', 'metrolinestationa8cd57a538e75071', '2016-06-05 20:08:52', '3', '70', '2', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('72', 'metrolinestationa8cd57a538e75072', '2016-06-05 20:08:52', '3', '71', '3', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('73', 'metrolinestationa8cd57a538e75073', '2016-06-05 20:08:52', '3', '72', '4', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('74', 'metrolinestationa8cd57a538e75074', '2016-06-05 20:08:52', '3', '73', '5', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('75', 'metrolinestationa8cd57a538e75075', '2016-06-05 20:08:52', '3', '74', '6', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('76', 'metrolinestationa8cd57a538e75076', '2016-06-05 20:08:52', '3', '75', '7', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('77', 'metrolinestationa8cd57a538e75077', '2016-06-05 20:08:52', '3', '76', '8', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('78', 'metrolinestationa8cd57a538e75078', '2016-06-05 20:08:52', '3', '77', '9', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('79', 'metrolinestationa8cd57a538e75079', '2016-06-05 20:08:52', '3', '78', '10', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('80', 'metrolinestationa8cd57a538e75080', '2016-06-05 20:08:52', '3', '79', '11', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('81', 'metrolinestationa8cd57a538e75081', '2016-06-05 20:08:52', '3', '80', '12', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('82', 'metrolinestationa8cd57a538e75082', '2016-06-05 20:08:52', '3', '81', '13', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('83', 'metrolinestationa8cd57a538e75083', '2016-06-05 20:08:52', '3', '82', '14', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('84', 'metrolinestationa8cd57a538e75084', '2016-06-05 20:08:52', '3', '83', '15', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('86', 'metrolinestationa8cd57a538e75086', '2016-06-05 20:08:52', '3', '11', '16', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('87', 'metrolinestationa8cd57a538e75087', '2016-06-05 20:08:53', '3', '84', '17', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('88', 'metrolinestationa8cd57a538e75088', '2016-06-05 20:08:53', '3', '85', '18', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('90', 'metrolinestationa8cd57a538e75090', '2016-06-05 20:08:53', '3', '54', '19', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('91', 'metrolinestationa8cd57a538e75091', '2016-06-05 20:08:53', '3', '86', '20', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('92', 'metrolinestationa8cd57a538e75092', '2016-06-05 20:08:53', '3', '87', '21', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('93', 'metrolinestationa8cd57a538e75093', '2016-06-05 20:08:53', '3', '88', '22', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('94', 'metrolinestationa8cd57a538e75094', '2016-06-05 20:08:53', '3', '89', '23', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('95', 'metrolinestationa8cd57a538e75095', '2016-06-05 20:08:53', '3', '90', '24', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('96', 'metrolinestationa8cd57a538e75096', '2016-06-05 20:08:53', '3', '91', '25', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('97', 'metrolinestationa8cd57a538e75097', '2016-06-05 20:08:53', '3', '92', '26', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('98', 'metrolinestationa8cd57a538e75098', '2016-06-05 20:08:53', '3', '93', '27', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('99', 'metrolinestationa8cd57a538e75099', '2016-06-05 20:08:53', '3', '94', '28', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('100', 'metrolinestationa8cd57a538e75100', '2016-06-05 20:08:53', '3', '95', '29', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('101', 'metrolinestationa8cd57a538e75101', '2016-06-05 20:08:53', '3', '96', '30', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('102', 'metrolinestationa8cd57a538e75102', '2016-06-05 20:08:53', '3', '97', '31', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('103', 'metrolinestationa8cd57a538e75103', '2016-06-05 20:08:53', '3', '98', '32', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('104', 'metrolinestationa8cd57a538e75104', '2016-06-05 20:08:53', '3', '99', '33', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('105', 'metrolinestationa8cd57a538e75105', '2016-06-05 20:08:53', '3', '100', '34', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('106', 'metrolinestationa8cd57a538e75106', '2016-06-05 20:08:53', '3', '101', '35', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('107', 'metrolinestationa8cd57a538e75107', '2016-06-05 20:08:53', '3', '102', '36', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('108', 'metrolinestationa8cd57a538e75108', '2016-06-05 20:08:53', '3', '103', '37', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('122', 'metrolinestationa8cd57a538e75122', '2016-06-05 20:08:53', '4', '104', '1', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('123', 'metrolinestationa8cd57a538e75123', '2016-06-05 20:08:53', '4', '105', '2', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('124', 'metrolinestationa8cd57a538e75124', '2016-06-05 20:08:53', '4', '106', '3', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('125', 'metrolinestationa8cd57a538e75125', '2016-06-05 20:08:53', '4', '107', '4', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('126', 'metrolinestationa8cd57a538e75126', '2016-06-05 20:08:53', '4', '108', '5', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('127', 'metrolinestationa8cd57a538e75127', '2016-06-05 20:08:53', '4', '109', '6', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('128', 'metrolinestationa8cd57a538e75128', '2016-06-05 20:08:53', '4', '110', '7', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('129', 'metrolinestationa8cd57a538e75129', '2016-06-05 20:08:53', '4', '111', '8', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('137', 'metrolinestationa8cd57a538e75137', '2016-06-05 20:08:53', '4', '112', '9', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('138', 'metrolinestationa8cd57a538e75138', '2016-06-05 20:08:53', '4', '113', '10', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('139', 'metrolinestationa8cd57a538e75139', '2016-06-05 20:08:53', '4', '114', '11', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('140', 'metrolinestationa8cd57a538e75140', '2016-06-05 20:08:53', '4', '115', '12', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('141', 'metrolinestationa8cd57a538e75141', '2016-06-05 20:08:53', '4', '116', '13', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('142', 'metrolinestationa8cd57a538e75142', '2016-06-05 20:08:53', '4', '117', '14', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('143', 'metrolinestationa8cd57a538e75143', '2016-06-05 20:08:53', '4', '118', '15', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('144', 'metrolinestationa8cd57a538e75144', '2016-06-05 20:08:53', '4', '119', '16', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('152', 'metrolinestationa8cd57a538e75152', '2016-06-05 20:08:53', '4', '57', '17', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('153', 'metrolinestationa8cd57a538e75153', '2016-06-05 20:08:53', '4', '120', '18', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('154', 'metrolinestationa8cd57a538e75154', '2016-06-05 20:08:53', '4', '7', '19', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('155', 'metrolinestationa8cd57a538e75155', '2016-06-05 20:08:53', '4', '121', '20', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('156', 'metrolinestationa8cd57a538e75156', '2016-06-05 20:08:53', '4', '122', '21', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('157', 'metrolinestationa8cd57a538e75157', '2016-06-05 20:08:53', '4', '123', '22', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('158', 'metrolinestationa8cd57a538e75158', '2016-06-05 20:08:53', '4', '124', '23', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('159', 'metrolinestationa8cd57a538e75159', '2016-06-05 20:08:53', '4', '125', '24', 'RUNNING');
INSERT INTO `metro_line_station` VALUES ('162', 'metrolinestationa8cd57a538e75162', '2016-06-05 20:08:53', '4', '126', '25', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('163', 'metrolinestationa8cd57a538e75163', '2016-06-05 20:08:53', '4', '127', '26', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('164', 'metrolinestationa8cd57a538e75164', '2016-06-05 20:08:53', '4', '128', '27', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('165', 'metrolinestationa8cd57a538e75165', '2016-06-05 20:08:53', '4', '129', '28', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('166', 'metrolinestationa8cd57a538e75166', '2016-06-05 20:08:53', '4', '130', '29', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('167', 'metrolinestationa8cd57a538e75167', '2016-06-05 20:08:53', '4', '131', '30', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('169', 'metrolinestationa8cd57a538e75169', '2016-06-05 20:08:53', '5', '132', '1', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('170', 'metrolinestationa8cd57a538e75170', '2016-06-05 20:08:53', '5', '133', '2', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('171', 'metrolinestationa8cd57a538e75171', '2016-06-05 20:08:53', '5', '134', '3', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('172', 'metrolinestationa8cd57a538e75172', '2016-06-05 20:08:53', '5', '135', '4', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('173', 'metrolinestationa8cd57a538e75173', '2016-06-05 20:08:53', '5', '136', '5', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('174', 'metrolinestationa8cd57a538e75174', '2016-06-05 20:08:53', '5', '137', '6', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('175', 'metrolinestationa8cd57a538e75175', '2016-06-05 20:08:53', '5', '138', '7', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('176', 'metrolinestationa8cd57a538e75176', '2016-06-05 20:08:53', '5', '139', '8', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('177', 'metrolinestationa8cd57a538e75177', '2016-06-05 20:08:53', '5', '140', '9', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('178', 'metrolinestationa8cd57a538e75178', '2016-06-05 20:08:53', '5', '141', '10', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('179', 'metrolinestationa8cd57a538e75179', '2016-06-05 20:08:53', '5', '142', '11', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('180', 'metrolinestationa8cd57a538e75180', '2016-06-05 20:08:53', '5', '143', '12', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('181', 'metrolinestationa8cd57a538e75181', '2016-06-05 20:08:53', '5', '144', '13', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('182', 'metrolinestationa8cd57a538e75182', '2016-06-05 20:08:53', '5', '145', '14', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('183', 'metrolinestationa8cd57a538e75183', '2016-06-05 20:08:53', '5', '146', '15', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('184', 'metrolinestationa8cd57a538e75184', '2016-06-05 20:08:53', '5', '147', '16', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('185', 'metrolinestationa8cd57a538e75185', '2016-06-05 20:08:53', '5', '148', '17', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('186', 'metrolinestationa8cd57a538e75186', '2016-06-05 20:08:54', '5', '149', '18', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('187', 'metrolinestationa8cd57a538e75187', '2016-06-05 20:08:54', '5', '150', '19', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('200', 'metrolinestationa8cd57a538e75200', '2016-06-05 20:08:54', '5', '57', '20', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('201', 'metrolinestationa8cd57a538e75201', '2016-06-05 20:08:54', '5', '151', '21', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('202', 'metrolinestationa8cd57a538e75202', '2016-06-05 20:08:54', '5', '152', '22', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('204', 'metrolinestationa8cd57a538e75204', '2016-06-05 20:08:54', '5', '82', '23', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('205', 'metrolinestationa8cd57a538e75205', '2016-06-05 20:08:54', '5', '153', '24', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('206', 'metrolinestationa8cd57a538e75206', '2016-06-05 20:08:54', '5', '154', '25', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('207', 'metrolinestationa8cd57a538e75207', '2016-06-05 20:08:54', '5', '155', '26', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('208', 'metrolinestationa8cd57a538e75208', '2016-06-05 20:08:54', '5', '156', '27', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('209', 'metrolinestationa8cd57a538e75209', '2016-06-05 20:08:54', '5', '157', '28', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('210', 'metrolinestationa8cd57a538e75210', '2016-06-05 20:08:54', '5', '158', '29', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('211', 'metrolinestationa8cd57a538e75211', '2016-06-05 20:08:54', '5', '159', '30', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('212', 'metrolinestationa8cd57a538e75212', '2016-06-05 20:08:54', '5', '160', '31', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('213', 'metrolinestationa8cd57a538e75213', '2016-06-05 20:08:54', '5', '161', '32', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('214', 'metrolinestationa8cd57a538e75214', '2016-06-05 20:08:54', '5', '162', '33', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('215', 'metrolinestationa8cd57a538e75215', '2016-06-05 20:08:54', '5', '163', '34', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('216', 'metrolinestationa8cd57a538e75216', '2016-06-05 20:08:54', '5', '164', '35', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('217', 'metrolinestationa8cd57a538e75217', '2016-06-05 20:08:54', '5', '165', '36', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('218', 'metrolinestationa8cd57a538e75218', '2016-06-05 20:08:54', '5', '166', '37', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('219', 'metrolinestationa8cd57a538e75219', '2016-06-05 20:08:54', '5', '167', '38', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('220', 'metrolinestationa8cd57a538e75220', '2016-06-05 20:08:54', '5', '168', '39', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('221', 'metrolinestationa8cd57a538e75221', '2016-06-09 22:41:35', '7', '169', '1', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('222', 'metrolinestationa8cd57a538e75222', '2016-06-09 22:41:35', '7', '4', '2', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('223', 'metrolinestationa8cd57a538e75223', '2016-06-09 22:41:35', '7', '90', '3', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('224', 'metrolinestationa8cd57a538e75224', '2016-06-09 22:41:35', '7', '170', '4', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('225', 'metrolinestationa8cd57a538e75225', '2016-06-09 22:41:35', '7', '171', '5', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('226', 'metrolinestationa8cd57a538e75226', '2016-06-09 22:41:35', '7', '172', '6', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('227', 'metrolinestationa8cd57a538e75227', '2016-06-09 22:41:35', '7', '173', '7', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('228', 'metrolinestationa8cd57a538e75228', '2016-06-09 22:41:35', '7', '174', '8', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('229', 'metrolinestationa8cd57a538e75229', '2016-06-09 22:41:35', '7', '175', '9', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('230', 'metrolinestationa8cd57a538e75230', '2016-06-09 22:41:35', '7', '126', '10', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('231', 'metrolinestationa8cd57a538e75231', '2016-06-09 22:41:35', '7', '176', '11', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('232', 'metrolinestationa8cd57a538e75232', '2016-06-09 22:41:35', '7', '48', '12', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('233', 'metrolinestationa8cd57a538e75233', '2016-06-09 22:41:35', '7', '177', '13', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('234', 'metrolinestationa8cd57a538e75234', '2016-06-09 22:41:35', '7', '178', '14', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('235', 'metrolinestationa8cd57a538e75235', '2016-06-09 22:41:35', '7', '179', '15', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('236', 'metrolinestationa8cd57a538e75236', '2016-06-09 22:41:36', '7', '180', '16', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('237', 'metrolinestationa8cd57a538e75237', '2016-06-09 22:41:36', '7', '181', '17', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('238', 'metrolinestationa8cd57a538e75238', '2016-06-09 22:41:36', '7', '14', '18', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('239', 'metrolinestationa8cd57a538e75239', '2016-06-09 22:41:36', '7', '182', '19', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('240', 'metrolinestationa8cd57a538e75240', '2016-06-09 22:41:36', '7', '183', '20', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('241', 'metrolinestationa8cd57a538e75241', '2016-06-09 22:41:36', '7', '80', '21', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('242', 'metrolinestationa8cd57a538e75242', '2016-06-09 22:41:36', '7', '184', '22', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('243', 'metrolinestationa8cd57a538e75243', '2016-06-09 22:41:36', '7', '185', '23', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('244', 'metrolinestationa8cd57a538e75244', '2016-06-09 22:41:36', '7', '186', '24', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('245', 'metrolinestationa8cd57a538e75245', '2016-06-09 22:41:36', '7', '117', '25', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('246', 'metrolinestationa8cd57a538e75246', '2016-06-09 22:41:36', '7', '187', '26', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('247', 'metrolinestationa8cd57a538e75247', '2016-06-09 22:41:36', '7', '60', '27', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('248', 'metrolinestationa8cd57a538e75248', '2016-06-09 22:41:36', '7', '188', '28', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('249', 'metrolinestationa8cd57a538e75249', '2016-06-09 22:41:36', '7', '189', '29', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('250', 'metrolinestationa8cd57a538e75250', '2016-06-09 22:41:36', '7', '190', '30', 'BUILDING');
INSERT INTO `metro_line_station` VALUES ('251', 'metrolinestationa8cd57a538e75251', '2016-06-09 22:41:36', '7', '191', '31', 'BUILDING');
