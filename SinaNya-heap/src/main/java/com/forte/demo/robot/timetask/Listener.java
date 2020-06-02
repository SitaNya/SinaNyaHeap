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

    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "\\.ignore[ ]*\\d+")
    public void listenerInputIgnore(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, GroupMsg msgGroup) {
        try {
            String botId = msgGet.getMsg().replaceAll("\\.ignore[ ]*", "").trim();
            new InsertHeapIgnore().insertHeapIgnore(botId);
            msgSender.SENDER.sendGroupMsg("808619122", "已将" + botId + "加入心跳检测忽略名单");
        }catch (NullPointerException e){
            log.error(e.getMessage(),e);
            log.error(msgGet.getMsg());
        }
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
        StringBuilder stringBuilder = new StringBuilder()
                .append("当前心跳检测忽略名单");
        for (String ignorebotId : heapIgnore) {
            stringBuilder.append("\n")
                    .append(ignorebotId);
        }
        msgSender.SENDER.sendGroupMsg("808619122", stringBuilder.toString());
    }

    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "\\.ignore[ ]*show[ ]*")
    public void listenerRmBanFromShiki(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, GroupMsg msgGroup) {
        StringBuilder stringBuilder = new StringBuilder()
                .append("当前心跳检测忽略名单");
        for (String ignorebotId : heapIgnore) {
            stringBuilder.append("\n")
                    .append(ignorebotId);
        }
        msgSender.SENDER.sendGroupMsg("808619122", stringBuilder.toString());
    }
}
