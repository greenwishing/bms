
# 文章添加封面图片地址及访问权限
alter table article add column cover varchar(500);
alter table article add column access varchar(32);
update article set access = 'PRIVATE' where access is null;