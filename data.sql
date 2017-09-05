/** Oauth - populate the oauth_client_details table */
INSERT INTO `oauth_client_details` (`client_id`, `client_secret`, `scope`, `authorized_grant_types`, `access_token_validity`, `additional_information`)
VALUES
('c1', 's1', 'read', 'authorization_code,password,refresh_token,implicit', '900', '{}')
ON DUPLICATE key UPDATE
client_secret = VALUES(`client_secret`),
scope = VALUES(`scope`),
authorized_grant_types = VALUES(`authorized_grant_types`),
access_token_validity = VALUES(`access_token_validity`),
additional_information = VALUES(`additional_information`);

INSERT INTO `oauth_example`.`account` ( `password`, `username`) VALUES ('guest123', 'guest')
ON DUPLICATE key UPDATE
password = VALUES(`password`),
username = VALUES(`username`);
