package info.lostred.service.impl;

import info.lostred.bo.Pager;
import info.lostred.mapper.LogMapper;
import info.lostred.pojo.Log;
import info.lostred.service.LogService;
import info.lostred.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {
    @Resource
    private LogMapper logMapper;

    @Override
    public Map<String, Object> pageFindLogByCondition(Map<String, Object> conditions, int page, int recPerPage) {
        Map<String, Object> map = new HashMap<>();
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
        int recTotal = logMapper.countByCondition(conditions);
        Pager pager = new Pager(page, recPerPage, recTotal);
        List<Log> list = logMapper.pageSelectByCondition(conditions, pager.getStart(), pager.getEnd());
        map.put("recTotal", pager.getRecTotal());
        map.put("list", list);
        return map;
    }
}
