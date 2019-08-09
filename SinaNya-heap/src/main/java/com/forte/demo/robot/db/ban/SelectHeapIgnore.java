package com.forte.demo.robot.db.ban;

import com.forte.demo.robot.db.tools.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.forte.demo.robot.System.heapIgnore;
import static com.forte.demo.robot.db.tools.GetTime.getNowString;

/**
 * @author SitaNya
 * @date 2019-08-09
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class SelectHeapIgnore {
    private static final Logger Log = LogManager.getLogger(SelectHeapIgnore.class.getName());

    /**
     * 读取数据库中的骰点历史信息到缓存
     */
    public ArrayList<String> selectHeapIgnore() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from heapIgnore";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        heapIgnore.add(set.getString("botId"));
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return heapIgnore;
    }
}
