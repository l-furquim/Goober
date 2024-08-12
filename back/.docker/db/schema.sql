create table users (
    userId varchar(36) not null primary key,
    userName varchar(255) not null,
    userEmail varchar(255) not null,
    userPassword varchar(255) not null,
    userImage varchar(255),
    status varchar(255) not null
);

create table question (
       userName varchar(255) not null,
       announcementId int not null,
       questionContent varchar(255) not null,
       questionId int primary key not null ,
       questionStatus varchar(255) not null
);

create table product (
    productID varchar(36) primary key not null,
    productName varchar(255) not null,
    productPrice decimal(19,4) not null,
    productDescription varchar(255),
    productImage varchar(255)
);

create table cart (
    cartId varchar(36) not null primary key,
    itemsQuantity int not null,
    totalPrice decimal(19,4) not null,
    userId varchar(36) not null
);

create table announcement (
    announcementId int  not null primary key,
    announcementName varchar(255) not null,
    announcementPrice decimal(19,4) not null,
    announcementLikes int not null,
    announcementQuestions int not null,
    announcerName varchar(255) not null,
    announcementImages varchar(255)not null
);

create table userVerifier (
userVerifierId int  not null primary key,
userVerifierEmail varchar(255) not null,
userVerifierPassword varchar(255) not null,
expiresAt timestamp not null,
status varchar(255) not null,
code varchar(36)
);




