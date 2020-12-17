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

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/log")
    public String log() {
        return "log";
    }

    @GetMapping("/system")
    public String system() {
        return "system";
    }

    @GetMapping("/role")
    public String role() {
        return "role";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/perm")
    public String perm() {
        return "perm";
    }

    @GetMapping("/doc_audit")
    public String docAudit() {
        return "doc_audit";
    }

    @GetMapping("/doc_config")
    public String docConfig() {
        return "doc_config";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("loginAdmin", null);
        session.invalidate();
        return "redirect:/login";
    }
}
