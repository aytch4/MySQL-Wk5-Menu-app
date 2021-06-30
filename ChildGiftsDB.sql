create database if not exists children;

use children;

drop table if exists gifts;
drop table if exists children;

create table children ( 
	id int(10) not null auto_increment,
	name varchar(50) not null,
	primary key(id)
);

create table gifts (
	id int(10) not null auto_increment,
	item varchar(25) not null,
	children_id int(10) not null,
	primary key(id),
	foreign key(children_id) references children(id)
);
