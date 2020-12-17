package info.lostred.service;

import info.lostred.pojo.Prof;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ProfServiceTest {
    private ProfService profService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        profService = context.getBean(ProfService.class);
    }

    @Test
    public void selectAllProf() {
        List<Prof> profs = profService.findAllProf();
        for (Prof prof : profs) {
            System.out.println(prof);
        }
    }
}