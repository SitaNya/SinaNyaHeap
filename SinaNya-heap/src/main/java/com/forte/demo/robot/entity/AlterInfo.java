package com.forte.demo.robot.entity;

import java.sql.Timestamp;

/**
 * @author SitaNya
 * @date 2019-08-09
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class AlterInfo {
    String botId="0";
    int reduce=0;
    Timestamp lastTime=new Timestamp(System.currentTimeMillis());
    String master="0";

    public AlterInfo() {
    }

    public AlterInfo(String botId, int reduce, Timestamp lastTime, String master) {
        this.botId = botId;
        this.reduce = reduce;
        this.lastTime = lastTime;
        this.master = master;
    }

    public String getBotId() {
        return botId;
    }

    public int getReduce() {
        return reduce;
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public String getMaster() {
        return master;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public void setReduce(int reduce) {
        this.reduce = reduce;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}
