package info.lostred.service;

import info.lostred.pojo.Admin;
import info.lostred.pojo.Menu;

import java.util.List;

public interface MenuService {
    int addMenu(Menu menu, Admin actionAdmin);

    int modifyMenu(Menu menu, Admin actionAdmin);

    int deleteMenu(Menu menu, Admin actionAdmin);

    List<Menu> findAllMenu();

    List<Menu> findMenuByRoleId(int roleId);
}
