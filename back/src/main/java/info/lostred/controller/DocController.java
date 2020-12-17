package info.lostred.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import info.lostred.dto.Ajax;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Doc;
import info.lostred.pojo.State;
import info.lostred.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/doc")
public class DocController {
    @Resource
    private DocService docService;

    @GetMapping("/pageFindByCondition")
    public String pageFindByCondition(String page, String recPerPage, String conditions, String orderField, String orderType) {
        if (page == null || "".equals(page)) {
            return Ajax.error("当前页码为空！");
        }
        if (recPerPage == null || "".equals(recPerPage)) {
            return Ajax.error("每页数据条数为空！");
        }
        if (conditions == null || "".equals(conditions)) {
            return Ajax.error("条件数组为空！");
        }
        int iPage = Integer.parseInt(page);
        int iRecPerPage = Integer.parseInt(recPerPage);
        Map<String, Object> conditionMap = JSON.parseObject(conditions, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map = docService.pageFindDocByCondition(conditionMap, iPage, iRecPerPage, orderField, orderType);
        return Ajax.success(map);
    }

    @PostMapping("/pass")
    public String pass(String docId, HttpSession session) {
        Doc doc = new Doc();
        int iDocId;
        try {
            iDocId = Integer.parseInt(docId);
            doc.setDocId(iDocId);
        } catch (NumberFormatException e) {
            return Ajax.error(40001, "通过失败！");
        }
        doc.setState(new State(10, null, null));
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = docService.modifyDoc(doc, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "通过失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("/unPass")
    public String unPass(String docId, HttpSession session) {
        Doc doc = new Doc();
        int iDocId;
        try {
            iDocId = Integer.parseInt(docId);
            doc.setDocId(iDocId);
        } catch (NumberFormatException e) {
            return Ajax.error(40001, "退回失败！");
        }
        doc.setState(new State(9, null, null));
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = docService.modifyDoc(doc, actionAdmin);
        if (num == 0) {

            return Ajax.error(40001, "退回失败！");
        }
        return Ajax.success(num);
    }

    @PostMapping("/delete")
    public String delete(String docId, HttpSession session) {
        Doc doc = new Doc();
        int iDocId;
        try {
            iDocId = Integer.parseInt(docId);
            doc.setDocId(iDocId);
        } catch (NumberFormatException e) {
            return Ajax.error(40001, "删除失败！");
        }
        doc.setState(new State(11, null, null));
        Admin actionAdmin = (Admin) session.getAttribute("loginAdmin");
        int num = docService.modifyDoc(doc, actionAdmin);
        if (num == 0) {
            return Ajax.error(40001, "删除失败！");
        }
        return Ajax.success(num);
    }
}
