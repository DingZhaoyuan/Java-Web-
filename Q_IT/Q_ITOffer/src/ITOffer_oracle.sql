--oracle版本
-- 创建求职者信息表
CREATE TABLE TB_APPLICANT(
	APPLICANT_ID NUMBER PRIMARY KEY,
	APPLICANT_EMAIL VARCHAR2(50) NOT NULL,
	APPLICANT_PWD VARCHAR2(50) NOT NULL,
	APPLICANT_REGISTDATE TIMESTAMP 
);
-- 创建求职者信息表所使用的序列
CREATE SEQUENCE SEQ_ITOFFER_APPLICANT MINVALUE 0 START WITH 0 INCREMENT BY 1;
-- 求职者信息表基础数据
INSERT INTO tb_applicant(applicant_id, applicant_email, applicant_pwd, applicant_registdate)VALUES(SEQ_ITOFFER_APPLICANT.NEXTVAL,'qst@itoffer.cn','123456',to_timestamp('2011-01-01 13:14:20','yyyy-MM-dd HH24:mi:ss'));
COMMIT;

-- 创建简历基本信息表
CREATE TABLE TB_RESUME_BASICINFO(
	BASICINFO_ID NUMBER PRIMARY KEY,
	APPLICANT_ID NUMBER NOT NULL,
	REALNAME VARCHAR2(50) NOT NULL,
	GENDER VARCHAR2(50) NOT NULL,
	BIRTHDAY TIMESTAMP,
	CURRENT_LOC VARCHAR2(255),
	RESIDENT_LOC VARCHAR2(255),
	TELEPHONE VARCHAR2(50),
	EMAIL VARCHAR2(50),
	JOB_INTENSION VARCHAR2(50),
	JOB_EXPERIENCE VARCHAR2(255),
	HEAD_SHOT VARCHAR2(255),
	CONSTRAINT TB_RESUME_BASICINFO_FK1 FOREIGN KEY(APPLICANT_ID) REFERENCES TB_APPLICANT(APPLICANT_ID),
	CONSTRAINT TB_RESUME_BASICINFO_CK1 CHECK (GENDER IN ('男','女'))
);
-- 简历基本信息表所使用的序列
CREATE SEQUENCE SEQ_ITOFFER_RESUMEBASICINFO MINVALUE 0 START WITH 0 INCREMENT BY 1;
COMMIT;
-- 简历基本信息测试数据
INSERT INTO tb_resume_basicinfo(basicinfo_id, applicant_id, realname, gender, birthday, current_loc, resident_loc, telephone, email, job_intension, job_experience, head_shot) VALUES(SEQ_ITOFFER_RESUMEBASICINFO.NEXTVAL,1,'张三','男',to_timestamp('1993-11-05','yyyy-mm-dd'),'山东省青岛市高新区','山东省青岛市','13166666666','qst@itoffer.cn','Java软件开发','刚刚参加工作'，'QST.jpg');
COMMIT;

-- 企业信息表
CREATE TABLE TB_COMPANY(
	COMPANY_ID NUMBER PRIMARY KEY,
	COMPANY_NAME VARCHAR2(50),
	COMPANY_AREA VARCHAR2(50),
	COMPANY_SIZE VARCHAR2(50),
	COMPANY_TYPE VARCHAR2(50),
	COMPANY_BRIEF VARCHAR2(2000),
	COMPANY_STATE NUMBER,
	COMPANY_SORT NUMBER, 
	COMPANY_VIEWNUM LONG,
	COMPANY_PIC VARCHAR2(255),
	CONSTRAINT "TB_COMPANY_CK1" CHECK (COMPANY_STATE IN (1,2,3))
);
-- 企业信息表所使用的序列
CREATE SEQUENCE SEQ_ITOFFER_COMPANY MINVALUE 0 START WITH 0 INCREMENT BY 1;
-- 企业信息表基础数据
INSERT INTO tb_company(company_id,company_name,company_area,company_size,company_type,company_brief,company_state,company_sort,company_viewnum,company_pic) VALUES(SEQ_ITOFFER_COMPANY.NEXTVAL,'凌志软件股份有限公司','苏州市','1000人以上','股份制企业','凌志软件股份有限公司成立于2003年1月，是一家致力于为各行业提供软件产品研发，软件外包开发及IT系统集成服务。业务范围包括证券，银行，保险，电子商务，物流等。金融行业产品研发及软件外包服务的专业性及稳定性，已成为企业的核心竞争力。公司在日本东京、上海、北京、深圳等地均设有分支机构。凌志软件经过10年多的发展，得到现有海内外客户高度认可，并在中国和日本形成了一定的品牌知名度，海外业务以日本市场为核心，已成为国际知名企业的核心供应商，在2011年软件出口企业排行榜中名列第10名，并获得2011-2012年国家规划布局内重点软件企业称号。凌志软件在稳步扩大高端软件外包业务的同时，自主研发国内高端金融产品，现已申请多项发明专利并开发了多款拥有自主知识产权的金融软件产品，投入商业应用并得到客户的高度评价，逐步在国内市场上崭露头角。',1,1,1389,'635170123249913750.jpg');
INSERT INTO tb_company(company_id,company_name,company_area,company_size,company_type,company_brief,company_state,company_sort,company_viewnum,company_pic) VALUES(SEQ_ITOFFER_COMPANY.NEXTVAL,'苏州大宇宙信息创造有限公司','苏州市','100-200人','外资企业','苏州大宇宙信息创造有限公司成立于2008年10月，是大宇宙信息创造（中国）有限公司全资子公司，注册资金为1600万元。公司位于风景优美的中国新加坡合作苏州工业园区独墅湖高教区，拥有自己的办公及研发大楼，是园区重点引进的软件服务外包企业。公司是一家专业从事国际和国内企事业信息化解决方案、软件外包的高科技企业，为国内外企业提供一流的软件开发、系统集成及维护、客户支持等综合的信息服务。公司拥有一支高素质的管理与开发团队，具有良好的外语能力和丰富的软件设计开发经验，同时具备与国内外客户的良好商务沟通能力。公司成立至今，保持着稳健发展的势头，事业日益发展和壮大，目前已与国内外多家企业建立了长期稳定的客户关系。公司于2009年7月份顺利通过ISO27001信息安全管理的国际认证，2010年6月顺利通过CMMI3级认证。公司具备完善的管理、教育培训和薪酬福利体系以及健全的规章制度，为员工的工作和学习提供了广阔、自由的发展空间。',1,2,577,'635508802169230812.jpg');
INSERT INTO tb_company(company_id,company_name,company_area,company_size,company_type,company_brief,company_state,company_sort,company_viewnum,company_pic) VALUES(SEQ_ITOFFER_COMPANY.NEXTVAL,'北京日立华胜信息系统有限公司','东城区','200-500人','合资企业','北京日立华胜信息系统有限公司（简称BHH）是世界五百强之一的HITACHI日立集团和信息产业部电子六所共同投资设立的高新技术企业。公司主要从事对日软件开发,自成立以来，我们承接了日本各大银行?证券交易所相关系统、日本新干线铁路座位预约系统、面向日本政府机关的财务会计系统/税金管理系统/居民信息管理系统、纳税系统、生产管理系统、销售管理系统、日本各大汽车厂商的ECU软件、信息终端设备软件等各种大型软件开发项目。业务领域涵盖：金融、产业/流通、公共政府、ATM以及嵌入式五大领域。从1996年起，公司便已经开始从事汽车引擎控制、变速器控制、自动巡航控制等领域的嵌入式软件开发，积累了丰富的嵌入式软件的开发经验。公司十分注重对员工的外语及业务技能培训，提供多次出国工作机会和充分的发展空间；公司员工均享有良好的薪资和完备的福利保险待遇（“五险一金”和补充医疗/意外伤害保险，以及多项补贴）。诚挚邀请有志于从事对日软件开发、德才兼备的毕业生加盟，开辟属于自己的崭新生活。欢迎各位有识之士的加盟。',1,3,1183,'635086129655240312.jpg');
COMMIT;


-- 招聘职位信息表
CREATE TABLE TB_JOB(
	JOB_ID NUMBER PRIMARY KEY,
	COMPANY_ID NUMBER NOT NULL,
	JOB_NAME VARCHAR2(100),
	JOB_HIRINGNUM NUMBER,
	JOB_SALARY VARCHAR2(20),
	JOB_AREA VARCHAR2(255),
	JOB_DESC VARCHAR2(255),
	JOB_ENDTIME TIMESTAMP,
	JOB_STATE NUMBER,
	CONSTRAINT TB_JOB_CHK1 CHECK (JOB_STATE IN (1,2,3)),
	CONSTRAINT TB_JOB_TB_COMPANY_FK1 FOREIGN KEY (COMPANY_ID) REFERENCES TB_COMPANY (COMPANY_ID)
);
-- 招聘职位信息表所需序列
CREATE SEQUENCE SEQ_ITOFFER_JOB MINVALUE 0 START WITH 0 INCREMENT BY 1;
-- 招聘职位信息表基础数据
INSERT INTO tb_job(job_id, company_id, job_name, job_hiringnum, job_salary, job_area, job_desc, job_endtime, job_state) VALUES (SEQ_ITOFFER_JOB.NEXTVAL,1,'对日软件开发工程师（提供岗前培训）',100,'2500~4000元/月','吴中区','担任设计书的制作、程序开发、测试实施等工作。', to_timestamp('2016-03-05','yyyy-mm-dd'),1);
INSERT INTO tb_job(job_id, company_id, job_name, job_hiringnum, job_salary, job_area, job_desc, job_endtime, job_state) VALUES (SEQ_ITOFFER_JOB.NEXTVAL,2,'Java软件开发工程师（提供岗前培训）',20,'2500~4000元/月','工业园区','针对客户现场软件问题提供技术支持，包括在研发实验室重现客户软件问题，分析代码问题原因，提供解决方案，并测试更新的代码符合客户要求', to_timestamp('2016-03-05','yyyy-mm-dd'),1);
INSERT INTO tb_job(job_id, company_id, job_name, job_hiringnum, job_salary, job_area, job_desc, job_endtime, job_state) VALUES (SEQ_ITOFFER_JOB.NEXTVAL,3,'对日软件开发（提供岗前培训）',40,'4万-4.5万/年','历城区','J2EE开发 TOMCAT/JBOSS等主流应用服务器 Webservice、SOCKET、SNMP等标准接口和协议,Struts2、Spring、Hibernate等常用框架 Linux操作系统及oracle。', to_timestamp('2016-03-05','yyyy-mm-dd'),1);
COMMIT;


-- 职位申请信息表
CREATE TABLE TB_JOBAPPLY(
	APPLY_ID NUMBER PRIMARY KEY,
	JOB_ID NUMBER NOT NULL,
	APPLICANT_ID NUMBER NOT NULL,
	APPLY_DATE TIMESTAMP,
	APPLY_STATE NUMBER,
	CONSTRAINT TB_JOBAPPLY_TB_JOB_FK1 FOREIGN KEY (JOB_ID) REFERENCES TB_JOB (JOB_ID),
  CONSTRAINT TB_JOBAPPLY_TB_APLLICANT_FK1 FOREIGN KEY (APPLICANT_ID) REFERENCES TB_APPLICANT (APPLICANT_ID)
);
-- 职位申请信息表序列
CREATE SEQUENCE SEQ_ITOFFER_JOBAPPLY MINVALUE 0 START WITH 0 INCREMENT BY 1;
-- 职位申请信息表基础数据
INSERT INTO tb_jobapply(apply_id, job_id, applicant_id, apply_date, apply_state) VALUES (SEQ_ITOFFER_JOBAPPLY.NEXTVAL,1,1,to_timestamp('2015-03-05','yyyy-mm-dd'),1);
COMMIT;