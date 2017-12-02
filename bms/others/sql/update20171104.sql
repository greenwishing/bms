
# 将 billing 的日期改为时间格式
ALTER TABLE `billing` MODIFY COLUMN `occurred_time` datetime AFTER `amount`;