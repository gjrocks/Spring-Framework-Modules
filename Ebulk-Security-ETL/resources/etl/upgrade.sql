ALTER TABLE `connection_config`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);

ALTER TABLE `crb_applications`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);

ALTER TABLE `crb_applications_archived`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);

ALTER TABLE `crb_application_names`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);

ALTER TABLE `organisations`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);

ALTER TABLE `disclosure_data`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);
ALTER TABLE `users`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);
ALTER TABLE `invite_requests`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);

ALTER TABLE `crb_application_addresses`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);
ALTER TABLE crb_applications
MODIFY
ca_disclosure_result VARCHAR(50);

ALTER TABLE crb_applications_archived
MODIFY
ca_disclosure_result VARCHAR(50);

ALTER TABLE `ebulk_messages`
ADD COLUMN
(
  ebulk_batch_lot TINYINT DEFAULT 0
);

ALTER TABLE users
MODIFY
u_title VARCHAR(50);