package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.pojo.State;
import info.lostred.service.StateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/state")
public class StateController {
    @Resource
    private StateService stateService;

    @GetMapping("findAll")
    public String findAll() {
        List<State> list = stateService.findAllState();
        return Ajax.success(list);
    }
}
