package info.lostred.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import info.lostred.dto.Ajax;
import info.lostred.pojo.*;
import info.lostred.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("pageFindByCondition")
    public String pageFindByCondition(String page, String recPerPage, String conditions) {
        if (page == null || "".equals(page)) {
            return Ajax.error("当前页码为空！");
        }
        if (recPerPage == null || "".equals(recPerPage)) {
            return Ajax.error("每页数据条数为空！");
        }
        if (conditions == null || "".equals(conditions)) {
            return Ajax.error("条件数组为空！");
        }
        int iPage = Integer.parseInt(page);
        int iRecPerPage = Integer.parseInt(recPerPage);
        Map<String, Object> conditionMap = JSON.parseObject(conditions, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map = userService.pageFindUserByCondition(conditionMap, iPage, iRecPerPage);
        return Ajax.success(map);
    }

    @PostMapping("modify")
    public String modify(String userId, String name, String sex, String mobilePhone, String email, String eduId, String profId, HttpSession session) {
        int iUserId;
        try {
            iUserId = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            return Ajax.error(40001, "修改用户失败！");
        }
        int iEduId;
        try {
            iEduId = Integer.parseInt(eduId);
        } catch (NumberFormatException e) {
            return Ajax.error("学历选择错误！");
        }
        int iProfId;
        try {
            iProfId = Integer.parseInt(profId);
        } catch (NumberFormatException e) {
            return Ajax.error("职业选择错误！");
        }
        Edu edu = new Edu();
        edu.setEduId(iEduId);
        Prof prof = new Prof();
        prof.setProfId(iProfId);
        User user = new User();
        user.setUserId(iUserId);
        user.setName(name);
        user.setSex(sex);
        user.setMobilePhone(mobilePhone);
        user.setEmail(email);
        user.setEdu(edu);
        user.setProf(prof);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = userService.modifyUser(user, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "修改用户失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("delete")
    public String delete(String userId, HttpSession session) {
        User user = new User();
        int iUserId;
        try {
            iUserId = Integer.parseInt(userId);
            user.setUserId(iUserId);
        } catch (NumberFormatException e) {

            return Ajax.error(40001, "删除失败！");
        }
        user.setState(new State(6, null, null));
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = userService.modifyUser(user, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "删除失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("modifyState")
    public String modifyState(String userId, HttpSession session) {
        User user = new User();
        int iUserId;
        try {
            iUserId = Integer.parseInt(userId);
            user.setUserId(iUserId);
        } catch (NumberFormatException e) {
            return Ajax.error(40001, "禁用失败！");
        }
        user.setState(new State(5, null, null));
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = userService.modifyUser(user, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "禁用失败！");
        }
        return Ajax.success(num);
    }
}
