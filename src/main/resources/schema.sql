DROP TABLE IF EXISTS `USER`;

CREATE TABLE IF NOT EXISTS `USER` (
  id       BIGINT AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(60),
  PRIMARY KEY (id)
);

INSERT INTO `USER` (id, username, password) VALUES (1, 'yinguowei', '111111');
INSERT INTO `USER` (id, username, password) VALUES (2, 'chengyu', '222222');
INSERT INTO `USER` (id, username, password) VALUES (3, 'lichenjing', '333333');