package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.pojo.Admin;
import info.lostred.pojo.DocType;
import info.lostred.pojo.State;
import info.lostred.service.DocTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/docType")
public class DocTypeController {
    @Resource
    private DocTypeService docTypeService;

    @PostMapping("/add")
    public String add(String typeName, String uploadPoint, HttpSession session) {
        if (typeName == null || "".equals(typeName)) {
            return Ajax.error("未提供文档类型名称！");
        }
        int iUploadPoint;
        try {
            iUploadPoint = Integer.parseInt(uploadPoint);
        } catch (NumberFormatException e) {
            return Ajax.error("上传奖励积分错误！");
        }
        DocType docType = new DocType(null, typeName, iUploadPoint, null);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = docTypeService.addDocType(docType, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "修改菜单失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("/modify")
    public String modify(String typeId, String typeName, String uploadPoint, HttpSession session) {
        int iTypeId;
        try {
            iTypeId = Integer.parseInt(typeId);
        } catch (NumberFormatException e) {
            return Ajax.error("修改文档类型失败！");
        }
        if (typeName == null || "".equals(typeName)) {
            return Ajax.error("未提供文档类型名称！");
        }
        int iUploadPoint;
        try {
            iUploadPoint = Integer.parseInt(uploadPoint);
        } catch (NumberFormatException e) {
            return Ajax.error("上传奖励积分错误！");
        }
        DocType docType = new DocType(iTypeId, typeName, iUploadPoint, null);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = docTypeService.modifyDocType(docType, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "修改菜单失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("/modifyState")
    public String modifyState(String typeId, HttpSession session) {
        int iTypeId;
        try {
            iTypeId = Integer.parseInt(typeId);
        } catch (NumberFormatException e) {
            return Ajax.error("禁用失败！");
        }
        DocType docType = new DocType(iTypeId, null, null, new State(8, null, null));
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = docTypeService.modifyDocType(docType, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "禁用失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("/delete")
    public String delete(String typeId, HttpSession session) throws IOException {
        int iTypeId;
        try {
            iTypeId = Integer.parseInt(typeId);
        } catch (NumberFormatException e) {
            return Ajax.error("删除失败！");
        }
        DocType docType = new DocType(iTypeId, null, null, null);
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = docTypeService.deleteDocType(docType, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "删除失败！");
        }
        return Ajax.success(num);
    }

    @GetMapping("/findAll")
    public String findAll() {
        List<DocType> docTypeList = docTypeService.findAllDocType();
        return Ajax.success(docTypeList);
    }
}
