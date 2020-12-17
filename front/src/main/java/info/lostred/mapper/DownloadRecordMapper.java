package info.lostred.mapper;

import info.lostred.pojo.DownloadRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DownloadRecordMapper {
    int insert(DownloadRecord downloadRecord);

    int countByUserIdAndDocId(@Param("docId") int docId, @Param("userId") int userId);

    int countByCondition(@Param("conditions") Map<String, Object> conditions);

    List<DownloadRecord> pageSelectByCondition(@Param("conditions") Map<String, Object> conditions,
                                               @Param("start") Integer start,
                                               @Param("end") Integer end);
}
