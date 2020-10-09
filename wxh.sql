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

insert  into `dairy`(`ID`,`TITLE`,`CONTENT`,`URL`,`CREATED`) values ('974b986185844fae9dad2e195263ac01','今天是个好日子！','明天就是国庆啦，开心！','http://wxh.wucunhua.com/fadc8aee0d01491a90964ee7e915c83f_3.jpg','2020-09-30 06:43:00');

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

insert  into `todo`(`ID`,`CONTENT`,`FINISH`,`CREATED`) values ('12ebb8d7688e4b2d897a9754bd9772b0','e\'w\'q',1,'2020-09-29 08:42:37'),('222','明早记得带雨伞！明早记得带雨伞！明早记得带雨伞！明早记得带雨伞！明早记得带雨伞！明早记得带雨伞！明早记得带222雨伞！',1,'2020-09-28 16:06:00'),('81a75f0343274b89a49c5ec5ad2133ae','大撒大撒',1,'2020-09-30 02:54:05'),('8590185cc19c4dbf82b06528d0218af0','大撒大撒',0,'2020-09-30 02:54:08'),('8c41c660ea934079b020c7792079852f','ewqewq的撒旦',0,'2020-09-30 01:41:00'),('aa623cc2f1bf4ee0ab264d6b006faea6','为五千',1,'2020-09-30 02:00:15'),('b45e93ea941b422f884ba683094c04e8','大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒',1,'2020-09-29 08:43:02'),('e1c5e2f493904d249c53a07c3b91dc25','大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒',1,'2020-09-29 08:42:00'),('e23a0adfe3ed4979b5e487db1f2e9ddc','大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒大撒',1,'2020-09-29 08:42:00'),('ea9e0df323be4ab38a7d55859459529f','撒旦撒',0,'2020-09-30 02:54:02'),('ed00f9592be64f81a7dd57c8ba32c2bb','大撒大撒',1,'2020-09-29 08:42:33');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
