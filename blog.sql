# Host: localhost  (Version 5.7.15-log)
# Date: 2018-08-15 22:04:42
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "article_info"
#

DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `content` text NOT NULL,
  `content_text` varchar(200) NOT NULL,
  `cover` varchar(100) NOT NULL,
  `view_count` int(10) NOT NULL,
  `update_date` datetime NOT NULL,
  `status` int(10) NOT NULL,
  `type_id` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

#
# Data for table "article_info"
#


#
# Structure for table "type_info"
#

DROP TABLE IF EXISTS `type_info`;
CREATE TABLE `type_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

#
# Data for table "type_info"
#

INSERT INTO `type_info` VALUES (20,'电影预告',1),(21,'新闻资讯',2);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'admin','111');
