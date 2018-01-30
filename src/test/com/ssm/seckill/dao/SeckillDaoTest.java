package com.ssm.seckill.dao;

import com.ssm.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lixy3 on 18-1-30.
 * 配置 spring 和 junit 整合, junit 启动时加载 springIOC 容器
 * 依赖 spring-test, junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;

        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckillList = seckillDao.queryAll(0,10);

        for (Seckill s :
                seckillList) {
            System.out.println(s);
        }
    }

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();

        int updateCount = seckillDao.reduceNumber(1000l, killTime);
        System.out.println(updateCount);
    }

}