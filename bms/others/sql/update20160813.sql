
drop table if exists user_app;
create table user_app (
  id int auto_increment primary key,
  guid varchar(255),
  creation_time datetime,
  user_id int,
  app_id varchar(255)
);

insert into user_app (guid, creation_time, user_id, app_id) VALUES ('userapp_1_test888888', now(), 1, 'test888888');