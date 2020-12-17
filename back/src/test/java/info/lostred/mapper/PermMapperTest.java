package info.lostred.mapper;

import info.lostred.pojo.Perm;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PermMapperTest {
    private SqlSession sqlSession;
    private PermMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(PermMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testBatchInsert() {
        List<Perm> perms = new ArrayList<>();
        perms.add(new Perm(null, 1, 1));
        perms.add(new Perm(null, 1, 1));
        perms.add(new Perm(null, 1, 1));
        perms.add(new Perm(null, 1, 1));
        perms.add(new Perm(null, 1, 1));
        perms.add(new Perm(null, 1, 1));
        int rs = mapper.batchInsert(perms);
        System.out.println(rs);
    }

    @Test
    public void testDeleteByRoleId() {
        int rs = mapper.deleteByRoleId(999);
        System.out.println(rs);
    }

    @Test
    public void testSelectByRoleId() {
        List<Perm> perms = mapper.selectByRoleId(1);
        for (Perm perm : perms) {
            System.out.println(perm);
        }
    }
}
