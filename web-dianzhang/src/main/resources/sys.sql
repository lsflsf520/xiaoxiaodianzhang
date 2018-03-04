-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: online_insure_api_db
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `link`
--

DROP TABLE IF EXISTS `link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键，无业务含义',
  `name` varchar(50) NOT NULL COMMENT '菜单的名字',
  `code` varchar(64) NOT NULL COMMENT '菜单的编码，默认由系统自动生成，也可以人工填写，用作jquery的address插件做菜单跳转的锚点',
  `link` varchar(200) DEFAULT NULL COMMENT '链接，多个链接以英文"|"隔开',
  `project_name` varchar(50) DEFAULT NULL COMMENT '所属web的名字',
  `order_rank` int(11) NOT NULL DEFAULT '100' COMMENT '菜单的显示顺序',
  `is_share` tinyint(4) DEFAULT '0' COMMENT '是否为多系统共享连接。0：不共享，1：共享',
  `need_data_check` tinyint(4) DEFAULT '0' COMMENT '是否需要数据权限验证。0：不需要，1：需要',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '链接的类型。0：功能链接；1：菜单',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单id，功能链接必须要有父菜单ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最近一次更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '对该链接的简单描述',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态。-1、已作废；0、正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2162 DEFAULT CHARSET=utf8 COMMENT='链接信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link`
--

LOCK TABLES `link` WRITE;
/*!40000 ALTER TABLE `link` DISABLE KEYS */;
INSERT INTO `link` VALUES (1,'系统管理','M00001','','web-admin,web-company,',40,0,0,1,NULL,'2017-03-01 14:50:18','2017-03-31 17:57:56','',0),(2,'菜单管理','M00002','/sys/menu/list.do','web-admin,',1,0,0,1,1,'2017-03-01 14:49:02','2017-05-04 10:31:57','',0),(3,'角色管理','M00003','/sys/role/list.do','web-admin,web-company,',50,0,0,1,1,'2017-03-01 14:51:24','2017-05-04 10:30:23','',0),(4,'操作员管理','M00004','/sys/worker/list.do','web-admin,web-company,',40,0,0,1,1,'2017-03-01 14:52:20','2017-05-04 10:30:31','',0),(5,'demo','M00005','','web-admin,',1,0,0,1,1,'2017-03-01 14:53:05','2017-05-04 10:32:16','',0),(6,'demo菜单','M583542','/demo/user/list.do','web-company,',30,0,0,1,5,'2017-03-02 17:48:59','2017-05-04 10:30:44','',0);
/*!40000 ALTER TABLE `link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键，无业务含义',
  `ac_id` int(11) DEFAULT NULL COMMENT '代理公司id',
  `name` varchar(50) NOT NULL COMMENT '角色的名字',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最近一次更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '对该角色的简单描述',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态。-1、已作废；0、正常',
  `show_2_user` tinyint(4) DEFAULT '0' COMMENT '是否可以用于用户的类型标识。0：不可以；1：可以',
  `project_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2303 DEFAULT CHARSET=utf8 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,0,'超级管理员','2017-04-10 09:43:13','2017-04-10 09:43:13','系统内置勿删',0,0,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ac_id` int(11) DEFAULT NULL,
  `worker_name` varchar(60) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `depart_name` varchar(60) DEFAULT NULL COMMENT '所属机构名称',
  `depart_id` int(11) DEFAULT NULL COMMENT '所属机构id，agent_depart',
  `create_time` datetime DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `login_name` varchar(32) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0:正常; -1:冻结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2106 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (1,0,'超级管理员',1,NULL,NULL,'2017-04-10 09:43:13','00d763690d161fdc01fe67edc8429afc','admin','13838384380',NULL,0);
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_r2_link`
--

DROP TABLE IF EXISTS `role_r2_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_r2_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键，无业务含义',
  `ac_id` int(11) DEFAULT NULL COMMENT '代理公司id',
  `role_id` int(11) NOT NULL,
  `link_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_link_ind` (`role_id`,`link_id`),
  KEY `link_ind` (`link_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6752 DEFAULT CHARSET=utf8 COMMENT='角色和链接的关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_r2_link`
--

LOCK TABLES `role_r2_link` WRITE;
/*!40000 ALTER TABLE `role_r2_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_r2_link` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-25 23:38:42
