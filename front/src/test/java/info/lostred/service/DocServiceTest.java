package info.lostred.service;

import info.lostred.pojo.Doc;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class DocServiceTest {
    private DocService docService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        docService = context.getBean(DocService.class);
    }

    @Test
    public void findDocById() {
        Doc doc = docService.findDocById(1);
        System.out.println(doc);
    }

    @Test
    public void pageFindDocByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("username", "yi");
        Map<String, Object> map = docService.pageFindDocByCondition(conditions, 1, 5, "download_total", "desc");
        for (String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }
    }
}