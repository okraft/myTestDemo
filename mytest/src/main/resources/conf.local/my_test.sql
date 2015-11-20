/*
SQLyog Ultimate v11.31 (64 bit)
MySQL - 5.6.19 : Database - flows-fruit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`flows-fruit` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `flows-fruit`;

/*Table structure for table `test_resource` */

DROP TABLE IF EXISTS `test_resource`;

CREATE TABLE `test_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `url` varchar(128) NOT NULL COMMENT '资源url',
  `model_id` int(11) NOT NULL COMMENT '模块id',
  `cuser` int(11) NOT NULL,
  `ctime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `test_resource` */

insert  into `test_resource`(`id`,`name`,`url`,`model_id`,`cuser`,`ctime`) values (1,'add','/add*',0,0,'2015-07-22 15:36:11'),(2,'delete','/delete*',0,0,'2015-07-22 15:36:44'),(3,'update','/update*',0,0,'2015-07-22 15:37:01'),(4,'query','/query*',0,0,'2015-07-22 15:37:15');

/*Table structure for table `test_role` */

DROP TABLE IF EXISTS `test_role`;

CREATE TABLE `test_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `ctime` datetime NOT NULL,
  `cuser` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `test_role` */

insert  into `test_role`(`id`,`name`,`ctime`,`cuser`) values (1,'admin','2015-07-22 15:33:17',0),(2,'manager','2015-07-22 15:33:35',0),(3,'normal','2015-07-22 15:33:46',0);

/*Table structure for table `test_role_resource` */

DROP TABLE IF EXISTS `test_role_resource`;

CREATE TABLE `test_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rold_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `cuser` int(11) NOT NULL,
  `ctime` datetime NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `test_role_resource` */

insert  into `test_role_resource`(`id`,`rold_id`,`resource_id`,`cuser`,`ctime`) values (1,1,1,0,'2015-07-22 15:38:28'),(2,1,3,0,'2015-07-22 15:38:54'),(3,2,2,0,'2015-07-22 15:39:07'),(4,3,4,0,'2015-07-22 15:41:00');

/*Table structure for table `test_sys_model` */

DROP TABLE IF EXISTS `test_sys_model`;

CREATE TABLE `test_sys_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `cuser` int(11) NOT NULL,
  `ctime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `test_sys_model` */

/*Table structure for table `test_tree` */

DROP TABLE IF EXISTS `test_tree`;

CREATE TABLE `test_tree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(50) NOT NULL COMMENT '名称',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父Id',
  `isleaf` int(11) NOT NULL DEFAULT '0' COMMENT '叶节点标识：1：是；0：不是',
  `sort_no` int(11) NOT NULL DEFAULT '0',
  `url` varchar(256) DEFAULT NULL COMMENT '链接',
  `ctime` date NOT NULL,
  `mtime` date DEFAULT NULL,
  `cuser` int(11) NOT NULL DEFAULT '0',
  `muser` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `test_tree` */

insert  into `test_tree`(`id`,`text`,`pid`,`isleaf`,`sort_no`,`url`,`ctime`,`mtime`,`cuser`,`muser`) values (1,'系统管理',0,0,0,NULL,'2015-07-09','2015-07-09',0,0),(2,'客户管理',0,0,0,NULL,'2015-07-09','2015-07-09',0,0),(3,'销售管理',0,0,0,NULL,'2015-07-09','2015-07-09',0,0),(4,'用户管理',0,0,0,NULL,'2015-07-09','2015-07-09',0,0),(5,'权限管理',0,0,0,NULL,'2015-07-17','2015-07-17',0,0);

/*Table structure for table `test_user` */

DROP TABLE IF EXISTS `test_user`;

CREATE TABLE `test_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(20) NOT NULL,
  `email` varchar(60) NOT NULL,
  `code` varchar(30) DEFAULT NULL COMMENT '登录代码',
  `name` varchar(60) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `ctime` datetime NOT NULL,
  `cuser` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `test_user` */

insert  into `test_user`(`id`,`mobile`,`email`,`code`,`name`,`password`,`ctime`,`cuser`) values (1,'18301352884','okraft@163.com',NULL,'wanggg','fee99328918359fca5360a032a069323','2015-07-22 10:51:11',0),(2,'18888888888','1@163.com',NULL,'zhangsan','fee99328918359fca5360a032a069323','2015-07-22 15:32:11',0),(3,'16666666666','2@163.com',NULL,'lisi','fee99328918359fca5360a032a069323','2015-07-22 15:32:36',0);

/*Table structure for table `test_user_role` */

DROP TABLE IF EXISTS `test_user_role`;

CREATE TABLE `test_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `role_id` int(11) NOT NULL COMMENT '角色Id',
  `cuser` int(11) NOT NULL,
  `ctime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `test_user_role` */

insert  into `test_user_role`(`id`,`user_id`,`role_id`,`cuser`,`ctime`) values (1,1,1,0,'2015-07-22 15:34:19'),(2,1,2,0,'2015-07-22 15:34:28'),(3,1,3,0,'2015-07-22 15:34:38'),(4,2,3,0,'2015-07-22 15:34:53'),(5,3,2,0,'2015-07-22 15:35:05');

/* Function  structure for function  `getChildList` */

/*!50003 DROP FUNCTION IF EXISTS `getChildList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `getChildList`(rootId INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
  DECLARE sTemp VARCHAR (1000) ;
  DECLARE sTempChd VARCHAR (1000) ;
  SET sTemp = '$' ;
  SET sTempChd = cast(rootId as char) ;
  while
    sTempChd is not null do set sTemp = concat(sTemp, ',', sTempChd) ;
    SELECT 
      group_concat(id) INTO sTempChd 
    FROM
      test_tree 
    where FIND_IN_SET(pid, sTempChd) > 0 ;
  end WHILE ;
  RETURN sTemp ;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
