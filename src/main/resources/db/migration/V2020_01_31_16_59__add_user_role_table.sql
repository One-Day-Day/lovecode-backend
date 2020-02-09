create table user_role(
    id bigint not null primary key auto_increment,
    user_id bigint not null,
    role varchar(64) not null,
    created_at datetime not null,
    updated_at datetime not null
) auto_increment = 1000;
