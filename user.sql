-- auto-generated definition
create table user
(
    id           bigint auto_increment
        primary key,
    username     varchar(255)                       null comment '用户主键
',
    userAccount  varchar(255)                       null comment '账号
',
    avatarUrl    varchar(1024)                      null comment '头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(255)                       not null comment '用户密码',
    phone        varchar(255)                       null comment '非空',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int                                null comment '状态 0 正常 ',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 null comment '是否删除',
    userRole     tinyint  default 0                 null comment '角色 0- 默认角色 1-管理员',
    planetCode   varchar(512)                       null comment '星球编号'
)
    comment '用户表';
