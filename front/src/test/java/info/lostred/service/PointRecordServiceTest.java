package info.lostred.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class PointRecordServiceTest {
    private PointRecordService pointRecordService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        pointRecordService = context.getBean(PointRecordService.class);
    }

    @Test
    public void pageFindPointRecordByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("username", "zhangsan");
        Map<String, Object> map = pointRecordService.pageFindPointRecordByCondition(conditions, 1, 5);
        for (String s : map.keySet()) {
            System.out.println(s + ":" + map.get(s));
        }
    }
}