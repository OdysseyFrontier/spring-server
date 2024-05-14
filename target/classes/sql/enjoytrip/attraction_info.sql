create table attraction_info
(
    content_id      int             not null
        primary key,
    content_type_id int             null,
    title           varchar(100)    null,
    addr1           varchar(100)    null,
    addr2           varchar(50)     null,
    zipcode         varchar(50)     null,
    tel             varchar(50)     null,
    first_image     varchar(200)    null,
    first_image2    varchar(200)    null,
    readcount       int             null,
    sido_code       int             null,
    gugun_code      int             null,
    latitude        decimal(20, 17) null,
    longitude       decimal(20, 17) null,
    mlevel          varchar(2)      null,
    constraint attraction_to_content_type_id_fk
        foreign key (content_type_id) references content_type (content_type_id),
    constraint attraction_to_gugun_code_fk
        foreign key (gugun_code) references gugun (gugun_code),
    constraint attraction_to_sido_code_fk
        foreign key (sido_code) references sido (sido_code)
);

create index attraction_to_content_type_id_fk_idx
    on attraction_info (content_type_id);

create index attraction_to_gugun_code_fk_idx
    on attraction_info (gugun_code);

create index attraction_to_sido_code_fk_idx
    on attraction_info (sido_code);

