package info.lostred.service;

import info.lostred.factory.BeanFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LogServiceTest {
    private final LogService menuService = BeanFactory.getBean(LogService.class);

    @Test
    public void testPageFindLogByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("startTime", null);
        conditions.put("endTime", "2020-10-02T00:00:00.000Z");
        conditions.put("username", "zhangsan");
        Map<String, Object> map = menuService.pageFindLogByCondition(conditions, 1, 5);
        for (String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }
    }
}
