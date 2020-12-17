package info.lostred.mapper;

import info.lostred.pojo.DownloadRecord;
import info.lostred.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadRecordMapperTest {
    private SqlSession sqlSession;
    private DownloadRecordMapper mapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(DownloadRecordMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testPageCountByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("userId", 1);
        conditions.put("docTypeId", 1);
        int rs = mapper.countByCondition(conditions);
        System.out.println(rs);
    }

    @Test
    public void testPageSelectByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("userId", 1);
        List<DownloadRecord> downloadRecords = mapper.pageSelectByCondition(conditions, 0, 5);
        for (DownloadRecord downloadRecord : downloadRecords) {
            System.out.println(downloadRecord);
        }
    }
}
