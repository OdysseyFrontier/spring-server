create table enjoytrip.member_search_hit
(
    member_search_hit_id bigint auto_increment
        primary key,
    searching_member_id  bigint                             not null,
    searched_member_id   bigint                             not null,
    create_time          datetime default CURRENT_TIMESTAMP not null,
    constraint fk_followers_members10
        foreign key (searching_member_id) references enjoytrip.members (member_id),
    constraint fk_followers_members20
        foreign key (searched_member_id) references enjoytrip.members (member_id)
);

create index fk_followers_members1_idx
    on enjoytrip.member_search_hit (searching_member_id);

create index fk_followers_members2_idx
    on enjoytrip.member_search_hit (searched_member_id);

