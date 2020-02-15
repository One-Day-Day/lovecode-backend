create table testcase
(
    id             bigint       not null primary key auto_increment,
    problem_id     bigint       not null,
    input_file_id    varchar(40)  not null,
    input_filename  varchar(255) not null,
    output_file_id   varchar(40)  not null,
    output_filename varchar(40)  not null,
    created_at     datetime     not null,
    updated_at     datetime     not null
) auto_increment = 1000;
