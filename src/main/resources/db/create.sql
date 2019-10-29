CREATE DATABASE organisational_api;
\c organisational_api;

CREATE TABLE IF NOT EXISTS departments (
id SERIAL PRIMARY KEY,
deptName VARCHAR,
description VARCHAR,
NoOfEmployees int
);

CREATE TABLE IF NOT EXISTS users (
id SERIAL PRIMARY KEY,
username VARCHAR,
company_position VARCHAR,
role VARCHAR,
departmentid int
);

CREATE TABLE IF NOT EXISTS news (
id SERIAL PRIMARY KEY,
type VARCHAR,
content VARCHAR,
departmentid int
);

CREATE DATABASE organisational_api_test WITH TEMPLATE organisational_api;