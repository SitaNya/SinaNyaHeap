package com.forte.demo.robot.db.ban;

import com.forte.demo.robot.db.tools.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.forte.demo.robot.System.heapIgnore;

/**
 * @author SitaNya
 * @date 2019-08-09
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class InsertHeapIgnore {
    private static final Logger Log = LogManager.getLogger(InsertHeapIgnore.class.getName());

    /**
     * 将历史骰点信息插入数据库，不过这个语句是由定时器每分钟调用一次的
     * 如果觉得卡的话可以去调整dice.sinanya.listener.InputHistoryToDatabase里的间隔时间
     * 先查询是否存在条目，不存在则插入，存在则更新
     */
    public void insertHeapIgnore(String botId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO heapIgnore(botId) VALUES(?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(botId));
                ps.executeUpdate();
            }
            if (!heapIgnore.contains(botId)){
                heapIgnore.add(botId);
            }
        }  catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
