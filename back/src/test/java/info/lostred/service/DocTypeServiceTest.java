package info.lostred.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DocTypeServiceTest {
    private DocService docService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        docService = context.getBean(DocService.class);
    }

    @Test
    public void addDocType() {
    }

    @Test
    public void modifyDocType() {
    }

    @Test
    public void deleteDocType() {
    }

    @Test
    public void findAllDocType() {
    }
}
