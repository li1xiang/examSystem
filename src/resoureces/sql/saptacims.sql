/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : saptacims

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-03-25 16:24:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_answer
-- ----------------------------
DROP TABLE IF EXISTS `tb_answer`;
CREATE TABLE `tb_answer` (
  `ANSWER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '答案ID',
  `QUESTION_ID` int(11) NOT NULL COMMENT '题目ID',
  `ANSWER` varchar(500) DEFAULT NULL COMMENT '参考答案',
  `ANSWER_IMG` varchar(500) DEFAULT NULL COMMENT '图片参考答案地址',
  `ANSWER_IMG_NAME` varchar(200) DEFAULT NULL COMMENT '图片参考答案名称',
  `ISRIGHT` int(1) DEFAULT NULL COMMENT '是否正确答案(Y是N否)',
  `CREATE_USER` int(11) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`ANSWER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=500 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_answer
-- ----------------------------
INSERT INTO `tb_answer` VALUES ('492', '327', '这个意思要自己思考', null, null, '1', '1', '2019-03-23 18:05:02', '1', '2019-03-23 18:05:02');
INSERT INTO `tb_answer` VALUES ('493', '328', '要自己理解', null, null, '1', '1', '2019-03-23 18:07:50', '1', '2019-03-23 18:07:50');
INSERT INTO `tb_answer` VALUES ('494', '329', '2', null, null, '1', '1', '2019-03-23 18:09:31', '1', '2019-03-23 18:09:31');
INSERT INTO `tb_answer` VALUES ('495', '329', '3', null, null, '0', '1', '2019-03-23 18:09:31', '1', '2019-03-23 18:09:31');
INSERT INTO `tb_answer` VALUES ('496', '329', '32', null, null, '0', '1', '2019-03-23 18:09:31', '1', '2019-03-23 18:09:31');
INSERT INTO `tb_answer` VALUES ('497', '329', '33', null, null, '0', '1', '2019-03-23 18:09:31', '1', '2019-03-23 18:09:31');
INSERT INTO `tb_answer` VALUES ('498', '329', '5', null, null, '0', '1', '2019-03-23 18:09:31', '1', '2019-03-23 18:09:31');
INSERT INTO `tb_answer` VALUES ('499', '330', 'saf', null, null, '1', '1', '2019-03-23 18:11:47', '1', '2019-03-23 18:11:47');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_STATUS` int(2) DEFAULT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='类别管理表';

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('126', '一年级', '1', '1', '1', '2019-03-23 17:48:59', '2019-03-23 17:48:59');

-- ----------------------------
-- Table structure for tb_examination
-- ----------------------------
DROP TABLE IF EXISTS `tb_examination`;
CREATE TABLE `tb_examination` (
  `EXAMINATION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EXAMINATION_NAME` varchar(100) DEFAULT NULL,
  `EXAMINATION_REMARK` varchar(500) DEFAULT NULL,
  `EXAMINATION_STATUS` int(1) DEFAULT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `TOTAL_SCORE` int(3) DEFAULT NULL,
  PRIMARY KEY (`EXAMINATION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_examination
-- ----------------------------
INSERT INTO `tb_examination` VALUES ('32', '阶段测试', '', '1', '1', '2019-03-23 18:12:58', null, null, '100');
INSERT INTO `tb_examination` VALUES ('33', '23', '', '1', '1', '2019-03-23 18:16:42', null, null, '100');

-- ----------------------------
-- Table structure for tb_examination_answer
-- ----------------------------
DROP TABLE IF EXISTS `tb_examination_answer`;
CREATE TABLE `tb_examination_answer` (
  `EXAMINATION_ANSWER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷答案ID',
  `ANSWER_ID` int(11) NOT NULL COMMENT '答案ID',
  `QUESTION_ID` int(11) NOT NULL COMMENT '试卷题目ID',
  `ANSWER_TEXT` varchar(500) NOT NULL COMMENT '答案',
  `ISRIGHT` int(1) DEFAULT NULL COMMENT '是否正确答案(1是0否)',
  `CREATE_USER` int(11) NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`EXAMINATION_ANSWER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=545 DEFAULT CHARSET=utf8 COMMENT='试卷答案表';

-- ----------------------------
-- Records of tb_examination_answer
-- ----------------------------
INSERT INTO `tb_examination_answer` VALUES ('537', '493', '316', '要自己理解', '1', '1', '2019-03-23 18:12:58', null, null);
INSERT INTO `tb_examination_answer` VALUES ('538', '499', '317', 'saf', '1', '1', '2019-03-23 18:12:58', null, null);
INSERT INTO `tb_examination_answer` VALUES ('539', '499', '318', 'saf', '1', '1', '2019-03-23 18:16:42', null, null);
INSERT INTO `tb_examination_answer` VALUES ('540', '494', '319', '2', '1', '1', '2019-03-23 18:16:42', null, null);
INSERT INTO `tb_examination_answer` VALUES ('541', '495', '319', '3', '0', '1', '2019-03-23 18:16:42', null, null);
INSERT INTO `tb_examination_answer` VALUES ('542', '496', '319', '32', '0', '1', '2019-03-23 18:16:42', null, null);
INSERT INTO `tb_examination_answer` VALUES ('543', '497', '319', '33', '0', '1', '2019-03-23 18:16:42', null, null);
INSERT INTO `tb_examination_answer` VALUES ('544', '498', '319', '5', '0', '1', '2019-03-23 18:16:42', null, null);

-- ----------------------------
-- Table structure for tb_examination_interviewer_rl
-- ----------------------------
DROP TABLE IF EXISTS `tb_examination_interviewer_rl`;
CREATE TABLE `tb_examination_interviewer_rl` (
  `RL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EXAMINATION_ID` int(11) NOT NULL COMMENT '试卷ID',
  `INTERVIEWER_ID` int(11) NOT NULL COMMENT '面试者ID',
  `CREATE_USER` int(11) NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`RL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT='试题与面试者关系表';

-- ----------------------------
-- Records of tb_examination_interviewer_rl
-- ----------------------------

-- ----------------------------
-- Table structure for tb_examination_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_examination_question`;
CREATE TABLE `tb_examination_question` (
  `EXAMINATION_QUESTION_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷试题ID',
  `QUESTION_ID` int(11) NOT NULL COMMENT '试题ID',
  `EXAMINATION_ID` int(11) NOT NULL COMMENT '试卷ID',
  `CATEGORY_ID` int(11) NOT NULL COMMENT '类别ID',
  `GROUP_ID` int(11) NOT NULL COMMENT '群组ID',
  `LEVELS` int(1) NOT NULL COMMENT '难度',
  `QUESTION_TITLE` varchar(500) DEFAULT NULL COMMENT '题目',
  `QUESTION_TYPE` int(1) NOT NULL COMMENT '题型',
  `QUESTION_IMG_PATH` varchar(500) DEFAULT NULL COMMENT '图片地址',
  `QUESTION_IMG_NAME` varchar(500) DEFAULT NULL COMMENT '图片名称',
  `TOTAL_SCORE` int(255) DEFAULT NULL COMMENT '总分',
  `CREATE_USER` int(11) NOT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`EXAMINATION_QUESTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8 COMMENT='试卷题目表';

-- ----------------------------
-- Records of tb_examination_question
-- ----------------------------
INSERT INTO `tb_examination_question` VALUES ('316', '328', '32', '126', '45', '3', '少小离家老大回所表达的意思？', '0', null, null, '2', '1', '2019-03-23 18:12:58', null, null);
INSERT INTO `tb_examination_question` VALUES ('317', '330', '32', '126', '45', '3', 'dg', '0', null, null, '98', '1', '2019-03-23 18:12:58', null, null);
INSERT INTO `tb_examination_question` VALUES ('318', '330', '33', '126', '45', '3', 'dg', '0', null, null, '98', '1', '2019-03-23 18:16:42', null, null);
INSERT INTO `tb_examination_question` VALUES ('319', '329', '33', '126', '46', '3', '1+1=？', '1', null, null, '2', '1', '2019-03-23 18:16:42', null, null);

-- ----------------------------
-- Table structure for tb_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_group`;
CREATE TABLE `tb_group` (
  `GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GROUP_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_STATUS` int(2) DEFAULT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `REMARK` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='群组管理表';

-- ----------------------------
-- Records of tb_group
-- ----------------------------
INSERT INTO `tb_group` VALUES ('45', '语文', '1', '1', '2019-03-23 17:44:33', '1', '2019-03-23 17:56:42', 'sdf');
INSERT INTO `tb_group` VALUES ('46', '数学', '1', '1', '2019-03-23 17:56:30', '1', '2019-03-23 17:56:30', '一年级数学');

-- ----------------------------
-- Table structure for tb_interviewer
-- ----------------------------
DROP TABLE IF EXISTS `tb_interviewer`;
CREATE TABLE `tb_interviewer` (
  `INTERVIEWER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '面试者ID',
  `NAME` varchar(128) NOT NULL COMMENT '姓名',
  `BIRTH` timestamp NULL DEFAULT NULL COMMENT '出生日期',
  `PHONE` varchar(15) NOT NULL COMMENT '手机号码',
  `EDUCATION` varchar(32) DEFAULT NULL COMMENT '学历',
  `MAJOR` varchar(128) DEFAULT NULL COMMENT '专业',
  `WORKING_YEARS` int(2) DEFAULT NULL COMMENT '工作年限',
  `SOURCE` int(1) DEFAULT NULL COMMENT '面试者类型（自招，外包）',
  `COMPANY` varchar(255) DEFAULT NULL COMMENT '工作单位(若是外包，则填写)',
  `ACTIVE` int(1) NOT NULL COMMENT '状态',
  `CREATE_USER` int(11) NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `ATTACHNAME` varchar(255) DEFAULT NULL COMMENT '简历文件名称',
  `ATTACHPATH` varchar(255) DEFAULT NULL COMMENT '简历文件路径+名称（服务器）',
  PRIMARY KEY (`INTERVIEWER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='面试者表';

-- ----------------------------
-- Records of tb_interviewer
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `MENU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `MENU_URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `MENU_PARENTID` int(11) DEFAULT NULL,
  `MENU_LEVEL` int(2) DEFAULT NULL,
  `SORT` int(2) DEFAULT NULL,
  `STATUS` int(2) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '账户管理', null, '2017-01-19 17:48:11', '1', '0', '1', '1', '1');
INSERT INTO `tb_menu` VALUES ('2', '新增用户', '/user/newUser', '2017-01-19 17:48:46', '1', '1', '2', '1', '1');
INSERT INTO `tb_menu` VALUES ('3', '用户管理', '/user/userManage', '2017-01-19 17:49:10', '1', '1', '2', '2', '1');
INSERT INTO `tb_menu` VALUES ('4', '权限管理', '/role/roleManage', '2017-01-19 17:49:31', '1', '1', '2', '3', '1');
INSERT INTO `tb_menu` VALUES ('6', '年级管理', '/category/typeManage', '2017-01-20 10:07:18', '1', '9', '2', '1', '1');
INSERT INTO `tb_menu` VALUES ('7', '科目管理', '/group/groupManage', '2017-01-20 10:08:01', '1', '9', '2', '2', '1');
INSERT INTO `tb_menu` VALUES ('9', '题库管理', null, '2017-01-20 10:08:44', '1', '0', '1', '3', '1');
INSERT INTO `tb_menu` VALUES ('10', '问答题录入', '/question/getAddSubjectQuestion', '2017-01-20 10:09:08', '1', '9', '2', '3', '1');
INSERT INTO `tb_menu` VALUES ('12', '试卷管理', null, '2017-01-20 10:09:45', '1', '0', '1', '4', '1');
INSERT INTO `tb_menu` VALUES ('13', '手工新增试卷', '/examination/addExamination', '2017-01-20 10:10:05', '1', '12', '2', '3', '1');
INSERT INTO `tb_menu` VALUES ('14', '自动新增试卷', '', '2017-01-20 10:10:38', '1', '12', '2', '4', '1');
INSERT INTO `tb_menu` VALUES ('15', '试卷列表维护', '/examination/examinationManage', '2017-01-20 10:10:53', '1', '12', '2', '5', '1');
INSERT INTO `tb_menu` VALUES ('16', '笔试答题', '/paper/choosePaper', '2017-01-20 10:11:19', '1', '0', '1', '6', '1');
INSERT INTO `tb_menu` VALUES ('17', '阅卷管理', null, '2017-01-20 10:11:39', '1', '0', '1', '7', '1');
INSERT INTO `tb_menu` VALUES ('19', '答卷列表', '/marking/toPaperList', '2017-01-20 10:12:16', '1', '17', '2', '2', '1');
INSERT INTO `tb_menu` VALUES ('22', '笔试答题', '/paper/choosePaper', '2017-01-22 14:25:26', '1', '16', '2', '1', '1');
INSERT INTO `tb_menu` VALUES ('23', '考试管理', '/interviewer/interviewerManage', '2017-01-23 11:17:19', '1', '12', '2', '1', '1');
INSERT INTO `tb_menu` VALUES ('25', '个人密码修改', '/user/changePassword', '2017-02-10 11:17:19', '1', '1', '2', '8', '1');
INSERT INTO `tb_menu` VALUES ('26', '题库查询', '/question/getQuestionSelect', '2017-02-22 16:24:39', '1', '9', '2', '6', '1');
INSERT INTO `tb_menu` VALUES ('27', '试题发布', '/question/getQuestionManage', '2017-02-24 10:56:37', '1', '9', '2', '5', '1');
INSERT INTO `tb_menu` VALUES ('28', '选择题题录入', '/question/getAddObjectQuestion', '2017-02-24 11:03:43', '1', '9', '2', '4', '1');
INSERT INTO `tb_menu` VALUES ('29', '我的信息', '/user/queryMyMessage', '2017-02-28 16:40:11', '1', '1', '2', '7', '1');
INSERT INTO `tb_menu` VALUES ('31', '判断题录入', '/question/getAddObjectQuestion', '2019-03-23 12:55:49', '1', '9', '2', '9', '1');

-- ----------------------------
-- Table structure for tb_paper
-- ----------------------------
DROP TABLE IF EXISTS `tb_paper`;
CREATE TABLE `tb_paper` (
  `PAPER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '答卷ID',
  `EXAMINATION_ID` int(11) NOT NULL COMMENT '试卷ID',
  `PAPER_USER` int(11) NOT NULL COMMENT '答题人',
  `PAPER_START_TIME` timestamp NULL DEFAULT NULL COMMENT '答题开始时间',
  `PAPER_END_TIME` timestamp NULL DEFAULT NULL COMMENT '答题结束时间',
  `SCORE` int(3) DEFAULT NULL COMMENT '总分',
  `SCORE_REMARKS` varchar(255) DEFAULT NULL COMMENT '评分说明',
  `MARKING_MAN` int(11) DEFAULT NULL COMMENT '阅卷人',
  `SUBMIT_STATUS` int(1) DEFAULT NULL COMMENT '交卷状态',
  `CREATE_USER` int(11) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`PAPER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COMMENT='答卷表';

-- ----------------------------
-- Records of tb_paper
-- ----------------------------

-- ----------------------------
-- Table structure for tb_paper_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_paper_detail`;
CREATE TABLE `tb_paper_detail` (
  `PAPER_DETAIL_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '答卷明细ID',
  `PAPER_ID` int(11) NOT NULL COMMENT '答卷ID',
  `QUESTION_ID` int(11) NOT NULL COMMENT '题目ID',
  `ANSWER_ID` int(11) DEFAULT NULL COMMENT '面试者答题答案(客观题)',
  `SUBJECTIVE_ANSWER` varchar(1024) DEFAULT NULL COMMENT '答题答案（主观题）',
  `SCORE` int(3) DEFAULT NULL COMMENT '得分',
  `CREATE_USER` int(11) NOT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`PAPER_DETAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8 COMMENT='答卷明细表';

-- ----------------------------
-- Records of tb_paper_detail
-- ----------------------------

-- ----------------------------
-- Table structure for tb_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_question`;
CREATE TABLE `tb_question` (
  `QUESTION_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '试题ID',
  `QUESTION_TYPE` int(1) NOT NULL COMMENT '题型',
  `CATEGORY_ID` int(11) NOT NULL COMMENT '类别ID',
  `GROUP_ID` int(11) NOT NULL COMMENT '群组ID',
  `SCORE` int(3) NOT NULL COMMENT '分数',
  `LEVELS` int(1) NOT NULL COMMENT '难度',
  `SUBJECT` varchar(500) DEFAULT NULL COMMENT '题目',
  `SUBJECT_IMG` varchar(500) DEFAULT NULL COMMENT '图片地址',
  `SUBJECT_IMG_NAME` varchar(200) DEFAULT NULL COMMENT '图片名称',
  `ACTIVE` int(1) NOT NULL COMMENT '是否可用',
  `CREATE_USER` int(11) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`QUESTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8 COMMENT='题目表';

-- ----------------------------
-- Records of tb_question
-- ----------------------------
INSERT INTO `tb_question` VALUES ('328', '0', '126', '45', '2', '3', '少小离家老大回所表达的意思？', null, null, '1', '1', '2019-03-23 18:10:44', '1', '2019-03-23 18:07:50');
INSERT INTO `tb_question` VALUES ('329', '1', '126', '46', '2', '3', '1+1=？', null, null, '1', '1', '2019-03-23 18:10:44', '1', '2019-03-23 18:09:31');
INSERT INTO `tb_question` VALUES ('330', '0', '126', '45', '98', '3', 'dg', null, null, '1', '1', '2019-03-23 18:12:15', '1', '2019-03-23 18:11:47');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '学生', '2017-03-06 12:15:30', '-1', '2017-04-01 10:37:54', '-1');
INSERT INTO `tb_role` VALUES ('2', '老师', '2019-03-17 13:54:27', '-1', '2019-03-17 13:54:34', '-1');
INSERT INTO `tb_role` VALUES ('3', '管理员', '2019-03-17 13:55:02', '-2', '2019-03-17 13:55:08', '-2');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) DEFAULT NULL,
  `MENU_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=709 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('666', '2', '29');
INSERT INTO `tb_role_menu` VALUES ('667', '2', '25');
INSERT INTO `tb_role_menu` VALUES ('668', '2', '9');
INSERT INTO `tb_role_menu` VALUES ('669', '2', '6');
INSERT INTO `tb_role_menu` VALUES ('670', '2', '7');
INSERT INTO `tb_role_menu` VALUES ('671', '2', '10');
INSERT INTO `tb_role_menu` VALUES ('672', '2', '28');
INSERT INTO `tb_role_menu` VALUES ('673', '2', '27');
INSERT INTO `tb_role_menu` VALUES ('674', '2', '26');
INSERT INTO `tb_role_menu` VALUES ('675', '2', '12');
INSERT INTO `tb_role_menu` VALUES ('676', '2', '23');
INSERT INTO `tb_role_menu` VALUES ('677', '2', '13');
INSERT INTO `tb_role_menu` VALUES ('678', '2', '14');
INSERT INTO `tb_role_menu` VALUES ('679', '2', '15');
INSERT INTO `tb_role_menu` VALUES ('680', '2', '17');
INSERT INTO `tb_role_menu` VALUES ('681', '2', '19');
INSERT INTO `tb_role_menu` VALUES ('682', '3', '1');
INSERT INTO `tb_role_menu` VALUES ('683', '3', '2');
INSERT INTO `tb_role_menu` VALUES ('684', '3', '3');
INSERT INTO `tb_role_menu` VALUES ('685', '3', '4');
INSERT INTO `tb_role_menu` VALUES ('686', '3', '30');
INSERT INTO `tb_role_menu` VALUES ('687', '3', '29');
INSERT INTO `tb_role_menu` VALUES ('688', '3', '25');
INSERT INTO `tb_role_menu` VALUES ('689', '3', '9');
INSERT INTO `tb_role_menu` VALUES ('690', '3', '6');
INSERT INTO `tb_role_menu` VALUES ('691', '3', '7');
INSERT INTO `tb_role_menu` VALUES ('692', '3', '10');
INSERT INTO `tb_role_menu` VALUES ('693', '3', '28');
INSERT INTO `tb_role_menu` VALUES ('694', '3', '27');
INSERT INTO `tb_role_menu` VALUES ('695', '3', '26');
INSERT INTO `tb_role_menu` VALUES ('696', '3', '12');
INSERT INTO `tb_role_menu` VALUES ('697', '3', '23');
INSERT INTO `tb_role_menu` VALUES ('698', '3', '13');
INSERT INTO `tb_role_menu` VALUES ('699', '3', '14');
INSERT INTO `tb_role_menu` VALUES ('700', '3', '15');
INSERT INTO `tb_role_menu` VALUES ('701', '3', '16');
INSERT INTO `tb_role_menu` VALUES ('702', '3', '22');
INSERT INTO `tb_role_menu` VALUES ('703', '3', '17');
INSERT INTO `tb_role_menu` VALUES ('704', '3', '19');
INSERT INTO `tb_role_menu` VALUES ('705', '1', '29');
INSERT INTO `tb_role_menu` VALUES ('706', '1', '25');
INSERT INTO `tb_role_menu` VALUES ('707', '1', '16');
INSERT INTO `tb_role_menu` VALUES ('708', '1', '22');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_CNAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `USER_PASSWORD` varchar(100) COLLATE utf8_bin NOT NULL,
  `ACCOUNT` varchar(100) COLLATE utf8_bin NOT NULL,
  `USER_STATUS` int(11) NOT NULL,
  `SEX` int(11) NOT NULL,
  `STU_NO` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PHONE` varchar(100) COLLATE utf8_bin NOT NULL,
  `StuCLASS` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `ACCOUNT` (`ACCOUNT`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '系统管理员', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '1', '1', '', '18860985160', '', null, null, '2019-03-17 22:04:51', '1');
INSERT INTO `tb_user` VALUES ('131', '李翔', 'e10adc3949ba59abbe56e057f20f883e', 'lixiang', '1', '0', '522019808', '15675434356', null, '2019-03-09 14:29:51', '1', '2019-03-09 14:30:22', '1');
INSERT INTO `tb_user` VALUES ('132', '李翔', 'e10adc3949ba59abbe56e057f20f883e', 'lixiang1', '1', '0', '520202020', '15375178625', '1', '2019-03-20 12:18:50', '1', '2019-03-20 12:19:26', '1');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '3');
INSERT INTO `tb_user_role` VALUES ('116', '131', '1');
INSERT INTO `tb_user_role` VALUES ('117', '132', '1');
