package info.lostred.mapper;

import info.lostred.pojo.Param;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParamMapperTest {
    private SqlSession sqlSession;
    private ParamMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(ParamMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        Param param = new Param();
        param.setParamName("test");
        param.setParamValue(1);
        int rs = mapper.insert(param);
        System.out.println(rs);
    }

    @Test
    public void testUpdate() {
        Param param = new Param();
        param.setParamId(999);
        param.setParamName("test");
        param.setParamValue(1);
        int rs = mapper.insert(param);
        System.out.println(rs);
    }

    @Test
    public void testSelectByParamName() {
        Param param = mapper.selectByParamName("注册奖励");
        System.out.println(param);
    }
}
