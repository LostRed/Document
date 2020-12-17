package info.lostred.service;

import info.lostred.pojo.Edu;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class EduServiceTest {
    private EduService eduService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        eduService = context.getBean(EduService.class);
    }

    @Test
    public void selectAllEdu() {
        List<Edu> edus = eduService.findAllEdu();
        for (Edu edu : edus) {
            System.out.println(edu);
        }
    }
}