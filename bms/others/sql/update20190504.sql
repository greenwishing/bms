
# 活动
drop table if exists `activity`;
create table `activity` (
  id int auto_increment primary key,
  guid varchar(64),
  creation_time datetime,
  user_id int,
  cover varchar(255),
  name varchar(255),
  remark text,
  date_from date,
  date_to date,
  done bit default 0,
  done_time datetime
);
ALTER TABLE `activity`
  ADD UNIQUE INDEX `idx_guid` (`guid`),
  ADD INDEX `idx_user_id` (`user_id`);


# 活动计划
drop table if exists `activity_plan`;
create table `activity_plan` (
  id int auto_increment primary key,
  guid varchar(64),
  creation_time datetime,
  activity_id int,
  name varchar(255),
  remark text,
  date_from date,
  date_to date,
  done bit default 0,
  done_time datetime,
  `order` datetime
);
ALTER TABLE `activity_plan`
  ADD UNIQUE INDEX `idx_guid` (`guid`),
  ADD INDEX `idx_activity_id` (`activity_id`);


# 活动计划预算
drop table if exists `activity_plan_budget`;
create table `activity_plan_budget` (
  id int auto_increment primary key,
  guid varchar(64),
  creation_time datetime,
  plan_id int,
  name varchar(255),
  remark text,
  amount decimal(12,2),
  actual_amount decimal(12,2)
);
ALTER TABLE `activity_plan_budget`
  ADD UNIQUE INDEX `idx_guid` (`guid`),
  ADD INDEX `idx_plan_id` (`plan_id`);