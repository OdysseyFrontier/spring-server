create table enjoytrip.board
(
    board_no      bigint auto_increment
        primary key,
    member_id     bigint                              not null,
    subject       varchar(100)                        not null,
    content       varchar(2000)                       not null,
    register_time timestamp default CURRENT_TIMESTAMP not null,
    type          enum ('notice', 'community')        not null,
    constraint board_to_members_member_id_fk
        foreign key (member_id) references enjoytrip.members (member_id)
);

