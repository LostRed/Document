package info.lostred.mapper;

import info.lostred.pojo.Edu;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EduMapperTest {
    private SqlSession sqlSession;
    private EduMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(EduMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        List<Edu> edus = mapper.selectAll();
        for (Edu edu : edus) {
            System.out.println(edu);
        }
    }
}
