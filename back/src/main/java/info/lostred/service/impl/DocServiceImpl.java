package info.lostred.service.impl;

import info.lostred.bo.Pager;
import info.lostred.mapper.DocMapper;
import info.lostred.mapper.LogMapper;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Doc;
import info.lostred.pojo.Log;
import info.lostred.service.DocService;
import info.lostred.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocServiceImpl implements DocService {
    @Resource
    private DocMapper docMapper;
    @Resource
    private LogMapper logMapper;

    @Override
    public int modifyDoc(Doc doc, Admin actionAdmin) {
        int rs = docMapper.update(doc);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("修改文档");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public Doc findDocById(int docId) {
        return docMapper.selectById(docId);
    }

    @Override
    public Map<String, Object> pageFindDocByCondition(Map<String, Object> conditions, int page, int recPerPage, String orderField, String orderType) {
        Map<String, Object> map = new HashMap<>();
        if (conditions.get("docTitle") != null) {
            conditions.put("docTitle", "%" + conditions.get("docTitle") + "%");
        }
        if (conditions.get("username") != null) {
            conditions.put("username", "%" + conditions.get("username") + "%");
        }
        if (conditions.get("startTime") != null) {
            Date startTime = DateUtils.parse(conditions.get("startTime").toString());
            conditions.put("startTime", startTime);
        }
        if (conditions.get("endTime") != null) {
            Date endTime = DateUtils.parse(conditions.get("endTime").toString());
            conditions.put("endTime", endTime);
        }
        int recTotal = docMapper.countByDocTitle(conditions);
        Pager pager = new Pager(page, recPerPage, recTotal);
        List<Doc> list = docMapper.selectOrderByField(conditions, pager.getStart(), pager.getEnd(), orderField, orderType);
        map.put("recTotal", recTotal);
        map.put("list", list);
        return map;
    }
}
