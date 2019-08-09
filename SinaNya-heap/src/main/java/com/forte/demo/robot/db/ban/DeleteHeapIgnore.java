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
public class DeleteHeapIgnore {
    private static final Logger Log = LogManager.getLogger(DeleteHeapIgnore.class.getName());
    /**
     * 从数据库中删除某一条线索
     *
     */
    public void deleteHeapIgnore(String botId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from heapIgnore where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, botId);
                ps.executeUpdate();
            }
            heapIgnore.remove(botId);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
