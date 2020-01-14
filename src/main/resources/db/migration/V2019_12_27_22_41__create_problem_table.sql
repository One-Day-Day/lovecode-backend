create table problem(
    id bigint not null primary key auto_increment,
    title varchar(255) not null,
    description longtext,
    input_format_description longtext,
    output_format_description longtext,
    sample_input longtext,
    sample_output longtext,
    time_limit bigint not null,
    memory_limit bigint not null,
    created_at datetime not null,
    updated_at datetime not null
) auto_increment = 1000;
