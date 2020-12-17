package info.lostred.service;

import info.lostred.pojo.Menu;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MenuServiceTest {
    private MenuService menuService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        menuService = context.getBean(MenuService.class);
    }

    @Test
    public void addMenu() {
        int rs = menuService.addMenu(new Menu(0, "test", 0, "test", "test"), null);
        System.out.println(rs);
    }

    @Test
    public void modifyMenu() {
    }

    @Test
    public void deleteMenu() {
    }

    @Test
    public void findAllMenu() {
        List<Menu> allMenu = menuService.findAllMenu();
        for (Menu menu : allMenu) {
            System.out.println(menu);
        }
    }

    @Test
    public void findMenuByRoleId() {
        List<Menu> allMenu = menuService.findMenuByRoleId(1);
        for (Menu menu : allMenu) {
            System.out.println(menu);
        }
    }
}
