package info.lostred.mapper;

import info.lostred.pojo.Doc;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocMapperTest {
    private SqlSession sqlSession;
    private DocMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(DocMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate() {
        Doc doc = new Doc();
        doc.setDocId(1);
        doc.setDocTitle("test");
        int rs = mapper.update(doc);
        System.out.println(rs);
    }

    @Test
    public void testSelectById() {
        Doc doc = mapper.selectById(1);
        System.out.println(doc);
        System.out.println(doc.getDocCover());
    }

    @Test
    public void testCountByDocTitle() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("docTitle", "%1%");
        int rs = mapper.countByDocTitle(conditions);
        System.out.println(rs);
    }

    @Test
    public void testSelectOrderByField() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("docTitle", "%1%");
        List<Doc> states = mapper.selectOrderByField(conditions, 0, 12, "download_total", "desc");
        for (Doc state : states) {
            System.out.println(state);
        }
    }
}
