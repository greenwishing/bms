
# 反馈
drop table if exists `feedback`;
create table `feedback` (
  id int auto_increment primary key,
  guid varchar(64),
  creation_time datetime,
  type varchar(64),
  content varchar(1000),
  image varchar(255),
  user_id int,
  parent_id int
);
ALTER TABLE `feedback`
  ADD UNIQUE INDEX `idx_guid` (`guid`),
  ADD INDEX `idx_parent_id` (`parent_id`),
  ADD INDEX `idx_user_id` (`user_id`);