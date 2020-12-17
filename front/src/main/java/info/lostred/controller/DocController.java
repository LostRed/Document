package info.lostred.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import info.lostred.dto.Ajax;
import info.lostred.pojo.Doc;
import info.lostred.pojo.DocType;
import info.lostred.pojo.User;
import info.lostred.service.*;
import info.lostred.utils.FileUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/doc")
public class DocController {
    @Resource
    private UserService userService;
    @Resource
    private DocService docService;
    @Resource
    private FileService fileService;
    @Resource
    private DocTypeService docTypeService;
    @Resource
    private DownloadRecordService downloadRecordService;

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

    @GetMapping("/findById")
    public String findById(String docId) {
        int iDocId;
        try {
            iDocId = Integer.parseInt(docId);
        } catch (NumberFormatException e) {
            return Ajax.error("文档id错误！");
        }
        Doc doc = docService.findDocById(iDocId);
        return Ajax.success(doc);
    }

    @GetMapping("/findDownloadRecordByUserIdAndDocId")
    public String findDownloadRecordByUserIdAndDocId(int docId, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        Doc doc = docService.findDocById(docId);
        if (user == null) {
            return Ajax.success(null);
        }
        user = userService.doLogin(user.getUsername());
        int num = downloadRecordService.countDownloadRecordByUserIdAndDocId(docId, user.getUserId());
        if (num == 0) {
            if (doc.getDownloadPoint() > user.getPoint()) {
                return Ajax.error(40001, "该文档需要扣除" + doc.getDownloadPoint() + "点积分，您的积分不足！");
            }
            return Ajax.error("下载该文档需要扣除" + doc.getDownloadPoint() + "点积分，确定下载吗？");
        }
        return Ajax.success(null);
    }


    @PostMapping("/uploadFile")
    public String uploadFile(Doc doc, MultipartFile cover, MultipartFile document, HttpSession session) throws IOException {
        //获取保存的目录
        String savePath = session.getServletContext().getRealPath("/uploads/");
        String subPath = "";
        Calendar calendar = Calendar.getInstance();
        subPath += calendar.get(Calendar.YEAR) + "/";
        subPath += calendar.get(Calendar.MONTH) + 1 + "/";
        subPath += calendar.get(Calendar.DATE) + "/";
        //判断一下有没有这个文件夹
        File file = new File(savePath + subPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //封面
        if (cover == null) {
            return Ajax.error("未上传文档封面！");
        }
        String coverName = cover.getOriginalFilename();
        if (coverName == null) {
            return Ajax.error("未上传文档封面！");
        }
        String[] names = coverName.split("\\.");
        String suffix = names[names.length - 1];
        if (!suffix.matches("jpg|jpeg|gif|png|bmp")) {
            return Ajax.error("请上传图片格式！");
        }
        String uuid = UUID.randomUUID().toString();
        String saverCoverName = uuid + "." + suffix;
        doc.setDocCover(session.getServletContext().getContextPath() + "/uploads/" + subPath + saverCoverName);
        //文件
        if (document == null) {
            return Ajax.error("未上传文件！");
        }
        String documentName = document.getOriginalFilename();
        if (documentName == null) {
            return Ajax.error("未上传文件！");
        }
        names = documentName.split("\\.");
        suffix = names[names.length - 1];
        List<DocType> allDocType = docTypeService.findAllDocType();
        boolean flag = false;
        for (DocType docType : allDocType) {
            if (docType.getTypeName().equals(suffix)) {
                doc.setDocType(docType);
                flag = true;
                break;
            }
        }
        if (!flag) {
            return Ajax.error("请上传正确的文档格式！");
        }
        uuid = UUID.randomUUID().toString();
        String saverDocumentName = uuid + "." + suffix;
        doc.setDocUrl("/uploads/" + subPath + saverDocumentName);
        User user = (User) session.getAttribute("loginUser");
        int num = fileService.uploadFile(doc, user);
        if (num == 0) {
            return Ajax.error(40001, "文件上传错误！");
        }
        cover.transferTo(new File(savePath + subPath + saverCoverName));
        document.transferTo(new File(savePath + subPath + saverDocumentName));
        return Ajax.success(num);
    }

    @GetMapping("/downloadFile")
    public String downloadFile(HttpServletResponse response, HttpSession session, int docId) {
        User user = (User) session.getAttribute("loginUser");
        Doc doc = docService.findDocById(docId);
        int num = fileService.downloadFile(doc, user);
        if (num > 0) {
            String path = session.getServletContext().getRealPath("") + doc.getDocUrl();
            try {
                FileUtil.downloadFile(response, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return Ajax.error(40001, "该文档需要扣除" + doc.getDownloadPoint() + "点积分，您的积分不足！");
        }
        return null;
    }
}
