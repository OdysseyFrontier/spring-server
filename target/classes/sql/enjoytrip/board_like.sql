create table enjoytrip.board_like
(
    member_id   bigint                             not null,
    board_no    bigint                             not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    primary key (member_id, board_no),
    constraint fk_like_members100
        foreign key (member_id) references enjoytrip.members (member_id),
    constraint fk_plan_like_copy1_board1
        foreign key (board_no) references enjoytrip.board (board_no)
);

create index fk_plan_like_copy1_board1_idx
    on enjoytrip.board_like (board_no);

