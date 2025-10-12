create database studentmanagement;
use studentmanagement;
create table teacher(
	id int primary key auto_increment,
    name nvarchar(100) not null,
    email varchar(50) unique,
    id_student int,
    status nvarchar(10),
    foreign key(id_student) references student(id) on delete set null);
create table department(
	id int primary key auto_increment,
    name nvarchar(40) not null);
create table class(
	id int primary key auto_increment,
    name varchar(10) not null,
    id_teacher int ,
    id_department int,
	foreign key(id_teacher) references teacher(id) on delete set null,
    foreign key(id_department) references department(id) on delete set null);
create table student(
	id int primary key auto_increment,
    name nvarchar(100),
    birthday date,
    phone varchar(10),
    email varchar(50),
    address nvarchar(100),
    sex varchar(6),
    status nvarchar(10),
    id_class int,
    foreign key(id_class) references class(id) on delete set null
);
create table subject(
	id int primary key auto_increment,
    name nvarchar(10) not null);
create table grades(
	id_student int,
    id_subject int,
    primary key(id_student, id_subject),
    grade float,
    foreign key(id_student) references student(id) on delete cascade,
    foreign key(id_subject) references subject(id) on delete cascade);
create table user(
	id int primary key auto_increment,
    username varchar(50) not null unique,
    password varchar(50) not null,
    role varchar(5));
create table registerclass(
	name nvarchar(50), 
    birthday date,
    sex varchar(6),
    phone varchar(10),
    address nvarchar(100),
    id_class int,
	foreign key(id_student) references student(id) on delete SET NULL
);