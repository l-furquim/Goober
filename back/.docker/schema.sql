create table users (
    "user_id" uuid not null primary key,
    "user_name" varchar(255) not null,
    "user_email" varchar(255) not null,
    "user_password" varchar(255) not null,
    "user_image" varchar(255),
    "status" varchar(255) not null
);

create table question (
       "user_name" varchar(255) not null,
       "announcement_id" bigint not null,
       "question_content" varchar(255) not null,
       "question_id" bigint primary key not null ,
       "question_status" varchar(255) not null,
        "parent_question_id" bigint
);

create table product (
    "product_id" uuid primary key not null,
    "product_name" varchar(255) not null,
    "product_price" decimal(19,4) not null,
    "product_categorie" varchar(255) not null,
    "product_description" varchar(255),
    "product_image" varchar(255)
);

create table cart (
    "cart_id" uuid not null primary key,
    "items_quantity" int not null,
    "total_price" decimal(19,4) not null,
    "user_id" uuid not null
);

create table announcement (
    "announcement_id" uuid  not null primary key,
    "announcement_name" varchar(255) not null,
    "announcement_price" decimal(19,4) not null,
    "announcement_likes" int not null,
    "announcement_questions" int not null,
    "announcer_name" varchar(255) not null,
    "product_images" varchar(255)not null
);

create table userVerifier (
"userverifier_id" uuid  not null primary key,
"userverifier_name" varchar(255) not null,
"userverifier_email" varchar(255) not null,
"userverifier_password" varchar(255) not null,
"expires_at" timestamp not null,
"status" varchar(255) not null,
"code" varchar(36)
);

create table announcement_products (
id serial primary key,
announcement_id uuid not null,
product_id uuid not null
);

create table announcement_questions (
id serial primary key,
announcement_id uuid not null,
question_id uuid not null
);

create table cart_products (
id serial primary key,
cart_id uuid not null,
product_id uuid not null
);

create table question_answers (
id serial primary key,
question_id uuid not null,
answer_id uuid not null
);




