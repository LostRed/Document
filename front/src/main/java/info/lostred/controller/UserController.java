package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.factory.BeanFactory;
import info.lostred.pojo.Edu;
import info.lostred.pojo.Prof;
import info.lostred.pojo.User;
import info.lostred.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("modify")
    public String modify(String name, String sex, String mobilePhone, String email, String eduId, String profId, HttpSession session) {
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
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setName(name);
        loginUser.setSex(sex);
        loginUser.setMobilePhone(mobilePhone);
        loginUser.setEmail(email);
        loginUser.setEdu(edu);
        loginUser.setProf(prof);
        int num = userService.modifyUser(loginUser);
        if (num == 0) {
            return Ajax.error(40001, "修改用户失败！");
        }
        loginUser = userService.doLogin(loginUser.getUsername());
        session.setAttribute("loginUser", loginUser);
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
        UserService userService = BeanFactory.getBean(UserService.class);
        User user = userService.doLogin(username);
        if (user == null || !user.getPassword().equals(password) || user.getState().getStateId() == 6) {
            return Ajax.error(40002, "用户名或密码错误！");
        }
        if (user.getState().getStateId() != 4) {
            return Ajax.error(40003, "用户状态异常，请联系管理员！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("location", "main");
        session.setAttribute("loginUser", user);
        return Ajax.success(map);
    }

    @PostMapping("doRegister")
    public String doRegister(String username, String password, String sex, String mobilePhone, String email, String eduId, String profId) {
        if (username == null || username.length() < 6) {
            return Ajax.error("用户名不能小于6位！");
        }
        if (password == null || password.length() < 6) {
            return Ajax.error("密码不能小于6位！");
        }
        if (mobilePhone == null || mobilePhone.length() != 11) {
            return Ajax.error("手机号格式错误！");
        }
        if (email == null || email.equals("")) {
            return Ajax.error("邮箱不能为空！");
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
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setMobilePhone(mobilePhone);
        user.setEmail(email);
        user.setEdu(new Edu(iEduId, null));
        user.setProf(new Prof(iProfId, null));
        UserService userService = BeanFactory.getBean(UserService.class);
        int num = userService.doRegister(user);
        if (num == 0) {
            return Ajax.error(40001, "该用户名已存在！");
        }
        return Ajax.success(num);
    }
}
