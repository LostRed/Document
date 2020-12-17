package info.lostred.controller;


import info.lostred.dto.Ajax;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Role;
import info.lostred.pojo.State;
import info.lostred.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @GetMapping("pageFindByCondition")
    public String pageFindByCondition(String page, String recPerPage, String username) {
        if (page == null || "".equals(page)) {
            return Ajax.error("当前页码为空！");
        }
        if (recPerPage == null || "".equals(recPerPage)) {
            return Ajax.error("每页数据条数为空！");
        }
        if (username == null) {
            return Ajax.error("搜索用户名为空！");
        }
        int iPage = Integer.parseInt(page);
        int iRecPerPage = Integer.parseInt(recPerPage);
        Map<String, Object> map = adminService.pageFindAdminByUsername(username, iPage, iRecPerPage);
        return Ajax.success(map);
    }

    @PostMapping("/add")
    public String add(String username, String password, String name, String roleId, HttpSession session) {
        if (username == null || username.length() < 6) {
            return Ajax.error("用户名不能小于6位！");
        }
        if (password == null || password.length() < 6) {
            return Ajax.error("密码不能小于6位！");
        }
        if (name == null || name.length() < 2) {
            return Ajax.error("姓名不能小于2位！");
        }
        int iRoleId;
        try {
            iRoleId = Integer.parseInt(roleId);
        } catch (NumberFormatException e) {
            return Ajax.error("角色选择错误！");
        }
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setName(name);
        admin.setRole(new Role(iRoleId, null));
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = adminService.addAdmin(admin, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "新增管理员失败！用户名已存在。");
        }
        return Ajax.success(num);
    }

    @PostMapping("/modify")
    public String modify(String adminId, String name, String roleId, HttpSession session) {
        Admin admin = new Admin();
        int iAdminId;
        try {
            iAdminId = Integer.parseInt(adminId);
            admin.setAdminId(iAdminId);
        } catch (NumberFormatException e) {
            return Ajax.error(40001, "修改管理员失败！");
        }
        if (name != null) {
            if (name.length() < 2) {
                return Ajax.error("姓名不能小于2位！");
            }
            admin.setName(name);
        }
        int iRoleId;
        try {
            iRoleId = Integer.parseInt(roleId);
            admin.setRole(new Role(iRoleId, null));
        } catch (NumberFormatException ignored) {
        }
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = adminService.modifyAdmin(admin, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "修改管理员失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("/delete")
    public String delete(String adminId, HttpSession session) {
        Admin admin = new Admin();
        int iAdminId;
        try {
            iAdminId = Integer.parseInt(adminId);
            admin.setAdminId(iAdminId);
        } catch (NumberFormatException e) {
            return Ajax.error(40001, "删除失败！");
        }
        admin.setState(new State(3, null, null));
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = adminService.modifyAdmin(admin, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "删除失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("/modifyState")
    public String modifyState(String adminId, HttpSession session) {
        Admin admin = new Admin();
        int iAdminId;
        try {
            iAdminId = Integer.parseInt(adminId);
            admin.setAdminId(iAdminId);
        } catch (NumberFormatException e) {
            return Ajax.error(40001, "禁用失败！");
        }
        admin.setState(new State(2, null, null));
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = adminService.modifyAdmin(admin, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "禁用失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("/modifyPassword")
    public String modifyPassword(String adminId, HttpSession session) {
        Admin admin = new Admin();
        int iAdminId;
        try {
            iAdminId = Integer.parseInt(adminId);
            admin.setAdminId(iAdminId);
        } catch (NumberFormatException e) {
            return Ajax.error(40001, "重置失败！");
        }
        admin.setPassword("123456");
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = adminService.modifyAdmin(admin, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "重置失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("doLogin")
    public String doLogin(String username, String password, String captcha, HttpSession session) {
        if (captcha == null || !captcha.equalsIgnoreCase((String) session.getAttribute("captcha"))) {
            return Ajax.error(40001, "验证码错误！");
        }
        if (username == null || username.trim().length() < 6) {
            return Ajax.error("用户名不能小于6位！");
        }
        if (password == null || password.trim().length() < 6) {
            return Ajax.error("密码不能小于6位！");
        }
        Admin admin = adminService.doLogin(username);
        if (admin == null || !admin.getPassword().equals(password) || admin.getState().getStateId() == 3) {
            return Ajax.error(40002, "用户名或密码错误！");
        }
        if (admin.getState().getStateId() != 1) {
            return Ajax.error(40003, "用户状态异常，请联系管理员！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("location", "main");
        session.setAttribute("loginAdmin", admin);
        return Ajax.success(map);
    }
}
