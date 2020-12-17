package info.lostred.service;

import java.util.Map;

public interface DownloadRecordService {
    int countDownloadRecordByUserIdAndDocId(int docId, int userId);

    Map<String, Object> pageFindDownloadRecordByCondition(Map<String, Object> conditions, int page, int recPerPage);
}
