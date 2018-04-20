-- 创建后台管理用户信息表
CREATE TABLE TB_USERS(
	USER_ID NUMBER PRIMARY KEY,
	USER_LOGNAME VARCHAR2(50) NOT NULL,
	USER_PWD VARCHAR2(50) NOT NULL,
	USER_REALNAME VARCHAR2(50) NOT NULL,
	USER_EMAIL VARCHAR2(50) NOT NULL,
	USER_ROLE NUMBER NOT NULL,
	USER_STATE NUMBER NOT NULL
);
-- 创建用户信息表所使用的序列
CREATE SEQUENCE SEQ_ITOFFER_USERS MINVALUE 0 START WITH 0 INCREMENT BY 1;
-- 用户信息表基础数据
INSERT INTO tb_users(user_id, user_logname, user_pwd, user_realname, user_email, user_role, user_state) VALUES(SEQ_ITOFFER_USERS.NEXTVAL,'admin','123456','青软实训','admin@test.com',1,1);
INSERT INTO tb_users(user_id, user_logname, user_pwd, user_realname, user_email, user_role, user_state) VALUES(SEQ_ITOFFER_USERS.NEXTVAL,'fengjj','123456','冯娟娟','fengjj@test.com',1,1);
INSERT INTO tb_users(user_id, user_logname, user_pwd, user_realname, user_email, user_role, user_state) VALUES(SEQ_ITOFFER_USERS.NEXTVAL,'test','123456','test','test@test.com',1,1);
COMMIT;