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

 Date: 22/05/2024 17:57:25
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
  PRIMARY KEY (`activity_id`) USING BTREE,
  INDEX `created_by`(`created_by` ASC) USING BTREE,
  CONSTRAINT `activities_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activities
-- ----------------------------

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
  INDEX `generated_by`(`generated_by` ASC) USING BTREE,
  CONSTRAINT `activity_reports_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `activity_reports_ibfk_2` FOREIGN KEY (`generated_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_reports
-- ----------------------------

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
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  CONSTRAINT `budgets_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of budgets
-- ----------------------------

-- ----------------------------
-- Table structure for expense_claims
-- ----------------------------
DROP TABLE IF EXISTS `expense_claims`;
CREATE TABLE `expense_claims`  (
  `claim_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `amount` decimal(10, 2) NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `status` enum('待审核','已批准','已拒绝') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '待审核',
  `submitted_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `reviewed_at` timestamp NULL DEFAULT NULL,
  `reviewed_by` int NULL DEFAULT NULL,
  PRIMARY KEY (`claim_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `reviewed_by`(`reviewed_by` ASC) USING BTREE,
  CONSTRAINT `expense_claims_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `expense_claims_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `expense_claims_ibfk_3` FOREIGN KEY (`reviewed_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expense_claims
-- ----------------------------

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
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  CONSTRAINT `feedback_analysis_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
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
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `feedbacks_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `feedbacks_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedbacks
-- ----------------------------

-- ----------------------------
-- Table structure for interactions
-- ----------------------------
DROP TABLE IF EXISTS `interactions`;
CREATE TABLE `interactions`  (
  `interaction_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `interaction_type` enum('聊天室','问答') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`interaction_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `interactions_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `interactions_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interactions
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
  INDEX `sent_by`(`sent_by` ASC) USING BTREE,
  CONSTRAINT `notification_logs_ibfk_1` FOREIGN KEY (`template_id`) REFERENCES `notification_templates` (`template_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `notification_logs_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `notification_logs_ibfk_3` FOREIGN KEY (`sent_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification_logs
-- ----------------------------

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
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `notification_receptions_ibfk_1` FOREIGN KEY (`log_id`) REFERENCES `notification_logs` (`log_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `notification_receptions_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification_templates
-- ----------------------------

-- ----------------------------
-- Table structure for participants
-- ----------------------------
DROP TABLE IF EXISTS `participants`;
CREATE TABLE `participants`  (
  `participant_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  `role` enum('Guest','Speaker','Worker','Participant') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Participant',
  PRIMARY KEY (`participant_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `participants_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `participants_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of participants
-- ----------------------------

-- ----------------------------
-- Table structure for resource_allocations
-- ----------------------------
DROP TABLE IF EXISTS `resource_allocations`;
CREATE TABLE `resource_allocations`  (
  `allocation_id` int NOT NULL AUTO_INCREMENT,
  `schedule_id` int NOT NULL,
  `resource_id` int NOT NULL,
  `allocated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`allocation_id`) USING BTREE,
  INDEX `schedule_id`(`schedule_id` ASC) USING BTREE,
  INDEX `resource_id`(`resource_id` ASC) USING BTREE,
  CONSTRAINT `resource_allocations_ibfk_1` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource_allocations_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_allocations
-- ----------------------------

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
  INDEX `booked_by`(`booked_by` ASC) USING BTREE,
  CONSTRAINT `resource_bookings_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource_bookings_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource_bookings_ibfk_3` FOREIGN KEY (`booked_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_bookings
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resources
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'admin');
INSERT INTO `roles` VALUES (2, 'user');

-- ----------------------------
-- Table structure for schedules
-- ----------------------------
DROP TABLE IF EXISTS `schedules`;
CREATE TABLE `schedules`  (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `schedule_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`schedule_id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedules
-- ----------------------------

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `user_role_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_role_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
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
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'zzh', '111', 'zzh18736680168@outlook.com', '2024-05-22 17:55:30', '2024-05-22 17:55:30');

-- ----------------------------
-- Table structure for verification_codes
-- ----------------------------
DROP TABLE IF EXISTS `verification_codes`;
CREATE TABLE `verification_codes`  (
  `code_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` enum('注册','找回密码') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expires_at` timestamp NOT NULL,
  PRIMARY KEY (`code_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `verification_codes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of verification_codes
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
