package info.lostred.mapper;

import info.lostred.pojo.Prof;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProfMapperTest {
    private SqlSession sqlSession;
    private ProfMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(ProfMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        List<Prof> profs = mapper.selectAll();
        for (Prof prof : profs) {
            System.out.println(prof);
        }
    }
}
