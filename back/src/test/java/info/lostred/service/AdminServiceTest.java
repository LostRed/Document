package info.lostred.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class AdminServiceTest {
    private AdminService adminService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        adminService = context.getBean(AdminService.class);
    }

    @Test
    public void addAdmin() {
    }

    @Test
    public void modifyAdmin() {
    }

    @Test
    public void deleteAdmin() {
    }

    @Test
    public void doLogin() {
    }

    @Test
    public void pageFindAdminByUsername() {
        Map<String, Object> map = adminService.pageFindAdminByUsername(null, 1, 5);
        for (String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }
    }
}