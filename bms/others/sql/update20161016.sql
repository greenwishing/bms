
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

insert into billing_account (id, guid, creation_time, user_id, type, name, balance) VALUES
  (1, 'billingaccount06b55240167e676001', now(), 1, 'CASH', '现金', 0),
  (2, 'billingaccount06b55240167e676002', now(), 1, 'CREDIT_CARD', '招商银行 4227', 0),
  (3, 'billingaccount06b55240167e676003', now(), 1, 'CREDIT_CARD', '中信银行 6349', 0),
  (4, 'billingaccount06b55240167e676004', now(), 1, 'DEPOSIT_CARD', '工商银行 4893', 0),
  (5, 'billingaccount06b55240167e676005', now(), 1, 'DEPOSIT_CARD', '中国银行 7920', 0),
  (6, 'billingaccount06b55240167e676006', now(), 1, 'DEPOSIT_CARD', '中信银行 6011', 0),
  (7, 'billingaccount06b55240167e676007', now(), 1, 'LOAN', '建设银行 房贷卡', 0),
  (8, 'billingaccount06b55240167e676008', now(), 1, 'VIRTUAL', '支付宝', 0),
  (9, 'billingaccount06b55240167e676009', now(), 1, 'VIRTUAL', '微信支付', 0)
;

/* 借出/代付 */
update billing set type='LOAN' where type='ACCOUNT_RECEIVABLE';
/* 收款 */
INSERT INTO billing (
  `guid`, `creation_time`, `name`, `description`, `type`, `amount`, `occurred_time`,
  `occurred_user_id`, `operator_id`, `category_id`, `subcategory_id`, `status`, `settle_time`
) SELECT
    replace(uuid(), '-', ''),
    now(),
    concat('收款 - ', name),
    '',
    'RECEIVE',
    amount,
    date_format(settle_time, '%Y-%m-%d'),
    occurred_user_id,
    operator_id,
    category_id,
    subcategory_id,
    'NORMAL',
    settle_time
  FROM billing
  WHERE type = 'ACCOUNT_RECEIVABLE' AND status = 'RECEIVED';

/* 借入 */
update billing set type='BORROW' where type = 'ACCOUNT_PAYABLE';
/* 还款 */
INSERT INTO billing (
  `guid`, `creation_time`, `name`, `description`, `type`, `amount`, `occurred_time`,
  `occurred_user_id`, `operator_id`, `category_id`, `subcategory_id`, `status`, `settle_time`
) SELECT
    replace(uuid(), '-', ''),
    now(),
    concat('还款 - ', name),
    '',
    'PAYBACK',
    amount,
    date_format(settle_time, '%Y-%m-%d'),
    occurred_user_id,
    operator_id,
    category_id,
    subcategory_id,
    'NORMAL',
    settle_time
  FROM billing
  WHERE type = 'ACCOUNT_PAYABLE' AND status = 'PAYED';

update billing_category set type='LOAN' where type='ACCOUNT_RECEIVABLE';
update billing_category set type='BORROW' where type='ACCOUNT_PAYABLE';

update billing set amount=-amount where amount<0;

alter table billing add column src_account_id int;
alter table billing add column target_account_id int;

delete from billing where type='BALANCE';
# TODO update billing accounts