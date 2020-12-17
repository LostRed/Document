package info.lostred.mapper;

import info.lostred.pojo.Menu;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MenuMapperTest {
    private SqlSession sqlSession;
    private MenuMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(MenuMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        int rs = mapper.insert(new Menu(0, "test",0,null,""));
        System.out.println(rs);
    }

    @Test
    public void testUpdate() {
        int rs = mapper.update(new Menu(0, "test",0,"",""));
        System.out.println(rs);
    }

    @Test
    public void testDeleteById() {
        int rs = mapper.deleteById(0);
        System.out.println(rs);
    }

    @Test
    public void testSelectAll() {
        List<Menu> Menus = mapper.selectAll();
        for (Menu Menu : Menus) {
            System.out.println(Menu);
        }
    }

    @Test
    public void testSelectByRoleId() {
        List<Menu> Menus = mapper.selectByRoleId(1);
        for (Menu Menu : Menus) {
            System.out.println(Menu);
        }
    }
}
