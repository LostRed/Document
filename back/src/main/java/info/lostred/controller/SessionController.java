package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.pojo.Admin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {
    @GetMapping("loginAdmin")
    public String getLoginAdmin(HttpSession session) {
        Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
        Map<String, Object> map = new HashMap<>();
        map.put("loginAdmin", loginAdmin);
        return Ajax.success(map);
    }
}
