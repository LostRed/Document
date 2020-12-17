package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Param;
import info.lostred.service.ParamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/param")
public class ParamController {
    @Resource
    private ParamService paramService;

    @PostMapping("modify")
    public String modify(String paramName, String paramValue, HttpSession session) {
        int iParamValue;
        try {
            iParamValue = Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return Ajax.error("参数值必须是数字！");
        }
        Param param = new Param(null, paramName, iParamValue);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = paramService.modifyParam(param, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "修改菜单失败！");
        }
        return Ajax.success(num);
    }
}
