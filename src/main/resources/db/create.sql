SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS departments (
id int PRIMARY KEY auto_increment,
deptName VARCHAR,
description VARCHAR,
NoOfEmployees int
);

CREATE TABLE IF NOT EXISTS users (
id int serial PRIMARY KEY,
username VARCHAR,
company_position VARCHAR,
role VARCHAR,
departmentid int
);

CREATE TABLE IF NOT EXISTS news (
id int serial PRIMARY KEY,
type VARCHAR,
content VARCHAR,
departmentid int
);