
drop table if exists todo;
create table todo (
  id int auto_increment primary key,
  guid varchar(255),
  creation_time datetime,
  user_id int,
  content varchar(1000),
  done bit default 0,
  done_time datetime,
  status varchar(32)
);


drop table if exists moment_type;
create table moment_type (
  id int auto_increment primary key,
  guid varchar(255),
  creation_time datetime,
  user_id int,
  name varchar(255),
  goal_type varchar(255),
  goal varchar(255)
);


drop table if exists moment;
create table moment (
  id int auto_increment primary key,
  guid varchar(255),
  creation_time datetime,
  user_id int,
  moment_type_id int,
  date date,
  start_time time,
  end_time time,
  description varchar(500)
);