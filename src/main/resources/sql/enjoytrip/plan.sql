create table plan
(
    plan_id            bigint auto_increment
        primary key,
    member_id          bigint                                                                       not null,
    title              varchar(45)                                                                  not null,
    description        varchar(100)                                                                 null,
    season             enum ('SPRING', 'SUMMER', 'FALL', 'WINTER', 'ETC') default 'ETC'             not null,
    start_time         datetime                                                                     null,
    access_type        enum ('PUBLIC', 'PRIVATE')                                                   null,
    recent_update_time datetime                                           default CURRENT_TIMESTAMP not null,
    constraint fk_plan_members1
        foreign key (member_id) references members (member_id)
);

