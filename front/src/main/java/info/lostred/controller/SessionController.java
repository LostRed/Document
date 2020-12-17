package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {
    @GetMapping("loginUser")
    public String getLoginAdmin(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>();
        map.put("loginUser", loginUser);
        return Ajax.success(map);
    }
}
