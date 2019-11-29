create database webmart;
use webmart;
create table customers(
customerId integer(10) not null,
firstName varchar(50),
lastName varchar(50),
phoneNumber varchar(20),
email varchar(50),
primary key(customerId));

create table employees(
employeeId integer(10) not null,
password varchar(50),
firstName varchar(50),
lastName varchar(50),
phoneNumber varchar(20),
email varchar(50),
primary key (employeeId));

create table inventory(
productCode integer(10) not null,
category varchar(50),
productName varchar(50),
price decimal(50, 2),
stock integer(50),
primary key (productCode));

create user 'customer1'@'%' identified by '#mtsu';
grant all privileges on *.* to 'customer1'@'%' identified by '#mtsu';