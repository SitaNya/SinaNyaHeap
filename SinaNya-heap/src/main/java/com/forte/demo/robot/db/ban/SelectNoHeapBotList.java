package com.forte.demo.robot.db.ban;

import com.forte.demo.robot.db.tools.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

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
    public ArrayList<String> selectNotHeapBotList() {
        ArrayList<String> noHeapBotList = new ArrayList<>(50);
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from heap";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        if (set.getBoolean("enable")) {
                            Timestamp timestamp = set.getTimestamp("time");
                            int reduce = (int) (System.currentTimeMillis() - timestamp.getTime()) / 1000 / 60;
                            if (reduce>60000){
                                noHeapBotList.add(set.getString("botId"));
                            } else {
                                Log.info(set.getString("botId") + "于" + getNowString() + "准时报告");
                            }
                        } else {
                            Log.info(set.getString("botId") + "被设定为不参与心跳报告，忽略");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return noHeapBotList;
    }
}
