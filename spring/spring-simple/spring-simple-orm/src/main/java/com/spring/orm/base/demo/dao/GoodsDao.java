package com.spring.orm.base.demo.dao;

import com.spring.orm.base.demo.model.Goods;
import com.spring.orm.base.framework.BaseDaoSupport;
import com.spring.orm.base.framework.QueryRule;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 *
 */
@Repository
public class GoodsDao extends BaseDaoSupport<Goods, Long> {
    @Override
    protected String getPKColumn() {
        return "id";
    }

   /* private DynamicDataSource dataSource;

    @Resource(name = "dynamicDataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = (DynamicDataSource) dataSource;
        this.setDataSourceReadOnly(dataSource);
        this.setDataSourceWrite(dataSource);
    }*/

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.setDataSourceReadOnly(dataSource);
        this.setDataSourceWrite(dataSource);
    }

    /**
     * @param name
     * @return
     */
    public List<Goods> selectByName(String name) throws Exception {
        //构建一个QureyRule 查询规则
        QueryRule queryRule = QueryRule.getInstance();
        //查询一个name= 赋值 结果，List
        queryRule.andEqual("goods_name", name);
        //相当于自己再拼SQL语句
        return super.select(queryRule);
    }


    public List<Goods> selectAll() throws Exception {
        QueryRule queryRule = QueryRule.getInstance();
        return super.select(queryRule);
    }

    /**
     * @throws Exception
     */
    public int insertAll(List<Goods> memberList) throws Exception {
        return super.insertAll(memberList);
    }

    /**
     * @throws Exception
     */
    public boolean update(Goods goods) throws Exception {
        return super.update(goods);
    }

    public boolean delete(Goods goods) throws Exception {
        return super.delete(goods);
    }
}
