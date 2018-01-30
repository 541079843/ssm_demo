package com.ssm.seckill.entity;

import java.util.Date;

/**
 * Created by lixy3 on 18-1-30.
 */
public class Seckill {

    private long seckillId;

    private long number;

    private String name;

    private Date startTime;

    private Date endTime;

    private Date createTime;


    public long getSeckillId() {
        return seckillId;
    }

    public long getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
