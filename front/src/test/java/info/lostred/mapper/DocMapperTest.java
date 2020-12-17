package info.lostred.mapper;

import info.lostred.pojo.Doc;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocMapperTest {
    private DocMapper mapper;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        mapper = context.getBean(DocMapper.class);
    }

    @Test
    public void insert() {
        Doc doc = new Doc();
        int rs = mapper.insert(doc);
        System.out.println(rs);
    }

    @Test
    public void selectById() {
        Doc doc = mapper.selectById(1);
        System.out.println(doc);
        System.out.println(doc.getDocCover());
    }

    @Test
    public void countByDocTitle() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("docTitle", "%1%");
        int rs = mapper.countByDocTitle(conditions);
        System.out.println(rs);
    }

    @Test
    public void selectOrderByField() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("docTitle", "%1%");
        List<Doc> states = mapper.selectOrderByField(conditions, 0, 12, "download_total", "desc");
        for (Doc state : states) {
            System.out.println(state);
        }
    }
}
