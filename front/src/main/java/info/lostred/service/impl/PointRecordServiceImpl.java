package info.lostred.service.impl;

import info.lostred.bo.Pager;
import info.lostred.mapper.PointRecordMapper;
import info.lostred.pojo.PointRecord;
import info.lostred.service.PointRecordService;
import info.lostred.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PointRecordServiceImpl implements PointRecordService {
    @Resource
    private PointRecordMapper pointRecordMapper;

    @Override
    public Map<String, Object> pageFindPointRecordByCondition(Map<String, Object> conditions, int page, int recPerPage) {
        Map<String, Object> map = new HashMap<>();
        if (conditions.get("startTime") != null) {
            Date startTime = DateUtils.parse(conditions.get("startTime").toString());
            conditions.put("startTime", startTime);
        }
        if (conditions.get("endTime") != null) {
            Date endTime = DateUtils.parse(conditions.get("endTime").toString());
            conditions.put("endTime", endTime);
        }
        int recTotal = pointRecordMapper.countByCondition(conditions);
        Pager pager = new Pager(page, recPerPage, recTotal);
        List<PointRecord> list = pointRecordMapper.pageSelectByCondition(conditions, pager.getStart(), pager.getEnd());
        map.put("recTotal", recTotal);
        map.put("list", list);
        return map;
    }
}
