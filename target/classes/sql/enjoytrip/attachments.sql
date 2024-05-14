create table enjoytrip.attachments
(
    attachment_id bigint auto_increment
        primary key,
    content_id    int                            not null,
    name          varchar(45) default 'undifine' not null,
    path          varchar(200)                   not null,
    type          enum ('IMAGE', 'ZIP', 'ETC')   not null,
    constraint fk_attachments_attraction_info1
        foreign key (content_id) references enjoytrip.attraction_info (content_id)
);

create index fk_attachments_attraction_info1_idx
    on enjoytrip.attachments (content_id);

