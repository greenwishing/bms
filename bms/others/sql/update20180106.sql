
# 开放平台用户
drop table if exists `open_user`;
create table `open_user` (
  id int auto_increment primary key,
  guid varchar(64),
  creation_time datetime,
  openid varchar(64),
  user_id int,
  nickname varchar(64),
  avatar text,
  update_time datetime,
  `ext_data` text
);
ALTER TABLE `open_user`
  ADD UNIQUE INDEX `idx_guid` (`guid`),
  ADD UNIQUE INDEX `idx_openid` (`openid`),
  ADD INDEX `idx_user_id` (`user_id`);