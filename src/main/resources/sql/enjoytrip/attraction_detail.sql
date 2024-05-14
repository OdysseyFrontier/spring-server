create table enjoytrip.attraction_detail
(
    content_id    int         not null
        primary key,
    cat1          varchar(3)  null,
    cat2          varchar(5)  null,
    cat3          varchar(9)  null,
    created_time  varchar(14) null,
    modified_time varchar(14) null,
    booktour      varchar(5)  null,
    constraint attraction_detail_to_basic_content_id_fk
        foreign key (content_id) references enjoytrip.attraction_info (content_id)
);

