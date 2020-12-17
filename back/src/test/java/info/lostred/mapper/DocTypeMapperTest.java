package info.lostred.mapper;

import info.lostred.pojo.DocType;
import info.lostred.pojo.State;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DocTypeMapperTest {
    private SqlSession sqlSession;
    private DocTypeMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(DocTypeMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        DocType docType = new DocType();
        docType.setTypeName("test");
        docType.setUploadPoint(20);
        docType.setState(new State(7, null, null));
        int rs = mapper.insert(docType);
        System.out.println(rs);
    }

    @Test
    public void testUpdate() {
        DocType docType = new DocType();
        docType.setTypeId(999);
        docType.setTypeName("test");
        docType.setUploadPoint(20);
        docType.setState(new State(7, null, null));
        int rs = mapper.insert(docType);
        System.out.println(rs);
    }

    @Test
    public void testDeleteById() {
        int rs = mapper.deleteById(999);
        System.out.println(rs);
    }

    @Test
    public void testSelectAll() {
        List<DocType> docTypes = mapper.selectAll();
        for (DocType docType : docTypes) {
            System.out.println(docType);
        }
    }
}
