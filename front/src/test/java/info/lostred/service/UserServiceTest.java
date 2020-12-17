package info.lostred.service;

import info.lostred.pojo.Admin;
import info.lostred.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userService = context.getBean(UserService.class);
    }

    @Test
    public void doRegister() {
        User user = new User();
        user.setName("test");
        user.setPoint(50);
        int rs = userService.doRegister(user);
        System.out.println(rs);
    }

    @Test
    public void modifyUser() {
        User user = new User();
        user.setUserId(1);
        user.setPoint(50);
        int rs = userService.modifyUser(user);
        System.out.println(rs);
    }

    @Test
    public void testModifyUser() {
        User user = new User();
        user.setUserId(1);
        user.setPoint(50);
        Admin admin = new Admin();
        admin.setAdminId(1);
        int rs = userService.modifyUser(user, admin);
        System.out.println(rs);
    }

    @Test
    public void doLogin() {
        User user = userService.doLogin("yiwanning");
        System.out.println(user);
    }

    @Test
    public void pageFindUserByCondition() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("username", "yi");
        Map<String, Object> map = userService.pageFindUserByCondition(conditions, 1, 5);
        for (String s : map.keySet()) {
            System.out.println(s + ":" + map.get(s));
        }
    }
}