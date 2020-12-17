package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.pojo.Edu;
import info.lostred.service.EduService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/edu")
public class EduController {
    @Resource
    private EduService eduService;

    @GetMapping("findAll")
    public String findAll() {
        List<Edu> list = eduService.findAllEdu();
        return Ajax.success(list);
    }
}
