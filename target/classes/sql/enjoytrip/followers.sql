create table followers
(
    follower_id  bigint                             not null,
    following_id bigint                             not null,
    create_time  datetime default CURRENT_TIMESTAMP not null,
    primary key (follower_id, following_id),
    constraint fk_followers_members1
        foreign key (follower_id) references members (member_id),
    constraint fk_followers_members2
        foreign key (following_id) references members (member_id)
);

create index fk_followers_members1_idx
    on followers (follower_id);

create index fk_followers_members2_idx
    on followers (following_id);

