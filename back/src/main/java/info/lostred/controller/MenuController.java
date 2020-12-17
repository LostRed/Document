package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Menu;
import info.lostred.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @PostMapping("add")
    public String add(String menuName, String parentId, String iconClass, String url, HttpSession session) {
        if (menuName == null || "".equals(menuName)) {
            return Ajax.error("未提供菜单名称！");
        }
        int iParentId;
        try {
            iParentId = Integer.parseInt(parentId);
        } catch (NumberFormatException e) {
            return Ajax.error("父菜单选择错误！");
        }
        Menu menu = new Menu(null, menuName, iParentId, iconClass, url);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = menuService.addMenu(menu, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "新增菜单失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("modify")
    public String modify(String menuId, String menuName, String parentId, String iconClass, String url, HttpSession session) {
        int iMenuId;
        try {
            iMenuId = Integer.parseInt(menuId);
        } catch (NumberFormatException e) {
            return Ajax.error("修改菜单失败！");
        }
        if (menuName == null || "".equals(menuName)) {
            return Ajax.error("未提供菜单名称！");
        }
        int iParentId;
        try {
            iParentId = Integer.parseInt(parentId);
        } catch (NumberFormatException e) {
            return Ajax.error("父菜单选择错误！");
        }
        Menu menu = new Menu(iMenuId, menuName, iParentId, iconClass, url);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = menuService.modifyMenu(menu, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "修改菜单失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("delete")
    public String delete(String menuId, HttpSession session) {
        int iMenuId;
        try {
            iMenuId = Integer.parseInt(menuId);
        } catch (NumberFormatException e) {
            return Ajax.error("删除菜单失败！");
        }
        Menu menu = new Menu(iMenuId, null, null, null, null);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = menuService.deleteMenu(menu, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "删除菜单失败！");
        }
        return Ajax.success(num);
    }

    @GetMapping("findByRoleId")
    public String findByRoleId(String roleId) {
        int iRoleId;
        try {
            iRoleId = Integer.parseInt(roleId);
        } catch (NumberFormatException e) {
            return Ajax.error("角色选择错误！");
        }
        List<Menu> list = menuService.findMenuByRoleId(iRoleId);
        return Ajax.success(list);
    }

    @GetMapping("findAll")
    public String findAll() {
        List<Menu> menuList = menuService.findAllMenu();
        return Ajax.success(menuList);
    }
}
