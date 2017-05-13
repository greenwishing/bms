
drop table if exists configuration;
create table configuration (
  id int auto_increment primary key,
  guid varchar(255),
  creation_time datetime,
  user_id int,
  `key` varchar(64),
  `value` text,
  description text,
  UNIQUE KEY `idx_guid` (`guid`) USING BTREE,
  KEY `idx_key` (`key`)
);