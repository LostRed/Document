package info.lostred.service;

import info.lostred.pojo.State;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class StateServiceTest {
    private StateService stateService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        stateService = context.getBean(StateService.class);
    }

    @Test
    public void findAllState() {
        List<State> allState = stateService.findAllState();
        for (State state : allState) {
            System.out.println(state);
        }
    }
}