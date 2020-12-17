package info.lostred.mapper;

import info.lostred.pojo.PointRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PointRecordMapper {
    int insert(PointRecord pointRecord);

    int countByCondition(@Param("conditions") Map<String, Object> conditions);

    List<PointRecord> pageSelectByCondition(@Param("conditions") Map<String, Object> conditions,
                                            @Param("start") Integer start,
                                            @Param("end") Integer end);
}
