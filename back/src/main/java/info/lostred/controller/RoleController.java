package info.lostred.controller;

import com.alibaba.fastjson.JSON;
import info.lostred.dto.Ajax;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Perm;
import info.lostred.pojo.Role;
import info.lostred.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("add")
    public String add(String roleName, HttpSession session) {
        if (roleName == null || "".equals(roleName)) {
            return Ajax.error("未提供角色名称！");
        }
        Role role = new Role(null, roleName);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = roleService.addRole(role, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "新增角色失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("modify")
    public String modify(String roleId, String roleName, HttpSession session) {
        int iRoleId;
        try {
            iRoleId = Integer.parseInt(roleId);
        } catch (NumberFormatException e) {
            return Ajax.error("修改角色失败！");
        }
        if (roleName == null || "".equals(roleName)) {
            return Ajax.error("未提供角色名称！");
        }
        Role role = new Role(iRoleId, roleName);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = roleService.modifyRole(role, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "修改角色失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("delete")
    public String delete(String roleId, HttpSession session) {
        int iRoleId;
        try {
            iRoleId = Integer.parseInt(roleId);
        } catch (NumberFormatException e) {
            return Ajax.error("删除角色失败！");
        }
        Role role = new Role(iRoleId, null);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = roleService.deleteRole(role, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "删除角色失败！");
        }
        return Ajax.success(num);
    }

    @GetMapping("findAll")
    public String findAll() {
        List<Role> list = roleService.findAllRole();
        return Ajax.success(list);
    }

    @GetMapping("findPermByRoleId")
    public String findPermByRoleId(String roleId) {
        int iRoleId;
        try {
            iRoleId = Integer.parseInt(roleId);
        } catch (NumberFormatException e) {
            return Ajax.error("角色选择错误！");
        }
        List<Perm> list = roleService.findPermByRoleId(iRoleId);
        return Ajax.success(list);
    }

    @PostMapping("modifyPermByRoleId")
    public String modifyPermByRoleId(String roleId, String menuIds, HttpSession session) {
        int iRoleId;
        try {
            iRoleId = Integer.parseInt(roleId);
        } catch (NumberFormatException e) {
            return Ajax.error("角色选择错误！");
        }
        List<Integer> menuIdList = JSON.parseArray(menuIds, Integer.class);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = roleService.modifyPermByRoleId(iRoleId, menuIdList, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "保存失败！");
        }
        return Ajax.success(num);
    }
}
