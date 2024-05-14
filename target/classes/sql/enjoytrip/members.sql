create table members
(
    member_id    bigint auto_increment
        primary key,
    name         varchar(20)                                                    not null,
    email_id     varchar(20)                                                    not null,
    email_domain varchar(45)                                                    null,
    password     varchar(16)                                                    not null,
    phone        varchar(20)                                                    not null,
    address      text                                                           null,
    status       enum ('ACTIVE', 'INACTIVE', 'ADMIN') default 'ACTIVE'          not null,
    image        varchar(45)                                                    null,
    birthday     timestamp                                                      null,
    join_date    timestamp                            default CURRENT_TIMESTAMP not null
);

