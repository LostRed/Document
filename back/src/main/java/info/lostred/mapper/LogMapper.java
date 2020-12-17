package info.lostred.mapper;

import info.lostred.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LogMapper {
    int insert(Log log);

    int countByCondition(@Param("conditions") Map<String, Object> conditions);

    List<Log> pageSelectByCondition(@Param("conditions") Map<String, Object> conditions, @Param("start") Integer start, @Param("end") Integer end);
}
