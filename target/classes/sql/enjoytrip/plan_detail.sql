create table enjoytrip.plan_detail
(
    plan_detail_id bigint auto_increment
        primary key,
    plan_id        bigint      not null,
    day            varchar(45) not null,
    content_id     int         not null,
    plan_time      datetime    null,
    constraint fk_plan_detail_attraction_info1
        foreign key (content_id) references enjoytrip.attraction_info (content_id),
    constraint fk_plan_detail_plan1
        foreign key (plan_id) references enjoytrip.plan (plan_id)
);

create index fk_plan_detail_attraction_info1_idx
    on enjoytrip.plan_detail (content_id);

create index fk_plan_detail_plan1_idx
    on enjoytrip.plan_detail (plan_id);

