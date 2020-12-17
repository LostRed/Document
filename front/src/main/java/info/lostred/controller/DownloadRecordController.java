package info.lostred.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import info.lostred.dto.Ajax;
import info.lostred.service.DownloadRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/downloadRecord")
public class DownloadRecordController {
    @Resource
    private DownloadRecordService downloadRecordService;

    @GetMapping("pageFindByCondition")
    public String pageFindByCondition(String page, String recPerPage, String conditions) {
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
        Map<String, Object> map = downloadRecordService.pageFindDownloadRecordByCondition(conditionMap, iPage, iRecPerPage);
        return Ajax.success(map);
    }
}
