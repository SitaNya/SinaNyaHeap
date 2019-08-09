package com.forte.demo.robot.timetask;

import com.forte.demo.robot.db.ban.SelectNoHeapBotList;
import com.forte.demo.robot.entity.AlterInfo;
import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.beans.cqcode.CQCode;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.BaseTimeJob;
import com.forte.qqrobot.utils.CQCodeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 这是一个简单的定时任务实例
 * 通过cron表达式来实现定时周期
 * 此处代表了每天0点执行一次
 * <p>
 * 其他定时任务相关注解请查阅文档
 * <p>
 * <p>
 * 除了注解，你还需要实现接口或者继承抽象类
 * 此处使用抽象类作为演示
 * 其余可选接口请查阅文档
 *
 * @author ForteScarlet <[email]ForteScarlet@163.com>
 * @since JDK1.8
 **/
@CronTask("0 */10 * * * ?")
public class TimeTask1 extends BaseTimeJob {
    /**
     * 实现抽象类的方法，此处代表每次定时任务触发的时候，向群号为"123456789"的群发送一个at全体并问好
     */
    @Override
    public void execute(MsgSender msgSender, CQCodeUtil cqCodeUtil) {
        ArrayList<AlterInfo> noHeapBotList = new SelectNoHeapBotList().selectNotHeapBotList();
        for (AlterInfo alterInfo : noHeapBotList) {
            CQCode cqCodeAt;
            if (alterInfo.getMaster().equals("0")) {
                cqCodeAt = cqCodeUtil.getCQCode_AtAll();
            } else {
                cqCodeAt = cqCodeUtil.getCQCode_At(alterInfo.getMaster());
            }
            msgSender.SENDER.sendGroupMsg("808619122", cqCodeAt.toString() + "\t(" + alterInfo.getBotId() + ")骰娘未报告心跳连接，距离上次报告有" + alterInfo.getReduce() + "分钟，最后一次报告于" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(alterInfo.getLastTime().getTime())) + "请骰主关注。\n如不需要使用心跳检测功能，请于配置文件末端添加heap=false并重启");
        }
    }
}
