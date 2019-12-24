DROP ALL OBJECTS;

/*-----------------------------------------------*/
DROP TABLE IF EXISTS `USER`;

CREATE TABLE USER
(
	id INT(11) NOT NULL AUTO_INCREMENT COMMENT 'user_id',
	user_name VARCHAR(64) DEFAULT NULL COMMENT 'name',
	email VARCHAR(256) NOT NULL COMMENT 'email',
	user_status CHAR NOT NULL COMMENT 'user state',
	created_at DATETIME NOT NULL COMMENT 'created time',
	updated_at DATETIME NOT NULL COMMENT 'update time',
	created_by VARCHAR(64) NOT NULL COMMENT 'created by user name',
	updated_by VARCHAR(64) NOT NULL COMMENT 'update by user name',
	last_login_time DATETIME COMMENT 'the time when the user login',
	PRIMARY KEY (id)
);
