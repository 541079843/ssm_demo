#数据库初始化脚本

#创建秒杀数据库
CREATE DATABASE seckill;

USE seckill;

#创建 秒杀库存表
CREATE TABLE seckill(
    `seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
    `name` varchar(120) NOT NULL COMMENT '商品名称',
    `number` int NOT NULL COMMENT '库存数量',
    `start_time` timestamp NOT NULL COMMENT '秒杀开始时间',
    `end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (seckill_id),
    KEY idx_start_time(start_time),
    KEY inx_end_time(end_time),
    KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

#初始化数据
INSERT INTO
    seckill(name,number,start_time,end_time)
VALUES
    ('1000元秒杀iphone8', 100, '2018-02-01 00:00:00', '2018-02-05 00:00:00'),
    ('800元秒杀iphone7', 60, '2018-02-01 00:00:00', '2018-02-05 00:00:00'),
    ('500元秒杀华为P6', 20, '2018-02-01 00:00:00', '2018-02-05 00:00:00'),
    ('200元秒小米5', 40, '2018-02-01 00:00:00', '2018-02-05 00:00:00');


#创建 秒杀成功明细表
CREATE TABLE success_killed(
    `seckill_id` bigint NOT NULL COMMENT '',
    `user_phone` bigint NOT NULL COMMENT '用户手机号',
    `state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识:-1,无效;0,成功;1,已付款',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY(seckill_id, user_phone),
    KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';