package com.ssm.seckill.dao;

import com.ssm.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lixy3 on 18-1-30.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据 id 查询 SuccessKilled 并携带秒杀商品对象实体
     * */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
