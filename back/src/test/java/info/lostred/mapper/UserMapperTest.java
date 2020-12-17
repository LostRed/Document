package info.lostred.mapper;

import info.lostred.pojo.Edu;
import info.lostred.pojo.Prof;
import info.lostred.pojo.State;
import info.lostred.pojo.User;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
    private SqlSession sqlSession;
    private UserMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("test");
        user.setSex("男");
        user.setPassword("123456");
        user.setName("test");
        user.setMobilePhone("12354646321");
        user.setEmail("");
        user.setPoint(0);
        user.setRegTime(new Date());
        user.setProf(new Prof(1, null));
        user.setEdu(new Edu(1, null));
        user.setState(new State(4, null, null));
        int rs = mapper.insert(user);
        System.out.println(rs);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserId(3);
        user.setUsername("test");
        user.setPassword("123456");
        user.setName("test");
        user.setProf(new Prof(1, null));
        user.setEdu(new Edu(1, null));
        user.setState(new State(4, null, null));
        int rs = mapper.update(user);
        System.out.println(rs);
    }

    @Test
    public void testCountByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("username", "");
        int rs = mapper.countByCondition(conditions);
        System.out.println(rs);
    }

    @Test
    public void testSelectByUsername() {
        User admin = mapper.selectByUsername("xiaowang");
        System.out.println(admin);
    }

    @Test
    public void testPageSelectByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("username", "%%");
        conditions.put("eduId", 1);
        List<User> admins = mapper.pageSelectByCondition(conditions, 0, 5);
        for (User admin : admins) {
            System.out.println(admin);
        }
    }
}
