
drop table if exists billing_account;
create table billing_account (
  id int auto_increment primary key,
  guid varchar(255),
  creation_time datetime,
  user_id int,
  type varchar(255),
  name varchar(255),
  balance decimal(8,2)
);