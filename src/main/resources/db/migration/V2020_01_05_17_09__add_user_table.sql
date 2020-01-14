create table user(
    id bigint not null primary key auto_increment,
    username varchar(64) not null,
    password varchar(32) not null,
    phone_number varchar(11) not null,
    created_at datetime not null,
    updated_at datetime not null
);
