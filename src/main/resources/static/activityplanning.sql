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

 Date: 13/06/2024 17:19:23
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
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of activities
-- ----------------------------
INSERT INTO `activities` VALUES (1, '222', '32', 3, NULL, NULL, '筹备中', '2024-06-13 15:51:55', '2024-06-13 15:51:55', NULL);
INSERT INTO `activities` VALUES (2, '13饿32', '带我去', 4, NULL, NULL, '筹备中', '2024-06-13 15:53:03', '2024-06-13 15:53:03', NULL);
INSERT INTO `activities` VALUES (4, 'wzq1298354', 'wzq', 1, '2024-06-06 21:06:30', '2024-06-07 21:06:33', '筹备中', '2024-06-02 14:10:01', '2024-06-06 21:06:35', 1);
INSERT INTO `activities` VALUES (10, '新活动8', '123', 4, '2024-05-29 00:00:00', '2024-05-31 00:00:00', '筹备中', '2024-06-13 16:24:47', '2024-06-13 16:24:47', 1);
INSERT INTO `activities` VALUES (11, '新活动7', '这是新的活动', 5, '2001-05-01 00:00:00', '2002-08-11 00:00:00', '筹备中', '2024-06-13 17:18:08', '2024-06-13 17:18:08', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of activity_reports
-- ----------------------------
INSERT INTO `activity_reports` VALUES (2, 1, 4, '2024-06-13 16:42:56', 10, '45', '2222');

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
INSERT INTO `activity_templates` VALUES (4, 1, 95, 'basketball court', '2024-06-13 16:24:48', 'basketball', 'wuzongqian', NULL, NULL, NULL);
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 1, 4, 'zzh1', 0, '这是第一条评论', '2024-06-13 16:13:11');
INSERT INTO `comments` VALUES (2, 2, 4, 'zzh1', 0, '这是活动2的评论', '2024-06-13 16:13:40');
INSERT INTO `comments` VALUES (3, 1, 5, 'wu', 1, '这是第一条评论的回复', '2024-06-13 16:14:41');
INSERT INTO `comments` VALUES (4, 1, 6, 'wuzongqian', 3, '这是第一条评论回复的回复', '2024-06-13 16:15:08');
INSERT INTO `comments` VALUES (19, 1, 7, 'zzh2', 0, '这是第4条评论', '2024-06-13 16:54:47');
INSERT INTO `comments` VALUES (22, 1, 7, 'zzh2', 0, '这是第4条评论', '2024-06-13 17:16:36');
INSERT INTO `comments` VALUES (23, 1, 7, 'zzh2', 0, '这是第4条评论', '2024-06-13 17:18:10');

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of expense_claims
-- ----------------------------
INSERT INTO `expense_claims` VALUES (6, 1, 123456, 100, '活动报销test', '待审核', '2024-06-13 17:04:31');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of feedbacks
-- ----------------------------
INSERT INTO `feedbacks` VALUES (1, 1, 4, 10, '非常好', '2024-06-13 16:42:55');

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
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notification_logs
-- ----------------------------
INSERT INTO `notification_logs` VALUES (1, 1, 1, 4, '4', '2024-06-13 17:04:33', '已发送');
INSERT INTO `notification_logs` VALUES (4, 2, 31, 1, '1', '2024-06-05 20:44:26', '已发送');
INSERT INTO `notification_logs` VALUES (6, 1, 1, 4, '4', '2024-06-13 16:42:57', '已发送');
INSERT INTO `notification_logs` VALUES (7, 1, 1, 4, '4', '2024-06-13 16:42:57', '已发送');
INSERT INTO `notification_logs` VALUES (8, 1, 1, 4, '4', '2024-06-13 16:42:57', '已发送');
INSERT INTO `notification_logs` VALUES (9, 1, 1, 4, '4', '2024-06-13 16:47:48', '已发送');
INSERT INTO `notification_logs` VALUES (10, 1, 1, 4, '4', '2024-06-13 16:47:48', '已发送');
INSERT INTO `notification_logs` VALUES (11, 1, 1, 4, '4', '2024-06-13 16:47:48', '已发送');
INSERT INTO `notification_logs` VALUES (12, 1, 1, 4, '4', '2024-06-13 16:51:13', '已发送');
INSERT INTO `notification_logs` VALUES (13, 1, 1, 4, '4', '2024-06-13 16:51:13', '已发送');
INSERT INTO `notification_logs` VALUES (14, 1, 1, 4, '4', '2024-06-13 16:51:13', '已发送');
INSERT INTO `notification_logs` VALUES (15, 1, 1, 4, '4', '2024-06-13 16:52:06', '已发送');
INSERT INTO `notification_logs` VALUES (16, 1, 1, 4, '4', '2024-06-13 16:52:06', '已发送');
INSERT INTO `notification_logs` VALUES (17, 1, 1, 4, '4', '2024-06-13 16:52:06', '已发送');
INSERT INTO `notification_logs` VALUES (18, 1, 1, 4, '4', '2024-06-13 16:53:24', '已发送');
INSERT INTO `notification_logs` VALUES (19, 1, 1, 4, '4', '2024-06-13 16:53:24', '已发送');
INSERT INTO `notification_logs` VALUES (20, 1, 1, 4, '4', '2024-06-13 16:53:24', '已发送');
INSERT INTO `notification_logs` VALUES (21, 1, 1, 4, '4', '2024-06-13 16:54:48', '已发送');
INSERT INTO `notification_logs` VALUES (22, 1, 1, 4, '4', '2024-06-13 16:54:48', '已发送');
INSERT INTO `notification_logs` VALUES (23, 1, 1, 4, '4', '2024-06-13 16:54:48', '已发送');
INSERT INTO `notification_logs` VALUES (24, 1, 1, 4, '4', '2024-06-13 16:57:25', '已发送');
INSERT INTO `notification_logs` VALUES (25, 1, 1, 4, '4', '2024-06-13 16:57:25', '已发送');
INSERT INTO `notification_logs` VALUES (26, 1, 1, 4, '4', '2024-06-13 16:57:25', '已发送');
INSERT INTO `notification_logs` VALUES (27, 1, 1, 4, '4', '2024-06-13 17:04:33', '已发送');
INSERT INTO `notification_logs` VALUES (28, 1, 1, 4, '4', '2024-06-13 17:04:33', '已发送');
INSERT INTO `notification_logs` VALUES (30, 1, 1, 4, '4', '2024-06-13 17:16:37', '已发送');
INSERT INTO `notification_logs` VALUES (31, 1, 1, 4, '4', '2024-06-13 17:16:37', '已发送');
INSERT INTO `notification_logs` VALUES (32, 1, 1, 4, '4', '2024-06-13 17:16:37', '已发送');
INSERT INTO `notification_logs` VALUES (33, 1, 1, 4, '4', '2024-06-13 17:18:10', '已发送');
INSERT INTO `notification_logs` VALUES (34, 1, 1, 4, '4', '2024-06-13 17:18:10', '已发送');
INSERT INTO `notification_logs` VALUES (35, 1, 1, 4, '4', '2024-06-13 17:18:10', '已发送');

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notification_receptions
-- ----------------------------
INSERT INTO `notification_receptions` VALUES (5, 67, 3, '2024-06-05 20:46:04', '2024-06-05 20:46:06');
INSERT INTO `notification_receptions` VALUES (6, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (7, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (8, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (9, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (10, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (11, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (12, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (13, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (14, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (15, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (16, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (17, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (18, 1, 4, NULL, NULL);
INSERT INTO `notification_receptions` VALUES (19, 1, 4, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notification_templates
-- ----------------------------
INSERT INTO `notification_templates` VALUES (1, '无敌模板', '无敌主题', '无敌结构', '2024-05-30 18:33:42', '2024-05-30 18:33:42');
INSERT INTO `notification_templates` VALUES (2, '无敌模', '无敌主题', '无敌结构', '2024-05-30 18:34:02', '2024-05-30 18:34:02');
INSERT INTO `notification_templates` VALUES (3, '模板', '主题', '结构', '2024-06-13 16:42:57', '2024-06-13 16:42:57');

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
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of participant_applications
-- ----------------------------
INSERT INTO `participant_applications` VALUES (1, 1, 11, 'sacascv', '已通过', '2024-06-13 16:09:58', '2024-06-13 16:24:44');
INSERT INTO `participant_applications` VALUES (2, 1, 6, 'wu', '已拒绝', '2024-06-13 16:10:21', '2024-06-13 16:24:44');
INSERT INTO `participant_applications` VALUES (4, 1, 11, 'sacascv', '申请中', '2024-06-13 16:11:33', '2024-06-13 16:11:33');
INSERT INTO `participant_applications` VALUES (5, 1, 4, 'zzh1', '申请中', '2024-06-13 16:24:44', '2024-06-13 16:24:44');
INSERT INTO `participant_applications` VALUES (6, 1, 11, 'sacascv', '申请中', '2024-06-13 16:24:44', '2024-06-13 16:24:44');
INSERT INTO `participant_applications` VALUES (7, 1, 4, 'zzh1', '申请中', '2024-06-13 16:29:51', '2024-06-13 16:29:51');
INSERT INTO `participant_applications` VALUES (8, 1, 11, 'sacascv', '申请中', '2024-06-13 16:29:51', '2024-06-13 16:29:51');
INSERT INTO `participant_applications` VALUES (9, 1, 4, 'zzh1', '申请中', '2024-06-13 16:39:16', '2024-06-13 16:39:16');
INSERT INTO `participant_applications` VALUES (10, 1, 11, 'sacascv', '申请中', '2024-06-13 16:39:17', '2024-06-13 16:39:17');
INSERT INTO `participant_applications` VALUES (11, 1, 4, 'zzh1', '申请中', '2024-06-13 16:42:52', '2024-06-13 16:42:52');
INSERT INTO `participant_applications` VALUES (12, 1, 11, 'sacascv', '申请中', '2024-06-13 16:42:52', '2024-06-13 16:42:52');
INSERT INTO `participant_applications` VALUES (13, 1, 4, 'zzh1', '申请中', '2024-06-13 16:47:43', '2024-06-13 16:47:43');
INSERT INTO `participant_applications` VALUES (14, 1, 11, 'sacascv', '申请中', '2024-06-13 16:47:43', '2024-06-13 16:47:43');
INSERT INTO `participant_applications` VALUES (15, 1, 4, 'zzh1', '申请中', '2024-06-13 16:51:08', '2024-06-13 16:51:08');
INSERT INTO `participant_applications` VALUES (16, 1, 11, 'sacascv', '申请中', '2024-06-13 16:51:08', '2024-06-13 16:51:08');
INSERT INTO `participant_applications` VALUES (17, 1, 4, 'zzh1', '申请中', '2024-06-13 16:52:02', '2024-06-13 16:52:02');
INSERT INTO `participant_applications` VALUES (18, 1, 11, 'sacascv', '申请中', '2024-06-13 16:52:02', '2024-06-13 16:52:02');
INSERT INTO `participant_applications` VALUES (19, 1, 4, 'zzh1', '申请中', '2024-06-13 16:53:19', '2024-06-13 16:53:19');
INSERT INTO `participant_applications` VALUES (20, 1, 11, 'sacascv', '申请中', '2024-06-13 16:53:19', '2024-06-13 16:53:19');
INSERT INTO `participant_applications` VALUES (21, 1, 4, 'zzh1', '申请中', '2024-06-13 16:54:43', '2024-06-13 16:54:43');
INSERT INTO `participant_applications` VALUES (22, 1, 11, 'sacascv', '申请中', '2024-06-13 16:54:43', '2024-06-13 16:54:43');
INSERT INTO `participant_applications` VALUES (23, 1, 4, 'zzh1', '申请中', '2024-06-13 16:57:20', '2024-06-13 16:57:20');
INSERT INTO `participant_applications` VALUES (24, 1, 11, 'sacascv', '申请中', '2024-06-13 16:57:20', '2024-06-13 16:57:20');
INSERT INTO `participant_applications` VALUES (25, 1, 4, 'zzh1', '申请中', '2024-06-13 17:04:28', '2024-06-13 17:04:28');
INSERT INTO `participant_applications` VALUES (26, 1, 11, 'sacascv', '申请中', '2024-06-13 17:04:28', '2024-06-13 17:04:28');
INSERT INTO `participant_applications` VALUES (27, 1, 4, 'zzh1', '申请中', '2024-06-13 17:16:32', '2024-06-13 17:16:32');
INSERT INTO `participant_applications` VALUES (28, 1, 11, 'sacascv', '申请中', '2024-06-13 17:16:32', '2024-06-13 17:16:32');
INSERT INTO `participant_applications` VALUES (29, 1, 4, 'zzh1', '申请中', '2024-06-13 17:18:05', '2024-06-13 17:18:05');
INSERT INTO `participant_applications` VALUES (30, 1, 11, 'sacascv', '申请中', '2024-06-13 17:18:05', '2024-06-13 17:18:05');

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
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of participants
-- ----------------------------
INSERT INTO `participants` VALUES (1, 1, 4, '参与者', 'zzh1', NULL);
INSERT INTO `participants` VALUES (2, 2, 4, '组织者', 'zzh1', NULL);
INSERT INTO `participants` VALUES (18, 5, 4, '组织者', 'zzh1', NULL);
INSERT INTO `participants` VALUES (24, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (25, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (26, 10, 4, '组织者', 'zzh1', NULL);
INSERT INTO `participants` VALUES (27, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (28, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (29, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (30, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (31, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (32, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (33, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (34, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (35, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (36, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (37, 1, 123456, 'speaker', 'zzh', NULL);
INSERT INTO `participants` VALUES (38, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (39, 1, 11, '参与者', 'sacascv', NULL);
INSERT INTO `participants` VALUES (40, 11, 5, '组织者', 'Zack', NULL);

-- ----------------------------
-- Table structure for resource_allocations
-- ----------------------------
DROP TABLE IF EXISTS `resource_allocations`;
CREATE TABLE `resource_allocations`  (
  `allocation_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NULL DEFAULT NULL,
  `resource_id` int NULL DEFAULT NULL,
  `resource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `allocated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`allocation_id`) USING BTREE,
  INDEX `resource_id`(`resource_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resource_allocations
-- ----------------------------
INSERT INTO `resource_allocations` VALUES (1, 1, 1, '篮球场', '2024-06-07 14:23:11', '2024-06-07 14:23:17', '2024-06-06 14:23:20');
INSERT INTO `resource_allocations` VALUES (2, 10, 1, '篮球场', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-06-06 14:37:23');
INSERT INTO `resource_allocations` VALUES (3, 10, 1, '篮球场', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-06-06 14:56:01');
INSERT INTO `resource_allocations` VALUES (4, 10, 1, '篮球场', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-06-06 15:00:23');
INSERT INTO `resource_allocations` VALUES (5, 10, 1, '篮球场', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-06-06 15:39:00');
INSERT INTO `resource_allocations` VALUES (6, 10, 1, '篮球场', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-06-06 15:40:12');
INSERT INTO `resource_allocations` VALUES (7, 10, 1, '篮球场', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-06-06 15:41:28');
INSERT INTO `resource_allocations` VALUES (8, 10, 1, '篮球场', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-06-06 16:23:53');
INSERT INTO `resource_allocations` VALUES (9, 10, 2, '篮球场', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-06-06 17:09:06');
INSERT INTO `resource_allocations` VALUES (10, 10, 2, '篮球场', '2024-06-04 13:38:20', '2024-06-04 14:38:20', '2024-06-06 19:35:08');
INSERT INTO `resource_allocations` VALUES (12, 10, 1, '篮球场', '2025-12-04 08:00:00', '2025-12-04 22:00:00', '2024-06-11 11:49:32');
INSERT INTO `resource_allocations` VALUES (13, 10, 1, '篮球场', '2026-01-04 08:00:00', '2026-01-04 22:00:00', '2024-06-13 16:43:00');

-- ----------------------------
-- Table structure for resource_bookings
-- ----------------------------
DROP TABLE IF EXISTS `resource_bookings`;
CREATE TABLE `resource_bookings`  (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `resource_id` int NOT NULL,
  `resource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `activity_id` int NOT NULL,
  `booked_by` int NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`booking_id`) USING BTREE,
  INDEX `resource_id`(`resource_id` ASC) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `booked_by`(`booked_by` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resource_bookings
-- ----------------------------
INSERT INTO `resource_bookings` VALUES (4, 1, '篮球场', 10, 4, '已审批', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-05-30 18:45:03');
INSERT INTO `resource_bookings` VALUES (6, 2, '篮球场', 10, 4, '已审批', '2024-05-30 13:20:20', '2024-05-30 15:20:29', '2024-06-02 14:18:15');
INSERT INTO `resource_bookings` VALUES (8, 1, '篮球场', 10, 4, '拒绝', '2024-06-03 13:38:20', '2024-06-03 14:20:29', '2024-06-03 13:37:49');
INSERT INTO `resource_bookings` VALUES (9, 1, '篮球场', 10, 4, '拒绝', '2024-06-04 13:38:20', '2024-06-04 14:20:29', '2024-06-03 13:54:47');
INSERT INTO `resource_bookings` VALUES (10, 2, '篮球场', 10, 4, '已审批', '2024-06-04 13:38:20', '2024-06-04 14:38:20', '2024-06-03 14:04:15');
INSERT INTO `resource_bookings` VALUES (12, 1, '篮球场', 10, 4, '拒绝', '2025-01-04 08:00:00', '2025-01-04 22:00:00', '2024-06-03 14:30:49');
INSERT INTO `resource_bookings` VALUES (13, 1, '篮球场', 10, 4, '已审批', '2025-12-04 08:00:00', '2025-12-04 22:00:00', '2024-06-03 14:31:01');
INSERT INTO `resource_bookings` VALUES (14, 1, '篮球场', 10, 4, '已审批', '2026-01-04 08:00:00', '2026-01-04 22:00:00', '2024-06-03 14:31:16');
INSERT INTO `resource_bookings` VALUES (15, 1, '篮球场', 10, 4, '拒绝', '2024-07-04 08:00:00', '2024-07-04 22:00:00', '2024-06-03 14:31:50');
INSERT INTO `resource_bookings` VALUES (16, 2, '篮球场', 10, 4, '申请中', '2024-07-04 08:00:00', '2024-07-04 22:00:00', '2024-06-03 14:32:35');
INSERT INTO `resource_bookings` VALUES (17, 1, '篮球场', 10, 4, '申请中', '2024-06-30 13:20:20', '2024-06-30 15:20:29', '2024-06-06 12:47:39');
INSERT INTO `resource_bookings` VALUES (18, 2, '篮球场', 10, 4, '申请中', '2024-06-30 13:20:20', '2024-06-30 15:20:29', '2024-06-06 12:55:23');
INSERT INTO `resource_bookings` VALUES (20, 1, '篮球场', 10, 4, '申请中', '2024-06-10 11:20:20', '2024-06-10 15:20:29', '2024-06-06 14:31:40');
INSERT INTO `resource_bookings` VALUES (21, 2, '篮球场', 10, 4, '申请中', '2024-06-10 11:20:20', '2024-06-10 15:20:29', '2024-06-06 14:31:43');
INSERT INTO `resource_bookings` VALUES (23, 1, '篮球场', 10, 4, '申请中', '2024-06-11 11:20:20', '2024-06-11 15:20:29', '2024-06-06 14:32:01');
INSERT INTO `resource_bookings` VALUES (24, 2, '篮球场', 10, 4, '申请中', '2024-06-11 10:20:20', '2024-06-11 15:20:29', '2024-06-06 14:32:08');
INSERT INTO `resource_bookings` VALUES (28, 1, '篮球场', 10, 4, '申请中', '2024-08-04 08:00:00', '2024-08-04 22:00:00', '2024-06-11 11:45:08');
INSERT INTO `resource_bookings` VALUES (29, 2, '篮球场', 10, 4, '申请中', '2024-08-04 08:00:00', '2024-08-04 22:00:00', '2024-06-11 11:45:10');
INSERT INTO `resource_bookings` VALUES (31, 1, '篮球场', 4, 4, '申请中', '2024-08-05 08:00:00', '2024-08-05 22:00:00', '2024-06-13 16:42:58');
INSERT INTO `resource_bookings` VALUES (32, 2, '篮球场', 4, 4, '拒绝', '2024-08-05 08:00:00', '2024-08-05 22:00:00', '2024-06-13 16:47:49');

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES (1, '篮球场', '场地', '111', '可用', '2024-06-02 14:12:41', '2024-06-02 14:12:41');
INSERT INTO `resources` VALUES (2, '篮球场', '场地', '111', '可用', '2024-06-02 14:13:54', '2024-06-02 14:13:54');
INSERT INTO `resources` VALUES (4, '报告厅', '场地', '22222', '可用', '2024-06-13 16:42:57', '2024-06-13 16:42:57');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 123457 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (4, 'zzh1', '111', 'zzh18736680168@outlook.com1', '2024-05-22 19:10:48', '2024-06-13 17:18:12', 1);
INSERT INTO `users` VALUES (5, 'Zack', '234', 'vrev', '2024-05-23 17:31:48', '2024-06-04 17:15:02', 2);
INSERT INTO `users` VALUES (6, 'wu', 'adcnjkc', 'csac@ouy.com', '2024-05-23 17:32:13', '2024-05-23 17:32:13', 0);
INSERT INTO `users` VALUES (7, 'wuzongqian', 'asdcv', 'xasc@ustb.com', '2024-05-23 17:32:29', '2024-05-23 17:32:29', 0);
INSERT INTO `users` VALUES (8, 'liboyang', 'sacasdVCW', 'x as@ustb.com', '2024-05-23 17:32:44', '2024-05-23 17:32:44', 0);
INSERT INTO `users` VALUES (9, 'cx asCV', 'xsqac@cxds.com', 'xcasc@xasc.com', '2024-05-23 17:33:04', '2024-05-23 17:33:04', 0);
INSERT INTO `users` VALUES (10, 'sac saDC', 'xsaxcc', 'XAAX@casc.com', '2024-05-23 17:33:19', '2024-05-23 17:33:19', 0);
INSERT INTO `users` VALUES (11, 'sacascv', 'sacqw', 'xsac@outlii.com', '2024-05-23 17:33:35', '2024-06-06 19:33:45', 1);
INSERT INTO `users` VALUES (12, 'cxwsqac', 'cxasc', 'xcsacs@casc.com', '2024-05-23 17:33:44', '2024-06-11 11:46:09', 1);
INSERT INTO `users` VALUES (13, 'csaCEASQ', 'xascwq', 'saca@mic.com', '2024-05-23 17:33:56', '2024-05-23 17:33:56', 0);
INSERT INTO `users` VALUES (14, ' asc', 'xaxasc', 'CXA@ncui.com', '2024-05-23 17:34:08', '2024-06-04 19:09:47', 1);
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
