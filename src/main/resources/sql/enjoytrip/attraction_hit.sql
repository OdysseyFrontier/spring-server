create table attraction_hit
(
    member_id   bigint                             not null,
    content_id  int                                not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    primary key (member_id, content_id),
    constraint fk_hit_copy1_attraction_info1
        foreign key (content_id) references attraction_info (content_id),
    constraint fk_hit_members10
        foreign key (member_id) references members (member_id)
);

create index fk_hit_copy1_attraction_info1_idx
    on attraction_hit (content_id);

