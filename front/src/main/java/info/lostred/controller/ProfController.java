package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.pojo.Prof;
import info.lostred.service.ProfService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/prof")
public class ProfController {
    @Resource
    private ProfService profService;

    @GetMapping("findAll")
    public String findAll() {
        List<Prof> list = profService.findAllProf();
        return Ajax.success(list);
    }
}
