package info.lostred.mapper;

import info.lostred.pojo.Log;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class LogMapperTest {
    private SqlSession sqlSession;
    private LogMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(LogMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        Log log = new Log();
        log.setAdmin(null);
        log.setUser(null);
        log.setOperation("test");
        log.setTime(new Date());
        System.out.println(log);
        int rs = mapper.insert(log);
        System.out.println(rs);
    }
}
