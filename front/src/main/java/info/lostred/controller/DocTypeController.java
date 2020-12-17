package info.lostred.controller;

import info.lostred.dto.Ajax;
import info.lostred.pojo.DocType;
import info.lostred.service.DocTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/docType")
public class DocTypeController {
    @Resource
    private DocTypeService docTypeService;

    @GetMapping("/findAll")
    public String findAll() {
        List<DocType> docTypeList = docTypeService.findAllDocType();
        return Ajax.success(docTypeList);
    }
}
