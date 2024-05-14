create table enjoytrip.attraction_description
(
    content_id int            not null
        primary key,
    homepage   varchar(100)   null,
    overview   varchar(10000) null,
    telname    varchar(45)    null,
    constraint attraction_detail_to_attraciton_id_fk
        foreign key (content_id) references enjoytrip.attraction_info (content_id)
);

