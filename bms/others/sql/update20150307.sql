

drop table if exists billing_category;
create table billing_category (
  id int auto_increment primary key,
  guid varchar(255),
  creation_time datetime,
  user_id int,
  type varchar(255),
  name varchar(255)
);

drop table if exists billing_subcategory;
create table billing_subcategory (
  id int auto_increment primary key,
  guid varchar(255),
  creation_time datetime,
  category_id int,
  name varchar(255)
);

drop table if exists billing_template;
create table billing_template (
  id int auto_increment primary key,
  guid varchar(255),
  creation_time datetime,
  user_id int,
  type varchar(255),
  category_id int,
  subcategory_id int,
  name varchar(255),
  amount decimal(8,2)
);

alter table billing add column category_id int;
alter table billing add column subcategory_id int;