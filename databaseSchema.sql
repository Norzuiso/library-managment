create table if not exists book
(
    id        int auto_increment
    primary key,
    author    varchar(255) null,
    editorial varchar(255) null,
    type      varchar(255) null
    );

create table if not exists library
(
    id      int auto_increment
    primary key,
    address varchar(255) null,
    name    varchar(255) null,
    phone   varchar(255) null
    );

create table if not exists reader
(
    id      int auto_increment
    primary key,
    address varchar(255) null,
    name    varchar(255) null,
    phone   varchar(255) null
    );

create table if not exists loan
(
    id        int auto_increment
    primary key,
    loan_date date null,
    book_id   int  null,
    reader_id int  null,
    constraint FK4tfdjvplqbjm5jyjn80nnnsap
    foreign key (book_id) references book (id),
    constraint FKntall4361lkjo60ekou24p8wq
    foreign key (reader_id) references reader (id)
    );

