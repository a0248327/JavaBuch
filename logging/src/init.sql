CREATE DATABASE log4j CHARACTER SET utf8;

USE log4j;

CREATE TABLE tb_log (id INT AUTO_INCREMENT, date VARCHAR(255), priority VARCHAR(255), message TEXT, classname VARCHAR(255), PRIMARY KEY(id));

