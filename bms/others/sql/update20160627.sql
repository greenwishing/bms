
alter table billing add column status varchar(255);
alter table billing add column settle_time datetime;

update billing set status='NORMAL' where type in ('INCOME', 'EXPEND', 'BALANCE');
update billing set status='RECEIVABLE' where type='ACCOUNT_RECEIVABLE';
update billing set status='PAYABLE' where type='ACCOUNT_PAYABLE';