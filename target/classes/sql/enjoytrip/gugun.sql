create table enjoytrip.gugun
(
    gugun_code int         not null,
    gugun_name varchar(30) null,
    sido_code  int         not null,
    primary key (gugun_code, sido_code),
    constraint gugun_to_sido_sido_code_fk
        foreign key (sido_code) references enjoytrip.sido (sido_code)
);

create index gugun_to_sido_sido_code_fk_idx
    on enjoytrip.gugun (sido_code);

