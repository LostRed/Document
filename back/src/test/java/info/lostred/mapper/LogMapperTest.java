package info.lostred.mapper;

import info.lostred.pojo.Log;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testCountByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("startTime", null);
        conditions.put("endTime", new Date());
        conditions.put("username", "%x%");
        int rs = mapper.countByCondition(conditions);
        System.out.println(rs);
    }

    @Test
    public void testPageSelectByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("startTime", new Date());
        conditions.put("endTime", null);
        conditions.put("username", "%z%");
        List<Log> logs = mapper.pageSelectByCondition(conditions, 0, 5);
        for (Log log : logs) {
            System.out.println(log);
        }
    }
}
