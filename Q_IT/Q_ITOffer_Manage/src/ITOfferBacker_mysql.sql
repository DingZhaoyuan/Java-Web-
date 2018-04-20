-- mysql版本
-- 创建后台管理用户信息表
-- USER_ROLE (1 系统管理员；2 企业管理员；3 普通用户)
-- USER_STATE(0 禁用；2启用)
CREATE TABLE TB_USERS(
	USER_ID int PRIMARY KEY auto_increment, 
	USER_LOGNAME VARCHAR(50) NOT NULL,
	USER_PWD VARCHAR(50) NOT NULL,
	USER_REALNAME VARCHAR(50) NOT NULL,
	USER_EMAIL VARCHAR(50) NOT NULL,
	USER_ROLE int NOT NULL,-- (1 系统管理员；2 企业管理员；3 普通用户)
	USER_STATE int NOT NULL-- USER_STATE(0 禁用；2启用)
);


-- 用户信息表基础数据
INSERT INTO tb_users(user_logname, user_pwd, user_realname, user_email, user_role, user_state) VALUES('admin','123456','青软实训','admin@test.com',1,1);
INSERT INTO tb_users(user_logname, user_pwd, user_realname, user_email, user_role, user_state) VALUES('test','123456','test','test@test.com',1,1);
COMMIT;