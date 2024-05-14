create table enjoytrip.board_hit
(
    board_hit_id bigint auto_increment
        primary key,
    member_id    bigint                             not null,
    board_no     bigint                             not null,
    create_time  datetime default CURRENT_TIMESTAMP not null,
    update_time  datetime default CURRENT_TIMESTAMP not null,
    constraint fk_hit_board1
        foreign key (board_no) references enjoytrip.board (board_no),
    constraint fk_hit_members1
        foreign key (member_id) references enjoytrip.members (member_id)
);

create index fk_hit_board1_idx
    on enjoytrip.board_hit (board_no);

