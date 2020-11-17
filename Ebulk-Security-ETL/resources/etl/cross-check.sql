SELECT COUNT(*) FROM  crb_applications
WHERE ebulk_batch_lot=0;

SELECT COUNT(*) FROM  crb_applications_archived
WHERE ebulk_batch_lot=0;

SELECT COUNT(*) FROM  organisations
WHERE ebulk_batch_lot=0;

SELECT COUNT(*) FROM  users 
WHERE ebulk_batch_lot=0;

SELECT COUNT(*) FROM  connection_config
WHERE ebulk_batch_lot=0;

SELECT COUNT(*) FROM  disclosure_data
WHERE ebulk_batch_lot=0;

SELECT COUNT(*) FROM  invite_requests
WHERE ebulk_batch_lot=0;

SELECT COUNT(*) FROM  ebulk_messages
WHERE ebulk_batch_lot=0;

SELECT COUNT(*) FROM  crb_application_names
WHERE ebulk_batch_lot=0;
