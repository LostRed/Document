package info.lostred.mapper;

import info.lostred.pojo.Admin;
import info.lostred.pojo.Role;
import info.lostred.pojo.State;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AdminMapperTest {
    private SqlSession sqlSession;
    private AdminMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(AdminMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("123456");
        admin.setName("test");
        admin.setRole(new Role(1, "管理员"));
        admin.setState(new State(1, "启用", "管理员状态"));
        int rs = mapper.insert(admin);
        System.out.println(rs);
    }

    @Test
    public void testUpdate() {
        Admin admin = new Admin();
        admin.setAdminId(4);
        admin.setUsername(null);
        admin.setPassword("123456");
        admin.setName(null);
        admin.setRole(null);
        admin.setState(null);
        int rs = mapper.update(admin);
        System.out.println(rs);
    }


    @Test
    public void testDeleteById() {
        int rs = mapper.deleteById(8);
        System.out.println(rs);
    }

    @Test
    public void testCountByUsername() {
        int rs = mapper.countByUsername("%%");
        System.out.println(rs);
    }

    @Test
    public void testSelectByUsername() {
        Admin admin = mapper.selectByUsername("zhangsan");
        System.out.println(admin);
    }

    @Test
    public void testPageSelectByUsername() {
        List<Admin> admins = mapper.pageSelectByUsername(null, 0, 5);
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }
}
