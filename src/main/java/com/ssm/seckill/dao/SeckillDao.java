package com.ssm.seckill.dao;

import com.ssm.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lixy3 on 18-1-30.
 */
public interface SeckillDao {

    /**
     * 减库存
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime")  Date killTime);

    /**
     * 查询单个商品
     */
    Seckill queryById(long seckillId);

    /**
     * 分页查询商品
     *
     * 使用 @Param 注解的原因是:
     * java 没有保存形参的记录: queryAll(int offset, int limit) => queryAll(arg0, arg1)
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
