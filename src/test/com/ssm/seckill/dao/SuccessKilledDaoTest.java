package com.ssm.seckill.dao;

import com.ssm.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by lixy3 on 18-1-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long seckillId = 1000l;
        long userPhone = 13622115864l;

        int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone);

        System.out.println(insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {

        long seckillId = 1000l;
        long userPhone = 13622115864l;

        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);

        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}