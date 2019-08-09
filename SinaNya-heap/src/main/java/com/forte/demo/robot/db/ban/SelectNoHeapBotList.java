package com.forte.demo.robot.db.ban;

import com.forte.demo.robot.db.tools.DbUtil;
import com.forte.demo.robot.entity.AlterInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

import static com.forte.demo.robot.System.heapIgnore;
import static com.forte.demo.robot.db.tools.GetTime.getNowString;


/**
 * @author SitaNya
 * 日期: 2019-08-07
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class SelectNoHeapBotList {
    private static final Logger Log = LogManager.getLogger(SelectNoHeapBotList.class.getName());

    /**
     * 读取数据库中的骰点历史信息到缓存
     */
    public ArrayList<AlterInfo> selectNotHeapBotList() {
        ArrayList<AlterInfo> alterInfos=new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from heap";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        AlterInfo alterInfo = new AlterInfo();
                        String botId = set.getString("botId");
                        if (heapIgnore.contains(botId)){
                            Log.warn(botId+"在忽略列表中");
                            continue;
                        }
                        if (set.getBoolean("enable")) {
                            Timestamp timestamp = set.getTimestamp("time");
                            int reduce = (int) ((System.currentTimeMillis() - timestamp.getTime()) / 1000 / 60);
                            if (reduce>10){
                                alterInfo.setBotId(botId);
                                alterInfo.setLastTime(timestamp);
                                alterInfo.setMaster(set.getString("botMaster"));
                                alterInfo.setReduce(reduce);
                                Log.warn(String.format("%s未能准时报告，上一次报告时间为%s", botId, String.valueOf(timestamp)));
                            } else {
                                Log.info(String.format("%s于%s,大约%d分钟前准时报告", botId, getNowString(), reduce));
                            }
                        } else {
                            Log.warn(String.format("%s被设定为不参与心跳报告，忽略", botId));
                        }
                        alterInfos.add(alterInfo);
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return alterInfos;
    }
}
