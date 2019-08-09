package com.forte.demo.robot.timetask;

import com.forte.demo.robot.db.ban.SelectNoHeapBotList;
import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.beans.cqcode.CQCode;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.BaseTimeJob;
import com.forte.qqrobot.utils.CQCodeUtil;

import java.util.ArrayList;

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
@CronTask("0 */1 * * * ?")
public class TimeTask1 extends BaseTimeJob {
    /**
     * 实现抽象类的方法，此处代表每次定时任务触发的时候，向群号为"123456789"的群发送一个at全体并问好
     */
    @Override
    public void execute(MsgSender msgSender, CQCodeUtil cqCodeUtil) {
        CQCode cqCodeAtAll = cqCodeUtil.getCQCode_AtAll();
        ArrayList<String> noHeapBotList = new SelectNoHeapBotList().selectNotHeapBotList();
        for (String noHeapBotId:noHeapBotList) {
            msgSender.SENDER.sendGroupMsg("808619122", cqCodeAtAll.toString() + "各位,"+noHeapBotId+"骰娘已超过10分钟未报告心跳连接，请骰主关注。\n如不需要使用心跳检测功能，请于配置文件末端添加heap=false并重启");
        }
    }
}
