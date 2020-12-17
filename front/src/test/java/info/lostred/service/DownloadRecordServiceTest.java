package info.lostred.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class DownloadRecordServiceTest {
    private DownloadRecordService downloadRecordService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        downloadRecordService = context.getBean(DownloadRecordService.class);
    }

    @Test
    public void pageFindDownloadRecordByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("startTime", null);
        conditions.put("endTime", null);
        conditions.put("userId", 1);
        Map<String, Object> map = downloadRecordService.pageFindDownloadRecordByCondition(conditions, 1, 5);
        for (String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }
    }

    @Test
    public void countDownloadRecordByUserIdAndDocId() {
        int rs = downloadRecordService.countDownloadRecordByUserIdAndDocId(17, 1);
        System.out.println(rs);
    }
}
