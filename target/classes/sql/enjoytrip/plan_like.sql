create table plan_like
(
    member_id   bigint                             not null,
    plan_id     bigint                             not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    primary key (member_id, plan_id),
    constraint fk_like_members10
        foreign key (member_id) references members (member_id),
    constraint fk_plan_like_plan1
        foreign key (plan_id) references plan (plan_id)
);

create index fk_plan_like_plan1_idx
    on plan_like (plan_id);

