create table comments
(
    comment_id    bigint unsigned auto_increment
        primary key,
    member_id     bigint                             not null,
    board_no      bigint                             not null,
    content       varchar(45)                        not null,
    register_time datetime default CURRENT_TIMESTAMP not null,
    constraint fk_comments_board1
        foreign key (board_no) references board (board_no),
    constraint fk_comments_members1
        foreign key (member_id) references members (member_id)
);

create index fk_comments_board1_idx
    on comments (board_no);

create index fk_comments_members1_idx
    on comments (member_id);

