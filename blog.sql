/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost
 Source Database       : blog

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : utf-8

 Date: 07/10/2019 17:03:45 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `blog_info`
-- ----------------------------
DROP TABLE IF EXISTS `blog_info`;
CREATE TABLE `blog_info` (
  `blog_id` varchar(25) NOT NULL COMMENT '博客信息主键',
  `author_id` varchar(25) DEFAULT NULL COMMENT '作者Id',
  `blog_title` varchar(50) DEFAULT NULL COMMENT '博客标题',
  `blog_summary` varchar(200) DEFAULT NULL COMMENT '博客摘要',
  `blog_content` text COMMENT '内容',
  `blog_html_content` text COMMENT 'html内容',
  `blog_category` int(11) DEFAULT '0' COMMENT '分类Id',
  `blog_user_category` int(11) DEFAULT '0' COMMENT '用户自定义分类的Id',
  `blog_reading` int(11) DEFAULT '0' COMMENT '阅读次数',
  `blog_comments` int(11) DEFAULT '0' COMMENT '评论数',
  `blog_likes` int(11) DEFAULT '0' COMMENT '赞点数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `blog_original` tinyint(11) DEFAULT '0' COMMENT '类型(原创、翻译、转载)',
  `blog_audit` tinyint(4) DEFAULT '0' COMMENT '状态(未审核、审核通过、未通过、草稿)',
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `category_info`
-- ----------------------------
DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) DEFAULT NULL COMMENT '分类名称',
  `category_num` int(11) DEFAULT NULL COMMENT '此分类下的文章数量',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `category_info`
-- ----------------------------
BEGIN;
INSERT INTO `category_info` VALUES ('1', '心智探寻', '4'), ('2', '学习方法', '0'), ('3', '计算机科学', '2'), ('4', '机器学习', '6'), ('5', '编程', '4'), ('6', '算法', '2'), ('7', '数学', '1');
COMMIT;

-- ----------------------------
--  Table structure for `category_user`
-- ----------------------------
DROP TABLE IF EXISTS `category_user`;
CREATE TABLE `category_user` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) DEFAULT NULL COMMENT '用户分类名称',
  `category_num` int(11) DEFAULT NULL COMMENT '用户此分类下的文章数量',
  `user_id` varchar(25) DEFAULT NULL COMMENT '用户Id',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `category_user`
-- ----------------------------
BEGIN;
INSERT INTO `category_user` VALUES ('1', 'Spring框架', '41', '123456'), ('2', 'JavaEE框架', '3', '123456'), ('3', 'C/C++', '6', '123456'), ('4', '软件测试', '6', '123457'), ('5', 'Linux系统编程', '3', '123456'), ('6', 'IDEA使用技巧', '1', '123456'), ('7', '测试用例', '3', '123457'), ('8', 'JavaEE框架', '0', '123456'), ('9', 'C/C++', '0', '123456'), ('10', '测试工具', '3', '123457');
COMMIT;

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(25) NOT NULL,
  `user_name` varchar(25) DEFAULT NULL,
  `user_email` varchar(25) DEFAULT NULL,
  `user_password` varchar(30) DEFAULT NULL,
  `user_pages` int(11) DEFAULT NULL COMMENT '用户文章数',
  `user_comment` int(11) DEFAULT NULL COMMENT '评论数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user_info`
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('123456', '邹长林', '15291418231@163.com', '123456', '0', '0', '2019-07-02 21:37:29', '2019-07-02 21:50:40'), ('123457', '黎红丽', '13772812804@163.com', '123456', null, null, '2019-07-02 21:37:29', '2019-07-02 22:33:18');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
