drop table if exists member;

create table
    member
(
    id           varchar(36),
    password     varchar(100),
    mail_address varchar(300),
    member_name  varchar(100),
    role         varchar(100),
    delete_flag boolean
);

insert into member(id, mail_address, password, member_name, role, delete_flag)
values ('550e8400-e29b-41d4-a716-446655440002', 'henoheno@mohe.zi',
        '$2a$04$5bMt55qnhiaXIkHIduBFyuWS0ON45PcG5SU.wFXN8sHRU5hNfB7Nq', '辺野　もへ子', 'ROLE_PRODUCT_OWNER', false);