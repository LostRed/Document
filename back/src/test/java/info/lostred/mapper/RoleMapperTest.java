package info.lostred.mapper;

import info.lostred.pojo.Role;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RoleMapperTest {
    private SqlSession sqlSession;
    private RoleMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(RoleMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        int rs = mapper.insert(new Role(0, "test"));
        System.out.println(rs);
    }

    @Test
    public void testUpdate() {
        int rs = mapper.update(new Role(0, "test"));
        System.out.println(rs);
    }

    @Test
    public void testDeleteById() {
        int rs = mapper.deleteById(0);
        System.out.println(rs);
    }

    @Test
    public void testSelectAll() {
        List<Role> roles = mapper.selectAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }
}
