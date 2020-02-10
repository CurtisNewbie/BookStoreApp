
--------------------------------------------------

-- MYSQL Script for BookStore backend

--------------------------------------------------

--  create database
CREATE DATABASE IF NOT EXISTS bookstore;

-- create user for this bookstore specifically
CREATE USER 'bsadmin'@'localhost' IDENTIFIED BY 'bookstoreadmin';

-- grant privileges to this user
GRANT ALL PRIVILEGES ON bookstore.* TO 'bsadmin'@'localhost';

-- check the foreign keys used in this database
SELECT * FROM information_schema.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_SCHEMA = "bookstore";
