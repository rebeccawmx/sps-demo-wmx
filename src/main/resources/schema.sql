DROP TABLE IF EXISTS `ORGANIZATION`;

CREATE TABLE IF NOT EXISTS `ORGANIZATION` (
  id   BIGINT AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `ROLE`;

CREATE TABLE IF NOT EXISTS `ROLE` (
  code VARCHAR(50),
  PRIMARY KEY (code)
);

DROP TABLE IF EXISTS `USER`;

CREATE TABLE IF NOT EXISTS `USER` (
  id       BIGINT AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(60),
  org_id   BIGINT,
  FOREIGN KEY (org_id) REFERENCES `ORGANIZATION` (id),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `USER_ROLE`;

CREATE TABLE IF NOT EXISTS `USER_ROLE` (
  user_id   BIGINT      NOT NULL,
  role_code VARCHAR(50) NOT NULL
);

INSERT INTO `ORGANIZATION` (id, name) VALUES (1, 'Microsoft');
INSERT INTO `ORGANIZATION` (id, name) VALUES (2, 'Amazon');
INSERT INTO `ORGANIZATION` (id, name) VALUES (3, 'Smartisan');
INSERT INTO `ORGANIZATION` (id, name) VALUES (4, '泛微');


INSERT INTO `USER` (id, username, password, org_id) VALUES (1, 'yinguowei', '111111', 1);
INSERT INTO `USER` (id, username, password, org_id) VALUES (2, 'chengyu', '222222', 2);
INSERT INTO `USER` (id, username, password, org_id) VALUES (3, 'lichenjing', '333333', 3);
INSERT INTO `USER` (id, username, password, org_id) VALUES (4, 'wumengxuan', '444444', 1);
INSERT INTO `USER` (id, username, password, org_id) VALUES (5, 'liurui', '55555', 4);
INSERT INTO `USER` (id, username, password, org_id) VALUES (6, 'chenjing', '111111', 1);
INSERT INTO `USER` (id, username, password, org_id) VALUES (7, 'xiongkun', '222222', 2);
INSERT INTO `USER` (id, username, password, org_id) VALUES (8, 'xingxiaoxiao', '333333', 3);
INSERT INTO `USER` (id, username, password, org_id) VALUES (9, 'xuqiuping', '444444', 1);
INSERT INTO `USER` (id, username, password, org_id) VALUES (10, 'yeyeyeye', '55555', 4);
INSERT INTO `USER` (id, username, password, org_id) VALUES (11, 'zhanghongbing', 'sixsixsix', 4);

INSERT INTO `ROLE` (code) VALUES ('ADMIN');
INSERT INTO `ROLE` (code) VALUES ('USER');
INSERT INTO `ROLE` (code) VALUES ('GUEST');

INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (1, 'ADMIN');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (1, 'USER');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (2, 'USER');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (3, 'ADMIN');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (3, 'USER');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (4, 'ADMIN');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (4, 'USER');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (5, 'GUEST');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (6, 'ADMIN');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (7, 'USER');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (8, 'USER');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (9, 'ADMIN');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (10, 'USER');
INSERT INTO `USER_ROLE` (user_id, role_code) VALUES (11, 'ADMIN');