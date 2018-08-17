import com.spring.orm.base.demo.dao.GoodsDao;
import com.spring.orm.base.demo.dao.UsersDao;
import com.spring.orm.base.demo.model.Goods;
import com.spring.orm.base.demo.model.Users;
import com.spring.orm.base.framework.QueryRule;
import com.spring.orm.extend.model.GoodsBean;
import com.spring.orm.extend.springJdbc.SupportJdbcTemplate;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrmTest {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    UsersDao usersDao;
    @Autowired
    SupportJdbcTemplate supportJdbcTemplate;

    @Test
    public void test() {
        System.out.println(goodsDao);
    }

    @Test
    @Ignore
    public void testRule() {
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.andEqual("goodsName", "测试");
        queryRule.getRuleList();
    }

    @Test
    public void testSelect() throws Exception {
        List<Goods> list = goodsDao.selectAll();
        System.out.println(Arrays.toString(list.toArray()));
        list = goodsDao.selectByName("鸡蛋");
        System.out.println(Arrays.toString(list.toArray()));
        List<Users> userList = usersDao.selectAll();
        System.out.println(Arrays.toString(userList.toArray()));
    }


    @Test
    public void testExtendSelect() throws Exception {
        String sql = "select * from t_scm_goods where goods_name = ?";
        GoodsBean goodsBean = this.supportJdbcTemplate.queryForBean(sql, GoodsBean.class, "鸡蛋");
        System.out.println(goodsBean);
    }
}

