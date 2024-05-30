/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : activityplanning

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 30/05/2024 18:59:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activities
-- ----------------------------
DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities`  (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `created_by` int NOT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `status` enum('筹备中','进行中','已完成') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '筹备中',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `template_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`) USING BTREE,
  INDEX `created_by`(`created_by` ASC) USING BTREE,
  INDEX `template_id`(`template_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activities
-- ----------------------------
INSERT INTO `activities` VALUES (1, '会议', '努力', 4, '2024-05-24 17:31:01', '2024-05-30 17:31:06', '筹备中', '2024-05-23 17:31:12', '2024-05-23 17:31:12', NULL);
INSERT INTO `activities` VALUES (2, '打篮球', '热情', 5, NULL, NULL, '筹备中', '2024-05-26 23:58:10', '2024-05-26 23:58:10', NULL);
INSERT INTO `activities` VALUES (3, '新活动5', '123', 4, '2024-05-29 00:00:00', '2024-05-30 00:00:00', '筹备中', '2024-05-30 18:40:00', '2024-05-30 18:40:00', 1);

-- ----------------------------
-- Table structure for activity_reports
-- ----------------------------
DROP TABLE IF EXISTS `activity_reports`;
CREATE TABLE `activity_reports`  (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `generated_by` int NOT NULL,
  `generated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `participant_count` int NULL DEFAULT NULL,
  `overall_effectiveness` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `report_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  PRIMARY KEY (`report_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `generated_by`(`generated_by` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_reports
-- ----------------------------
INSERT INTO `activity_reports` VALUES (1, 1, 4, '2024-05-30 18:35:09', 10, '45', '2222');

-- ----------------------------
-- Table structure for activity_templates
-- ----------------------------
DROP TABLE IF EXISTS `activity_templates`;
CREATE TABLE `activity_templates`  (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `template_id` int NOT NULL,
  `activity_size` int NULL DEFAULT NULL,
  `place_plan_to_use` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sports_kind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `safety_officer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `indoor_kind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `host_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of activity_templates
-- ----------------------------
INSERT INTO `activity_templates` VALUES (1, 1, NULL, NULL, '2024-05-29 19:29:58', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `activity_templates` VALUES (10, 1, 95, 'basketball court', '2024-05-29 23:02:51', 'basketball', 'wuzongqian', '', '', '');
INSERT INTO `activity_templates` VALUES (11, 1, 95, 'basketball court', '2024-05-29 23:12:15', 'basketball', 'wuzongqian', NULL, NULL, NULL);
INSERT INTO `activity_templates` VALUES (12, 1, 95, 'basketball court', '2024-05-29 23:12:44', 'basketball', 'wuzongqian', NULL, NULL, NULL);
INSERT INTO `activity_templates` VALUES (13, 2, 95, 'basketball court', '2024-05-29 23:12:51', NULL, NULL, 'lesson', 'wzq', 'wzq');
INSERT INTO `activity_templates` VALUES (20, 1, 95, 'basketball court', '2024-05-29 23:21:16', 'basketball', 'wuzongqian', NULL, NULL, NULL);
INSERT INTO `activity_templates` VALUES (21, 1, 95, 'basketball court', '2024-05-30 18:41:27', 'basketball', 'wuzongqian', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for budgets
-- ----------------------------
DROP TABLE IF EXISTS `budgets`;
CREATE TABLE `budgets`  (
  `budget_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `total_budget` decimal(10, 2) NOT NULL,
  `actual_spent` decimal(10, 2) NULL DEFAULT 0.00,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`budget_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of budgets
-- ----------------------------

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `parent_id` int NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 1, 4, 'zzh1', 0, '这是评论1', '2024-05-27 18:23:48');
INSERT INTO `comments` VALUES (2, 1, 5, 'Zack', 1, '我回复了第一条评论', '2024-05-27 18:24:25');
INSERT INTO `comments` VALUES (3, 1, 6, 'wu', 2, '我回复了第一条评论的第一条回复', '2024-05-27 18:25:09');
INSERT INTO `comments` VALUES (4, 1, 7, 'wuzongqian', 2, '我回复了第一条评论的第二条回复', '2024-05-27 18:25:34');
INSERT INTO `comments` VALUES (5, 1, 8, 'liboyang', 0, '这是评论2', '2024-05-27 18:25:56');
INSERT INTO `comments` VALUES (6, 1, 9, 'cx asCV', 3, '我回复了第一条评论的第一条回复的第一条回复', '2024-05-27 18:26:23');
INSERT INTO `comments` VALUES (7, 2, 10, 'sac saDC', 0, '这是评论3', '2024-05-27 18:27:09');
INSERT INTO `comments` VALUES (8, 2, 4, 'zzh1', 0, '这是第3条评论', '2024-05-27 19:21:55');

-- ----------------------------
-- Table structure for expense_claims
-- ----------------------------
DROP TABLE IF EXISTS `expense_claims`;
CREATE TABLE `expense_claims`  (
  `claim_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `amount` decimal(10, 0) NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `status` enum('待审核','已批准','已拒绝') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '待审核',
  `submitted_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`claim_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expense_claims
-- ----------------------------
INSERT INTO `expense_claims` VALUES (2, 1, 123456, 100, '活动报销test', '待审核', '2024-05-30 18:09:36');

-- ----------------------------
-- Table structure for feedback_analysis
-- ----------------------------
DROP TABLE IF EXISTS `feedback_analysis`;
CREATE TABLE `feedback_analysis`  (
  `analysis_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `analysis_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`analysis_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback_analysis
-- ----------------------------

-- ----------------------------
-- Table structure for feedbacks
-- ----------------------------
DROP TABLE IF EXISTS `feedbacks`;
CREATE TABLE `feedbacks`  (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `rating` int NOT NULL,
  `comments` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `submitted_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`feedback_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedbacks
-- ----------------------------

-- ----------------------------
-- Table structure for notification_logs
-- ----------------------------
DROP TABLE IF EXISTS `notification_logs`;
CREATE TABLE `notification_logs`  (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `template_id` int NOT NULL,
  `activity_id` int NOT NULL,
  `sent_by` int NOT NULL,
  `sent_to` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sent_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('已发送','发送失败') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '已发送',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `template_id`(`template_id` ASC) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `sent_by`(`sent_by` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification_logs
-- ----------------------------
INSERT INTO `notification_logs` VALUES (1, 1, 1, 4, '4', '2024-05-30 18:33:36', '已发送');
INSERT INTO `notification_logs` VALUES (2, 1, 1, 4, '4', '2024-05-30 18:33:56', '已发送');
INSERT INTO `notification_logs` VALUES (3, 1, 1, 4, '4', '2024-05-30 18:34:09', '已发送');
INSERT INTO `notification_logs` VALUES (4, 1, 1, 4, '4', '2024-05-30 18:34:13', '已发送');

-- ----------------------------
-- Table structure for notification_receptions
-- ----------------------------
DROP TABLE IF EXISTS `notification_receptions`;
CREATE TABLE `notification_receptions`  (
  `reception_id` int NOT NULL AUTO_INCREMENT,
  `log_id` int NOT NULL,
  `user_id` int NOT NULL,
  `received_at` timestamp NULL DEFAULT NULL,
  `read_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`reception_id`) USING BTREE,
  INDEX `log_id`(`log_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification_receptions
-- ----------------------------

-- ----------------------------
-- Table structure for notification_templates
-- ----------------------------
DROP TABLE IF EXISTS `notification_templates`;
CREATE TABLE `notification_templates`  (
  `template_id` int NOT NULL AUTO_INCREMENT,
  `template_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`template_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification_templates
-- ----------------------------
INSERT INTO `notification_templates` VALUES (1, '无敌模板', '无敌主题', '无敌结构', '2024-05-30 18:33:42', '2024-05-30 18:33:42');
INSERT INTO `notification_templates` VALUES (2, '无敌模', '无敌主题', '无敌结构', '2024-05-30 18:34:02', '2024-05-30 18:34:02');

-- ----------------------------
-- Table structure for participant_applications
-- ----------------------------
DROP TABLE IF EXISTS `participant_applications`;
CREATE TABLE `participant_applications`  (
  `apply_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `result_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of participant_applications
-- ----------------------------
INSERT INTO `participant_applications` VALUES (1, 1, 11, 'sacascv', '申请中', '2024-05-27 23:17:30', '2024-05-27 23:27:16');
INSERT INTO `participant_applications` VALUES (2, 1, 6, 'wu', '申请中', '2024-05-27 23:28:20', '2024-05-27 23:30:07');

-- ----------------------------
-- Table structure for participants
-- ----------------------------
DROP TABLE IF EXISTS `participants`;
CREATE TABLE `participants`  (
  `participant_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '参与者',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `money` int NULL DEFAULT NULL,
  PRIMARY KEY (`participant_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of participants
-- ----------------------------
INSERT INTO `participants` VALUES (2, 1, 4, '组织者', 'zzh1', NULL);
INSERT INTO `participants` VALUES (5, 1, 8, '参与者', 'liboyang', NULL);
INSERT INTO `participants` VALUES (6, 1, 9, '参与者', 'cx asCV', NULL);
INSERT INTO `participants` VALUES (14, 2, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (17, 3, 4, '组织者', 'zzh1', NULL);

-- ----------------------------
-- Table structure for resource_allocations
-- ----------------------------
DROP TABLE IF EXISTS `resource_allocations`;
CREATE TABLE `resource_allocations`  (
  `allocation_id` int NOT NULL AUTO_INCREMENT,
  `resource_id` int NULL DEFAULT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `allocated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`allocation_id`) USING BTREE,
  INDEX `resource_id`(`resource_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resource_allocations
-- ----------------------------
INSERT INTO `resource_allocations` VALUES (1, 1, '2024-05-30 18:43:39', '2024-05-30 20:43:44', '2024-05-30 18:43:50');

-- ----------------------------
-- Table structure for resource_bookings
-- ----------------------------
DROP TABLE IF EXISTS `resource_bookings`;
CREATE TABLE `resource_bookings`  (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `resource_id` int NOT NULL,
  `activity_id` int NOT NULL,
  `booked_by` int NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`booking_id`) USING BTREE,
  INDEX `resource_id`(`resource_id` ASC) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `booked_by`(`booked_by` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resource_bookings
-- ----------------------------
INSERT INTO `resource_bookings` VALUES (4, 1, 10, 4, '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-05-30 18:45:03');
INSERT INTO `resource_bookings` VALUES (5, 1, 10, 4, '2024-05-30 16:20:20', '2024-05-30 18:20:29', '2024-05-30 18:46:37');

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources`  (
  `resource_id` int NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `resource_type` enum('场地','设备') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `status` enum('可用','不可用') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '可用',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES (2, '7斋地下一层报告厅', '场地', '22222', '可用', '2024-05-30 18:33:25', '2024-05-30 18:33:25');

-- ----------------------------
-- Table structure for template_agendas
-- ----------------------------
DROP TABLE IF EXISTS `template_agendas`;
CREATE TABLE `template_agendas`  (
  `agenda_id` int NOT NULL AUTO_INCREMENT,
  `template_id` int NOT NULL,
  `agenda_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `speaker` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `start_time` time NULL DEFAULT NULL,
  `end_time` time NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`agenda_id`) USING BTREE,
  INDEX `template_id`(`template_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of template_agendas
-- ----------------------------

-- ----------------------------
-- Table structure for template_materials
-- ----------------------------
DROP TABLE IF EXISTS `template_materials`;
CREATE TABLE `template_materials`  (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `template_id` int NOT NULL,
  `material_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `material_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`material_id`) USING BTREE,
  INDEX `template_id`(`template_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of template_materials
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE,
  INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123457 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (4, 'zzh1', '111', 'zzh18736680168@outlook.com1', '2024-05-22 19:10:48', '2024-05-23 23:38:24', 0);
INSERT INTO `users` VALUES (5, 'Zack', '234', 'vrev', '2024-05-23 17:31:48', '2024-05-23 17:31:48', 0);
INSERT INTO `users` VALUES (6, 'wu', 'adcnjkc', 'csac@ouy.com', '2024-05-23 17:32:13', '2024-05-23 17:32:13', 0);
INSERT INTO `users` VALUES (7, 'wuzongqian', 'asdcv', 'xasc@ustb.com', '2024-05-23 17:32:29', '2024-05-23 17:32:29', 0);
INSERT INTO `users` VALUES (8, 'liboyang', 'sacasdVCW', 'x as@ustb.com', '2024-05-23 17:32:44', '2024-05-23 17:32:44', 0);
INSERT INTO `users` VALUES (9, 'cx asCV', 'xsqac@cxds.com', 'xcasc@xasc.com', '2024-05-23 17:33:04', '2024-05-23 17:33:04', 0);
INSERT INTO `users` VALUES (10, 'sac saDC', 'xsaxcc', 'XAAX@casc.com', '2024-05-23 17:33:19', '2024-05-23 17:33:19', 0);
INSERT INTO `users` VALUES (11, 'sacascv', 'sacqw', 'xsac@outlii.com', '2024-05-23 17:33:35', '2024-05-23 17:33:35', 0);
INSERT INTO `users` VALUES (12, 'cxwsqac', 'cxasc', 'xcsacs@casc.com', '2024-05-23 17:33:44', '2024-05-23 17:33:44', 0);
INSERT INTO `users` VALUES (13, 'csaCEASQ', 'xascwq', 'saca@mic.com', '2024-05-23 17:33:56', '2024-05-23 17:33:56', 0);
INSERT INTO `users` VALUES (14, ' asc', 'xaxasc', 'CXA@ncui.com', '2024-05-23 17:34:08', '2024-05-23 17:34:08', 0);
INSERT INTO `users` VALUES (123456, 'zzh', '222', 'zzh18736680168@outlook.com', '2024-05-23 23:39:17', '2024-05-30 17:48:55', 0);

-- ----------------------------
-- Triggers structure for table participants
-- ----------------------------
DROP TRIGGER IF EXISTS `before_insert_participants`;
delimiter ;;
CREATE TRIGGER `before_insert_participants` BEFORE INSERT ON `participants` FOR EACH ROW BEGIN
    DECLARE uname VARCHAR(255);
    SELECT username INTO uname FROM users WHERE user_id = NEW.user_id;
    SET NEW.username = uname;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table users
-- ----------------------------
DROP TRIGGER IF EXISTS `after_update_users`;
delimiter ;;
CREATE TRIGGER `after_update_users` AFTER UPDATE ON `users` FOR EACH ROW BEGIN
    IF OLD.username != NEW.username THEN
        UPDATE participants
        SET username = NEW.username
        WHERE user_id = OLD.user_id;
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
