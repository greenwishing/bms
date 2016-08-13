/*
Date: 2015-11-07 10:53:24
*/

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) NOT NULL default '',
  `token` blob,
  `authentication_id` varchar(255) default NULL,
  `user_name` varchar(255) default NULL,
  `client_id` varchar(255) default NULL,
  `authentication` blob,
  `refresh_token` varchar(255) default NULL,
  PRIMARY KEY  (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL default '',
  `client_secret` varchar(255) default NULL,
  `resource_ids` varchar(255) default NULL,
  `scope` varchar(255) default NULL,
  `authorized_grant_types` varchar(255) default NULL,
  `web_server_redirect_uri` varchar(255) default NULL,
  `authorities` varchar(255) default NULL,
  `access_token_validity` int(11) default NULL,
  `refresh_token_validity` int(11) default NULL,
  `additional_information` varchar(5120) default NULL,
  PRIMARY KEY  (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `oauth_client_details` VALUES ('test888888', 'd25f622a6f0c4d34a27ac2864f9bb091', 'oauth_api', 'read,white,trust', 'password,authorization_code,refresh_token,implicit,client_credentials', '/oauth/api/redirect', 'ROLE_API_BASE', '7200', '7200', '{\"additional_information\":\"test info\"}');

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(255) NOT NULL default '',
  `authentication` blob,
  PRIMARY KEY  (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) NOT NULL default '',
  `token` blob,
  `authentication` blob,
  PRIMARY KEY  (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

