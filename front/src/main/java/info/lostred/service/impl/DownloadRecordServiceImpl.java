package info.lostred.service.impl;

import info.lostred.bo.Pager;
import info.lostred.mapper.DownloadRecordMapper;
import info.lostred.pojo.DownloadRecord;
import info.lostred.service.DownloadRecordService;
import info.lostred.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DownloadRecordServiceImpl implements DownloadRecordService {
    @Resource
    private DownloadRecordMapper downloadRecordMapper;

    @Override
    public int countDownloadRecordByUserIdAndDocId(int docId, int userId) {
        return downloadRecordMapper.countByUserIdAndDocId(docId, userId);
    }

    @Override
    public Map<String, Object> pageFindDownloadRecordByCondition(Map<String, Object> conditions, int page, int recPerPage) {
        Map<String, Object> map = new HashMap<>();
        if (conditions.get("docTitle") != null) {
            conditions.put("docTitle", "%" + conditions.get("docTitle") + "%");
        }
        if (conditions.get("startTime") != null) {
            Date startTime = DateUtils.parse(conditions.get("startTime").toString());
            conditions.put("startTime", startTime);
        }
        if (conditions.get("endTime") != null) {
            Date endTime = DateUtils.parse(conditions.get("endTime").toString());
            conditions.put("endTime", endTime);
        }
        int recTotal = downloadRecordMapper.countByCondition(conditions);
        Pager pager = new Pager(page, recPerPage, recTotal);
        List<DownloadRecord> list = downloadRecordMapper.pageSelectByCondition(conditions, pager.getStart(), pager.getEnd());
        map.put("recTotal", recTotal);
        map.put("list", list);
        return map;
    }
}
