package info.lostred.mapper;

import info.lostred.pojo.State;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StateMapperTest {
    private SqlSession sqlSession;
    private StateMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(StateMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        List<State> states = mapper.selectAll();
        for (State state : states) {
            System.out.println(state);
        }
    }
}
