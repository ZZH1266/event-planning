/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : eventplanning

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2024-05-15 17:23:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply_return_money
-- ----------------------------
DROP TABLE IF EXISTS `apply_return_money`;
CREATE TABLE `apply_return_money` (
  `apply_ID` int DEFAULT NULL,
  `event_ID` int DEFAULT NULL,
  `money_amount` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of apply_return_money
-- ----------------------------

-- ----------------------------
-- Table structure for events
-- ----------------------------
DROP TABLE IF EXISTS `events`;
CREATE TABLE `events` (
  `event_ID` int NOT NULL,
  `event_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `event_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `event_detail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `event_model` int NOT NULL,
  `event_status` int NOT NULL,
  `event_share` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `creator_ID` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `place_ID` int DEFAULT NULL,
  `budget` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of events
-- ----------------------------

-- ----------------------------
-- Table structure for event_chatroom
-- ----------------------------
DROP TABLE IF EXISTS `event_chatroom`;
CREATE TABLE `event_chatroom` (
  `chat_ID` int DEFAULT NULL,
  `event_ID` int DEFAULT NULL,
  `send_user_ID` int DEFAULT NULL,
  `chat` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `send_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_chatroom
-- ----------------------------

-- ----------------------------
-- Table structure for event_comment
-- ----------------------------
DROP TABLE IF EXISTS `event_comment`;
CREATE TABLE `event_comment` (
  `comment_ID` int NOT NULL,
  `event_ID` int NOT NULL,
  `from_user_ID` int NOT NULL,
  `to_comment_ID` int DEFAULT NULL,
  `comment` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_comment
-- ----------------------------

-- ----------------------------
-- Table structure for event_inform_information
-- ----------------------------
DROP TABLE IF EXISTS `event_inform_information`;
CREATE TABLE `event_inform_information` (
  `event_ID` int NOT NULL,
  `informed_count` int NOT NULL,
  `received` int DEFAULT NULL,
  PRIMARY KEY (`event_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_inform_information
-- ----------------------------

-- ----------------------------
-- Table structure for event_model
-- ----------------------------
DROP TABLE IF EXISTS `event_model`;
CREATE TABLE `event_model` (
  `event_ID` int DEFAULT NULL,
  `event_model_ID` int NOT NULL,
  `meeting_theme` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `speaker` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `data_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`event_model_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_model
-- ----------------------------

-- ----------------------------
-- Table structure for event_place
-- ----------------------------
DROP TABLE IF EXISTS `event_place`;
CREATE TABLE `event_place` (
  `place_ID` int NOT NULL,
  `place_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_place
-- ----------------------------

-- ----------------------------
-- Table structure for event_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `event_user_relation`;
CREATE TABLE `event_user_relation` (
  `event_ID` int NOT NULL,
  `userID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_role` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `inform_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `event_advice` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_user_relation
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET tis620 COLLATE tis620_thai_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
