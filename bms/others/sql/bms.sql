
drop table if exists billing;
create table billing (
  id int,
  guid varchar(255),
  creation_time datetime,
  name varchar(255),
  description varchar(255),
  type varchar(255),
  amount numeric(8,2),
  occurred_time date,
  occurred_user_guid varchar(255),
  operator_guid varchar(255)
);

drop table if exists `user`;
create table `user` (
  id int,
  guid varchar(255),
  creation_time datetime,
  username varchar(255),
  account varchar(255),
  password varchar(255),
  last_login_time datetime,
  status varchar(255)
);

drop table if exists article_category;
create table article_category (
  id int,
  guid varchar(255),
  creation_time datetime,
  name varchar(255),
  owner_id int
);

drop table if exists article;
create table article (
  id int,
  guid varchar(255),
  creation_time datetime,
  title varchar(255),
  content text,
  category_id int,
  author_id int
);