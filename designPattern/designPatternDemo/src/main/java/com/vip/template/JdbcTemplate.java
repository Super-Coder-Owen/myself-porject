package com.vip.template;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板模式
 */
public class JdbcTemplate {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    private PreparedStatement getPreparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    private ResultSet executeQuery(PreparedStatement ps, Object[] values) throws SQLException {
        if (null != values && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i, values[i]);
            }
        }
        return ps.executeQuery();
    }

    private void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    private void closeStatement(PreparedStatement ps) throws SQLException {
        ps.close();
    }

    private void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    private List<?> parseResultSet(ResultSet rs, RowMapper rowMapper) throws SQLException {
        List<Object> result = new ArrayList();
        int rowNum = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return result;
    }

    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            connection = this.getConnection();
            // 创建语句集
            ps = this.getPreparedStatement(connection, sql);
            // 执行语句集，获得结果集
            rs = this.executeQuery(ps, values);
            // 解析语句集
            List<?> result = this.parseResultSet(rs, rowMapper);

            // 关闭结果集
            this.closeResultSet(rs);
            // 关闭语句集
            this.closeStatement(ps);
            // 关闭连接
            this.closeConnection(connection);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
