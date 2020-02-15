create table testcase
(
    id             bigint       not null primary key auto_increment,
    problem_id     bigint       not null,
    inputFileId    varchar(40)  not null,
    inputFilename  varchar(255) not null,
    outputFileId   varchar(40)  not null,
    outputFilename varchar(40)  not null,
    created_at     datetime     not null,
    updated_at     datetime     not null
) auto_increment = 1000;
