create table if not exists course
(
    id
        bigint
        auto_increment
        primary
            key,
    course_name
        varchar(255) not null,
    constraint UK_9dll001xc2cip6hug6axoab0p
        unique
        (
         course_name
            )
);

create table if not exists student
(
    id            bigint auto_increment
        primary key,
    date_of_birth datetime(6)  not null,
    email_address varchar(255) not null,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    constraint UK_68piymbjmju4vef8wax27ihox
        unique (email_address)
);

create index name_index
    on student (first_name, last_name);

create table if not exists results
(
    id
               bigint
        auto_increment
        primary
            key,
    score
               enum
                   (
                       'A',
                       'B',
                       'C',
                       'D',
                       'E',
                       'F'
                       ) not null,
    course_id  bigint    not null,
    student_id bigint    not null,
    constraint FKg30pq3ucxw909sr3u1o8e7klg
        foreign key
            (
             course_id
                ) references course
            (
             id
                ),
    constraint FKmipijxw5mdlc42ihg4brlp1fo
        foreign key
            (
             student_id
                ) references student
            (
             id
                )
);

