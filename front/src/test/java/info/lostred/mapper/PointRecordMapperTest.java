package info.lostred.mapper;

import info.lostred.pojo.PointRecord;
import info.lostred.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointRecordMapperTest {
    private PointRecordMapper mapper;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        mapper = context.getBean(PointRecordMapper.class);
    }

    @Test
    public void insert() {
        PointRecord pointRecord = new PointRecord();
        User user = new User();
        user.setUserId(1);
        pointRecord.setUser(user);
        pointRecord.setName("上传文档");
        pointRecord.setPoint(30);
        pointRecord.setType("增加");
        pointRecord.setTime(new Date());
        int rs = mapper.insert(pointRecord);
        System.out.println(rs);
    }

    @Test
    public void testPageCountByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("userId", 1);
        int rs = mapper.countByCondition(conditions);
        System.out.println(rs);
    }

    @Test
    public void testPageSelectByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("userId", 1);
        conditions.put("type", "扣除");
        List<PointRecord> downloadRecords = mapper.pageSelectByCondition(conditions, 0, 5);
        for (PointRecord downloadRecord : downloadRecords) {
            System.out.println(downloadRecord);
        }
    }
}
