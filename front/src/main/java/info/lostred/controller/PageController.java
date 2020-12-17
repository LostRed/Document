package info.lostred.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/doc_info")
    public String docInfo() {
        return "doc_info";
    }

    @GetMapping("/personal")
    public String personal() {
        return "personal";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("loginUser", null);
        session.invalidate();
        return "redirect:/login";
    }
}
