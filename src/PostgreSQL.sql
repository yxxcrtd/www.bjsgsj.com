-- 发布系统的 PostgreSQL 数据库脚本
-- By Yang XinXin
-- @2011-07-07


-- 1，创建：登录角色
-- DROP ROLE "bjsgsj";
CREATE ROLE "bjsgsj" LOGIN ENCRYPTED PASSWORD 'md56dc992ed8bd8b7be4c0a24003893b3aa'	-- 密码：bjsgsj
   VALID UNTIL 'infinity';


-- 2，创建：数据库: "bjsgsj"
-- For Windows:
-- DROP DATABASE "bjsgsj";
CREATE DATABASE "bjsgsj"
  WITH OWNER = "bjsgsj"
       ENCODING = 'UTF8'
       LC_COLLATE = 'C'
       LC_CTYPE = 'C'
       CONNECTION LIMIT = -1;

-- For Linux:
-- DROP DATABASE "bjsgsj";
CREATE DATABASE "bjsgsj"
  WITH OWNER = "bjsgsj"
       ENCODING = 'UTF8'
       LC_COLLATE = 'zh_CN.UTF-8'
       LC_CTYPE = 'zh_CN.UTF-8'
       CONNECTION LIMIT = -1;


-- 3，创建：Hibernate序列（PostgreSQL 默认不支持 hibernate 自增长，会报错：hibernate_sequence 不存在。）
-- DROP SEQUENCE hibernate_sequence;
CREATE SEQUENCE hibernate_sequence
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1;

-- 更改所有者
ALTER TABLE hibernate_sequence OWNER TO "bjsgsj";


--------------------------------------插入数据--------------------------------------
-- 1, 用户登录
INSERT INTO "T_User" ("Username", "Password") 
VALUES ('7b7bc2512ee1fedcd76bdc68926d4f7b', '4af3053d169782730ecc4ed55cda27b8');	-- Administrator,BJSGSJ.COM

