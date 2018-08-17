package com.spring.orm.base.demo;

import com.spring.orm.base.demo.model.Goods;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JDBC操作
 */
public class JdbcTest {
    public static void main(String[] args) {
        List<?> list = select(Goods.class);
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static List<?> select(Class<?> clazz) {
        List<Object> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2.建立连接
            connection = DriverManager.getConnection("jdbc:mysql://10.0.40.244:3306/m2c_scm?user=root&password=root");
            // 3.创建语句并开始事务
            ps = connection.prepareStatement("select id,goods_id as goodsId,goods_name as goodsName from t_scm_goods limit 0,5");
            // 4.执行语句集
            rs = ps.executeQuery();
            // 5.获取结果集

            Integer columnCount = rs.getMetaData().getColumnCount();// 列的数量
            while (rs.next()) {
                Object instance = clazz.newInstance();
                for (int i = 1; i < columnCount + 1; i++) {
                    String columnName = rs.getMetaData().getColumnLabel(i);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    // 数据类型映射
                    field.set(instance, rs.getObject(columnName));
                }
                resultList.add(instance);
            }

            // ORM begin

            // ORM end

            // 6.关闭结果集，关闭语句集，关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}
