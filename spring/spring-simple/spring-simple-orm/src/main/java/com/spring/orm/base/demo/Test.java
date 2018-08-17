package com.spring.orm.base.demo;

/**
 * ORM(对象关系映射 Object Relation Mapping)
 * 将持久化数据转化为Java对象
 * 用Java对象来描述对象与对象之间的关系和数据内容
 * <p>
 * Hibernate/MyBatis/Spring JDBC
 * Hibernate 全自动 不需要写sql 耗性能
 * MyBatis 半自动 支持单表映射，多表关联需要配置，轻量级
 * Spring JDBC 手动 SQL和映射都要自己实现
 * <p>
 * <p>
 * Spring JDBC ORM解决问题
 * 1.大数据监控
 * 2.数据吞吐量大
 * 3.数据存储方式多样化
 * 4.数据源需要频繁切换
 * 5.API无法统一
 */
public class Test {
    public static void main(String[] args) {
/*        List<String> list = getBetweenDays("2018-08-12", "2018-08-15");
        System.out.println(Arrays.toString(list.toArray()));*/

        System.out.println(String.format("%1$.2f%%", (2 * 100.0) / 3));
    }
}
