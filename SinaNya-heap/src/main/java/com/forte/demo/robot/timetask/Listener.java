package com.forte.demo.robot.timetask;

import com.forte.demo.robot.db.ban.DeleteHeapIgnore;
import com.forte.demo.robot.db.ban.InsertHeapIgnore;
import com.forte.qqrobot.anno.Constr;
import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.beans.messages.msgget.MsgGet;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.forte.demo.robot.System.heapIgnore;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 总监听入口类，这里是实际上接收到消息的第一个类
 */
public class Listener {

    private static Logger log = LogManager.getLogger(Listener.class.getName());

    private Listener() {
    }

    @Constr
    public static Listener getInstance() {
        return new Listener();
    }

    /**
     * 群无过滤入口类，用于日志消息的捕捉
     * 机器人的开启关闭也是在这一层识别的
     *
     * @param msgGet      消息实体
     * @param msgGetTypes 消息来源类型
     * @param msgSender   发送器
     * @param msgGroup    群消息对象
     */
    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "\\.ignore[ ]*\\d+")
    public void listenerInputIgnore(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, GroupMsg msgGroup) {
        String botId = msgGet.getMsg().replaceAll("\\.ignore[ ]*", "").trim();
        new InsertHeapIgnore().insertHeapIgnore(botId);
        msgSender.SENDER.sendGroupMsg("808619122", "已将" + botId + "加入心跳检测忽略名单");
    }

    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "\\.ignore[ ]*remove[ ]*\\d+")
    public void listenerRemoveIgnore(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, GroupMsg msgGroup) {
        String botId = msgGet.getMsg().replaceAll("\\.ignore[ ]*remove[ ]*", "").trim();
        new DeleteHeapIgnore().deleteHeapIgnore(botId);
        msgSender.SENDER.sendGroupMsg("808619122", "已将" + botId + "移出心跳检测忽略名单");
    }

    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "\\.ignore[ ]*show[ ]*")
    public void listenerGetIgnore(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, GroupMsg msgGroup) {
        String botId = msgGet.getMsg().replaceAll("\\.ignore[ ]*show[ ]*", "").trim();
        StringBuilder stringBuilder = new StringBuilder()
                .append("当前心跳检测忽略名单");
        for (String ignorebotId : heapIgnore) {
            stringBuilder.append("\n")
                    .append(ignorebotId);
        }
        msgSender.SENDER.sendGroupMsg("808619122", stringBuilder.toString());
    }
}
