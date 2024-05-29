/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : activityplanning

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2024-05-29 23:24:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activities
-- ----------------------------
DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `created_by` int NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` enum('筹备中','进行中','已完成') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '筹备中',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `template_id` int DEFAULT NULL,
  PRIMARY KEY (`activity_id`) USING BTREE,
  KEY `created_by` (`created_by`) USING BTREE,
  KEY `template_id` (`template_id`) USING BTREE,
  CONSTRAINT `activities_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of activities
-- ----------------------------
INSERT INTO `activities` VALUES ('10', '新活动', '123', '4', null, null, '筹备中', '2024-05-29 19:54:49', '2024-05-29 19:54:49', '1');
INSERT INTO `activities` VALUES ('11', '新活动2', '123', '7', null, null, '筹备中', '2024-05-29 19:55:57', '2024-05-29 19:55:57', '1');
INSERT INTO `activities` VALUES ('12', '新活动3', '123', '4', null, null, '筹备中', '2024-05-29 19:58:09', '2024-05-29 19:58:09', '1');
INSERT INTO `activities` VALUES ('13', '新活动4', '123', '4', null, '2024-05-30 00:00:00', '筹备中', '2024-05-29 08:00:00', '2024-05-29 20:01:04', '1');
INSERT INTO `activities` VALUES ('14', '新活动5', '123', '4', '2024-05-29 00:00:00', '2024-05-30 00:00:00', '筹备中', '2024-05-29 20:03:19', '2024-05-29 20:03:19', '1');
INSERT INTO `activities` VALUES ('16', '新活动6', 'helloworld', '4', '2018-05-13 00:00:00', '2002-05-05 00:00:00', '筹备中', '2024-05-29 20:22:18', '2024-05-29 20:22:18', '1');
INSERT INTO `activities` VALUES ('17', '新活动7', 'helloworld', '4', '2018-05-13 00:00:00', '2002-05-05 00:00:00', '筹备中', '2024-05-29 23:06:55', '2024-05-29 23:06:55', '1');
INSERT INTO `activities` VALUES ('18', '新活动8', 'helloworld', '5', '2018-05-13 00:00:00', '2002-05-05 00:00:00', '筹备中', '2024-05-29 23:13:08', '2024-05-29 23:13:08', '1');
INSERT INTO `activities` VALUES ('20', '新活动9', 'helloworld', '5', '2018-05-13 00:00:00', '2002-05-05 00:00:00', '筹备中', '2024-05-29 23:19:09', '2024-05-29 23:19:09', '2');

-- ----------------------------
-- Table structure for activity_reports
-- ----------------------------
DROP TABLE IF EXISTS `activity_reports`;
CREATE TABLE `activity_reports` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `generated_by` int NOT NULL,
  `generated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `participant_count` int DEFAULT NULL,
  `overall_effectiveness` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `report_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`report_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  KEY `generated_by` (`generated_by`) USING BTREE,
  CONSTRAINT `activity_reports_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `activity_reports_ibfk_2` FOREIGN KEY (`generated_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of activity_reports
-- ----------------------------

-- ----------------------------
-- Table structure for activity_templates
-- ----------------------------
DROP TABLE IF EXISTS `activity_templates`;
CREATE TABLE `activity_templates` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `template_id` int NOT NULL,
  `activity_size` int DEFAULT NULL,
  `place_plan_to_use` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sports_kind` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `safety_officer_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `indoor_kind` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `host_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `teacher_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of activity_templates
-- ----------------------------
INSERT INTO `activity_templates` VALUES ('1', '1', null, null, '2024-05-29 19:29:58', null, null, null, null, null);
INSERT INTO `activity_templates` VALUES ('10', '1', '95', 'basketball court', '2024-05-29 23:02:51', 'basketball', 'wuzongqian', '', '', '');
INSERT INTO `activity_templates` VALUES ('11', '1', '95', 'basketball court', '2024-05-29 23:12:15', 'basketball', 'wuzongqian', null, null, null);
INSERT INTO `activity_templates` VALUES ('12', '1', '95', 'basketball court', '2024-05-29 23:12:44', 'basketball', 'wuzongqian', null, null, null);
INSERT INTO `activity_templates` VALUES ('13', '2', '95', 'basketball court', '2024-05-29 23:12:51', null, null, 'lesson', 'wzq', 'wzq');
INSERT INTO `activity_templates` VALUES ('20', '1', '95', 'basketball court', '2024-05-29 23:21:16', 'basketball', 'wuzongqian', null, null, null);

-- ----------------------------
-- Table structure for budgets
-- ----------------------------
DROP TABLE IF EXISTS `budgets`;
CREATE TABLE `budgets` (
  `budget_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `total_budget` decimal(10,2) NOT NULL,
  `actual_spent` decimal(10,2) DEFAULT '0.00',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`budget_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  CONSTRAINT `budgets_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of budgets
-- ----------------------------

-- ----------------------------
-- Table structure for expense_claims
-- ----------------------------
DROP TABLE IF EXISTS `expense_claims`;
CREATE TABLE `expense_claims` (
  `claim_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `status` enum('待审核','已批准','已拒绝') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '待审核',
  `submitted_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `reviewed_at` timestamp NULL DEFAULT NULL,
  `reviewed_by` int DEFAULT NULL,
  PRIMARY KEY (`claim_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `reviewed_by` (`reviewed_by`) USING BTREE,
  CONSTRAINT `expense_claims_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `expense_claims_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `expense_claims_ibfk_3` FOREIGN KEY (`reviewed_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of expense_claims
-- ----------------------------

-- ----------------------------
-- Table structure for feedbacks
-- ----------------------------
DROP TABLE IF EXISTS `feedbacks`;
CREATE TABLE `feedbacks` (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `rating` int NOT NULL,
  `comments` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `submitted_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`feedback_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `feedbacks_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `feedbacks_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of feedbacks
-- ----------------------------

-- ----------------------------
-- Table structure for feedback_analysis
-- ----------------------------
DROP TABLE IF EXISTS `feedback_analysis`;
CREATE TABLE `feedback_analysis` (
  `analysis_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `analysis_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`analysis_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  CONSTRAINT `feedback_analysis_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of feedback_analysis
-- ----------------------------

-- ----------------------------
-- Table structure for interactions
-- ----------------------------
DROP TABLE IF EXISTS `interactions`;
CREATE TABLE `interactions` (
  `interaction_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `interaction_type` enum('聊天室','问答') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`interaction_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `interactions_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `interactions_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of interactions
-- ----------------------------

-- ----------------------------
-- Table structure for notification_logs
-- ----------------------------
DROP TABLE IF EXISTS `notification_logs`;
CREATE TABLE `notification_logs` (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `template_id` int NOT NULL,
  `activity_id` int NOT NULL,
  `sent_by` int NOT NULL,
  `sent_to` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sent_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('已发送','发送失败') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '已发送',
  PRIMARY KEY (`log_id`) USING BTREE,
  KEY `template_id` (`template_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  KEY `sent_by` (`sent_by`) USING BTREE,
  CONSTRAINT `notification_logs_ibfk_1` FOREIGN KEY (`template_id`) REFERENCES `notification_templates` (`template_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `notification_logs_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `notification_logs_ibfk_3` FOREIGN KEY (`sent_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notification_logs
-- ----------------------------

-- ----------------------------
-- Table structure for notification_receptions
-- ----------------------------
DROP TABLE IF EXISTS `notification_receptions`;
CREATE TABLE `notification_receptions` (
  `reception_id` int NOT NULL AUTO_INCREMENT,
  `log_id` int NOT NULL,
  `user_id` int NOT NULL,
  `received_at` timestamp NULL DEFAULT NULL,
  `read_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`reception_id`) USING BTREE,
  KEY `log_id` (`log_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `notification_receptions_ibfk_1` FOREIGN KEY (`log_id`) REFERENCES `notification_logs` (`log_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `notification_receptions_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notification_receptions
-- ----------------------------

-- ----------------------------
-- Table structure for notification_templates
-- ----------------------------
DROP TABLE IF EXISTS `notification_templates`;
CREATE TABLE `notification_templates` (
  `template_id` int NOT NULL AUTO_INCREMENT,
  `template_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`template_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notification_templates
-- ----------------------------

-- ----------------------------
-- Table structure for participants
-- ----------------------------
DROP TABLE IF EXISTS `participants`;
CREATE TABLE `participants` (
  `participant_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '参与者',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`participant_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `participants_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `participants_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of participants
-- ----------------------------
INSERT INTO `participants` VALUES ('14', '10', '4', '组织者', 'zzh');
INSERT INTO `participants` VALUES ('15', '11', '7', '组织者', 'wuzongqian');
INSERT INTO `participants` VALUES ('16', '12', '4', '组织者', 'zzh');
INSERT INTO `participants` VALUES ('17', '13', '4', '组织者', 'zzh');
INSERT INTO `participants` VALUES ('18', '14', '4', '组织者', 'zzh');
INSERT INTO `participants` VALUES ('19', '16', '4', '组织者', 'zzh');
INSERT INTO `participants` VALUES ('20', '17', '4', '组织者', 'zzh');
INSERT INTO `participants` VALUES ('21', '18', '5', '组织者', 'Zack');
INSERT INTO `participants` VALUES ('22', '20', '5', '组织者', 'Zack');

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `resource_id` int NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `resource_type` enum('场地','设备') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `status` enum('可用','不可用') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '可用',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of resources
-- ----------------------------

-- ----------------------------
-- Table structure for resource_allocations
-- ----------------------------
DROP TABLE IF EXISTS `resource_allocations`;
CREATE TABLE `resource_allocations` (
  `allocation_id` int NOT NULL AUTO_INCREMENT,
  `schedule_id` int NOT NULL,
  `resource_id` int NOT NULL,
  `allocated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`allocation_id`) USING BTREE,
  KEY `schedule_id` (`schedule_id`) USING BTREE,
  KEY `resource_id` (`resource_id`) USING BTREE,
  CONSTRAINT `resource_allocations_ibfk_1` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource_allocations_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of resource_allocations
-- ----------------------------

-- ----------------------------
-- Table structure for resource_bookings
-- ----------------------------
DROP TABLE IF EXISTS `resource_bookings`;
CREATE TABLE `resource_bookings` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `resource_id` int NOT NULL,
  `activity_id` int NOT NULL,
  `booked_by` int NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`booking_id`) USING BTREE,
  KEY `resource_id` (`resource_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  KEY `booked_by` (`booked_by`) USING BTREE,
  CONSTRAINT `resource_bookings_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource_bookings_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource_bookings_ibfk_3` FOREIGN KEY (`booked_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of resource_bookings
-- ----------------------------

-- ----------------------------
-- Table structure for schedules
-- ----------------------------
DROP TABLE IF EXISTS `schedules`;
CREATE TABLE `schedules` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `schedule_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`schedule_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of schedules
-- ----------------------------

-- ----------------------------
-- Table structure for template_agendas
-- ----------------------------
DROP TABLE IF EXISTS `template_agendas`;
CREATE TABLE `template_agendas` (
  `agenda_id` int NOT NULL AUTO_INCREMENT,
  `template_id` int NOT NULL,
  `agenda_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `speaker` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`agenda_id`) USING BTREE,
  KEY `template_id` (`template_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of template_agendas
-- ----------------------------

-- ----------------------------
-- Table structure for template_materials
-- ----------------------------
DROP TABLE IF EXISTS `template_materials`;
CREATE TABLE `template_materials` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `template_id` int NOT NULL,
  `material_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `material_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`material_id`) USING BTREE,
  KEY `template_id` (`template_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of template_materials
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('4', 'zzh', '222', 'zzh18736680168@outlook.com', '2024-05-22 19:10:48', '2024-05-22 19:10:48', '0');
INSERT INTO `users` VALUES ('5', 'Zack', '234', 'vrev', '2024-05-23 17:31:48', '2024-05-23 17:31:48', '0');
INSERT INTO `users` VALUES ('6', 'wu', 'adcnjkc', 'csac@ouy.com', '2024-05-23 17:32:13', '2024-05-23 17:32:13', '0');
INSERT INTO `users` VALUES ('7', 'wuzongqian', 'asdcv', 'xasc@ustb.com', '2024-05-23 17:32:29', '2024-05-23 17:32:29', '0');
INSERT INTO `users` VALUES ('8', 'liboyang', 'sacasdVCW', 'x as@ustb.com', '2024-05-23 17:32:44', '2024-05-23 17:32:44', '0');
INSERT INTO `users` VALUES ('9', 'cx asCV', 'xsqac@cxds.com', 'xcasc@xasc.com', '2024-05-23 17:33:04', '2024-05-23 17:33:04', '0');
INSERT INTO `users` VALUES ('10', 'sac saDC', 'xsaxcc', 'XAAX@casc.com', '2024-05-23 17:33:19', '2024-05-23 17:33:19', '0');
INSERT INTO `users` VALUES ('11', 'sacascv', 'sacqw', 'xsac@outlii.com', '2024-05-23 17:33:35', '2024-05-23 17:33:35', '0');
INSERT INTO `users` VALUES ('12', 'cxwsqac', 'cxasc', 'xcsacs@casc.com', '2024-05-23 17:33:44', '2024-05-23 17:33:44', '0');
INSERT INTO `users` VALUES ('13', 'csaCEASQ', 'xascwq', 'saca@mic.com', '2024-05-23 17:33:56', '2024-05-23 17:33:56', '0');
INSERT INTO `users` VALUES ('14', ' asc', 'xaxasc', 'CXA@ncui.com', '2024-05-23 17:34:08', '2024-05-23 17:34:08', '0');

-- ----------------------------
-- Table structure for verification_codes
-- ----------------------------
DROP TABLE IF EXISTS `verification_codes`;
CREATE TABLE `verification_codes` (
  `code_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` enum('注册','找回密码') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expires_at` timestamp NOT NULL,
  PRIMARY KEY (`code_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `verification_codes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of verification_codes
-- ----------------------------
DROP TRIGGER IF EXISTS `before_insert_participants`;
DELIMITER ;;
CREATE TRIGGER `before_insert_participants` BEFORE INSERT ON `participants` FOR EACH ROW BEGIN
    DECLARE uname VARCHAR(255);
    SELECT username INTO uname FROM users WHERE user_id = NEW.user_id;
    SET NEW.username = uname;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `after_update_users`;
DELIMITER ;;
CREATE TRIGGER `after_update_users` AFTER UPDATE ON `users` FOR EACH ROW BEGIN
    IF OLD.username != NEW.username THEN
        UPDATE participants
        SET username = NEW.username
        WHERE user_id = OLD.user_id;
    END IF;
END
;;
DELIMITER ;
