package info.lostred.service;

import java.util.Map;

public interface PointRecordService {
    Map<String, Object> pageFindPointRecordByCondition(Map<String, Object> conditions, int page, int recPerPage);
}
