/*
Navicat MySQL Data Transfer

Source Server         : mysql069
Source Server Version : 80033
Source Host           : localhost:3306
Source Database       : community2

Target Server Type    : MYSQL
Target Server Version : 80033
File Encoding         : 65001

Date: 2023-12-30 21:44:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `Name` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Sex` char(2) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Phonenum` char(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
  `BID` int NOT NULL,
  `BName` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ResworkerID` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`BID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES ('1', '幸福苑', '2');
INSERT INTO `building` VALUES ('2', '康乐苑', '3');
INSERT INTO `building` VALUES ('3', '爱馨苑', '4');

-- ----------------------------
-- Table structure for cannounce
-- ----------------------------
DROP TABLE IF EXISTS `cannounce`;
CREATE TABLE `cannounce` (
  `No` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Time` datetime(5) DEFAULT NULL,
  `Content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `AnounncedByid` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Level` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cannounce
-- ----------------------------
INSERT INTO `cannounce` VALUES ('12', 'B', '2023-12-28 17:23:59.62916', 'B', '社区管理员', 'B');
INSERT INTO `cannounce` VALUES ('13', 'B级', '2023-12-30 16:14:53.28068', 'B级才能看到', '社区管理员', 'B');

-- ----------------------------
-- Table structure for healthcareworker
-- ----------------------------
DROP TABLE IF EXISTS `healthcareworker`;
CREATE TABLE `healthcareworker` (
  `ID` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Sex` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Tele` char(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Name` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `building` int DEFAULT NULL,
  KEY `build_heal_foreignkey` (`building`),
  CONSTRAINT `build_heal_foreignkey` FOREIGN KEY (`building`) REFERENCES `building` (`BID`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of healthcareworker
-- ----------------------------
INSERT INTO `healthcareworker` VALUES ('2', '男', '13107487338', '小孙', '1');

-- ----------------------------
-- Table structure for health_records
-- ----------------------------
DROP TABLE IF EXISTS `health_records`;
CREATE TABLE `health_records` (
  `ID` char(10) NOT NULL,
  `Height` int DEFAULT NULL,
  `Weight` int DEFAULT NULL,
  `Highpress` int DEFAULT NULL,
  `Lowpress` int DEFAULT NULL,
  `Bloodsugar` int DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of health_records
-- ----------------------------
INSERT INTO `health_records` VALUES ('101', '187', '60', '127', '67', '3', '22:26:49', '2023-12-20');
INSERT INTO `health_records` VALUES ('102', '160', '50', '141', '80', '8', '17:51:11', '2023-12-27');
INSERT INTO `health_records` VALUES ('103', '171', '80', '170', '110', '5', '16:05:00', '2023-12-29');

-- ----------------------------
-- Table structure for olds
-- ----------------------------
DROP TABLE IF EXISTS `olds`;
CREATE TABLE `olds` (
  `ID` char(10) NOT NULL,
  `O_name` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `O_sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `O_age` int DEFAULT NULL,
  `O_tele` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `roomID` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `building` int DEFAULT NULL,
  `Rela_ID` char(10) DEFAULT NULL,
  `CareLevel` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `FamilyHistory` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `build_forkey` (`building`),
  CONSTRAINT `build_forkey` FOREIGN KEY (`building`) REFERENCES `building` (`BID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of olds
-- ----------------------------
INSERT INTO `olds` VALUES ('101', '老李', '男', '88', '17465087765', '1', '1', '1011', 'C', 'NONE');
INSERT INTO `olds` VALUES ('102', '老王', '男', '60', '14392875680', '2', '1', '1021', 'B', 'NONE');
INSERT INTO `olds` VALUES ('103', '老刘', '男', '76', '18147581023', '3', '1', '1011', 'B', 'NONE');

-- ----------------------------
-- Table structure for relatives
-- ----------------------------
DROP TABLE IF EXISTS `relatives`;
CREATE TABLE `relatives` (
  `ID` char(10) NOT NULL,
  `Name` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Phonenum` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `CareOldID` char(10) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `olds_forkey` (`CareOldID`),
  CONSTRAINT `olds_forkey` FOREIGN KEY (`CareOldID`) REFERENCES `olds` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of relatives
-- ----------------------------
INSERT INTO `relatives` VALUES ('1011', '李军', '男', '18122314221', '101');
INSERT INTO `relatives` VALUES ('1021', '王明', '男', '15984926598', '102');

-- ----------------------------
-- Table structure for reports
-- ----------------------------
DROP TABLE IF EXISTS `reports`;
CREATE TABLE `reports` (
  `publisher` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `publishTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of reports
-- ----------------------------
INSERT INTO `reports` VALUES ('社区管理员', '1', '1', '2023-12-27 17:27:23');
INSERT INTO `reports` VALUES ('社区管理员', '1', '1', '2023-12-27 17:27:44');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `U_num` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `U_type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `U_name` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `U_psw` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`U_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '社区管理员', '老李', '1');
INSERT INTO `users` VALUES ('101', '老年人', '老李', '1');
INSERT INTO `users` VALUES ('1011', '家属', '李军', '1');
INSERT INTO `users` VALUES ('102', '老年人', '老王', '1');
INSERT INTO `users` VALUES ('1021', '家属', '王明', '1');
INSERT INTO `users` VALUES ('103', '老年人', '老刘', '1');
INSERT INTO `users` VALUES ('2', '健康记录员', '小孙', '1');
INSERT INTO `users` VALUES ('3', '健康管理员', '小王', '1');
INSERT INTO `users` VALUES ('4', '健康记录员', '小吴', '1');
