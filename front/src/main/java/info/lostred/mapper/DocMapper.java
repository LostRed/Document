package info.lostred.mapper;

import info.lostred.pojo.Doc;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DocMapper {
    int insert(Doc doc);

    int updateDownloadTotal(Doc doc);

    Doc selectById(@Param("docId") int docId);

    int countByDocTitle(@Param("conditions") Map<String, Object> conditions);

    List<Doc> selectOrderByField(@Param("conditions") Map<String, Object> conditions,
                                 @Param("start") Integer start,
                                 @Param("end") Integer end,
                                 @Param("orderField") String orderField,
                                 @Param("orderType") String orderType);
}
