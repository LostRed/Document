package info.lostred.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userService = context.getBean(UserService.class);
    }

    @Test
    public void doRegister() {
    }

    @Test
    public void modifyUser() {
    }

    @Test
    public void doLogin() {
    }

    @Test
    public void pageFindUserByCondition() {
    }
}