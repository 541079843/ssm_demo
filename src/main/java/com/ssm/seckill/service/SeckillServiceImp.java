package com.ssm.seckill.service;

import com.ssm.seckill.dao.SeckillDao;
import com.ssm.seckill.dao.SuccessKilledDao;
import com.ssm.seckill.dto.Exposer;
import com.ssm.seckill.dto.SeckillExecution;
import com.ssm.seckill.entity.Seckill;
import com.ssm.seckill.entity.SuccessKilled;
import com.ssm.seckill.exception.RepeatKillException;
import com.ssm.seckill.exception.SeckillCloseException;
import com.ssm.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by lixy3 on 18-1-31.
 */
public class SeckillServiceImp implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private SeckillDao seckillDao;

    private SuccessKilledDao successKilledDao;

    private final String slat = "sfasdfwer32423Ewrfqwe";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    /**
     * 秒杀开启则返回 秒杀地址,否则返回 系统时间和秒杀时间
     *
     * @param seckillId
     */
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();

        Date nowTime = new Date();

        if (nowTime.getTime() < startTime.getTime() ||
                nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        String md5 = getMd5(seckillId);

        return new Exposer(true, md5, seckillId);
    }

    /**
     * 执行秒杀
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }

        Date now = new Date();
        // 执行秒杀: 减库存 + 记录明细
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, now);
            if (updateCount <= 0) {
                // 秒杀 活动过期
                throw new SeckillCloseException("seckill is closed");
            } else {
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    // 重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    // 秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, 1, "秒杀成功", successKilled);
                }
            }
        } catch (SeckillCloseException e) {
            throw  e;
        } catch (RepeatKillException e) {
            throw  e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 所有编译期异常, 转化为运行期异常
            throw  new SeckillException("seckill inner error:" + e.getMessage());
        }
    }


    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());

        return md5;
    }
}
