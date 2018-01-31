package com.ssm.seckill.service;

import com.ssm.seckill.dto.Exposer;
import com.ssm.seckill.dto.SeckillExecution;
import com.ssm.seckill.entity.Seckill;
import com.ssm.seckill.exception.RepeatKillException;
import com.ssm.seckill.exception.SeckillCloseException;
import com.ssm.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by lixy3 on 18-1-31.
 */
public interface SeckillService {

    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    /**
     * 秒杀开启则返回 秒杀地址,否则返回 系统时间和秒杀时间
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;
}
