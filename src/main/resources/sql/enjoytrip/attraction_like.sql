create table enjoytrip.attraction_like
(
    member_id   bigint                             not null,
    content_id  int                                not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    primary key (member_id, content_id),
    constraint fk_like_attraction_info1
        foreign key (content_id) references enjoytrip.attraction_info (content_id),
    constraint fk_like_members1
        foreign key (member_id) references enjoytrip.members (member_id)
);

create index fk_like_attraction_info1_idx
    on enjoytrip.attraction_like (content_id);

