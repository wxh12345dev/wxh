/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.20 : Database - wxh
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wxh` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wxh`;

/*Table structure for table `dairy` */

DROP TABLE IF EXISTS `dairy`;

CREATE TABLE `dairy` (
  `ID` varchar(32) NOT NULL,
  `TITLE` varchar(500) DEFAULT NULL,
  `CONTENT` varchar(4000) DEFAULT NULL,
  `URL` varchar(1000) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dairy` */

insert  into `dairy`(`ID`,`TITLE`,`CONTENT`,`URL`,`CREATED`) values ('1','今天天气好晴朗！','厦门的天气可真不错啊！','http://qiniu.wucunhua.com/8fbd448f70ae461ea5e04280a789883a_2.jpg',NULL),('2','生活日记','生活美好，好好珍惜！','http://qiniu.wucunhua.com/a92c23462c14463395fa2a546f30d368_1.jpg;http://qiniu.wucunhua.com/d433d1b654e7428bb53f205bedc5b4d2_images.jpg',NULL);

/*Table structure for table `todo` */

DROP TABLE IF EXISTS `todo`;

CREATE TABLE `todo` (
  `ID` varchar(32) NOT NULL,
  `CONTENT` varchar(4000) NOT NULL,
  `FINISH` int NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `todo` */

insert  into `todo`(`ID`,`CONTENT`,`FINISH`,`CREATED`) values ('111','今天晚上吃西瓜！',0,'2020-09-28 16:05:50'),('222','明早记得带雨伞！',1,'2020-09-28 16:06:08');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
