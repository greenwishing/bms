
ALTER TABLE `article`
ADD UNIQUE INDEX `idx_guid` (`guid`),
ADD INDEX `idx_user_id` (`user_id`);

ALTER TABLE `article_category`
ADD UNIQUE INDEX `idx_guid` (`guid`),
ADD INDEX `idx_user_id` (`user_id`);

ALTER TABLE `billing`
ADD UNIQUE INDEX `idx_guid` (`guid`),
ADD INDEX `idx_query` (`user_id`, `type`, `category_id`, `subcategory_id`, `name`);

ALTER TABLE `billing_account`
ADD UNIQUE INDEX `idx_guid` (`guid`),
ADD INDEX `idx_user_id` (`user_id`);

ALTER TABLE `billing_category`
ADD UNIQUE INDEX `idx_guid` (`guid`),
ADD INDEX `idx_user_id` (`user_id`);

ALTER TABLE `billing_subcategory`
ADD UNIQUE INDEX `idx_guid` (`guid`);

ALTER TABLE `metro_line`
ADD UNIQUE INDEX `idx_guid` (`guid`);

ALTER TABLE `station`
ADD UNIQUE INDEX `idx_guid` (`guid`);

ALTER TABLE `metro_line_station`
ADD UNIQUE INDEX `idx_guid` (`guid`),
ADD INDEX `idx_line_id` (`metro_line_id`),
ADD INDEX `idx_station_id` (`station_id`);

ALTER TABLE `user`
ADD UNIQUE INDEX `idx_guid` (`guid`),
ADD INDEX `idx_account` (`account`);

ALTER TABLE `user_app`
ADD UNIQUE INDEX `idx_guid` (`guid`),
ADD INDEX `idx_user_id` (`user_id`);