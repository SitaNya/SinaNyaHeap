package com.forte.demo.robot.timetask;

import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.BaseTimeJob;
import com.forte.qqrobot.utils.CQCodeUtil;

import static com.forte.demo.robot.db.tools.GetTime.getNowString;

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
@CronTask("0 0 */6 * * ?")
public class TimeTask2 extends BaseTimeJob {
    /**
     * 实现抽象类的方法，此处代表每次定时任务触发的时候，向群号为"123456789"的群发送一个at全体并问好
     */
    @Override
    public void execute(MsgSender msgSender, CQCodeUtil cqCodeUtil) {
            msgSender.SENDER.sendGroupMsg("808619122", getNowString()+"\t监控机器人每6小时通报一次自身存活");
    }
}
