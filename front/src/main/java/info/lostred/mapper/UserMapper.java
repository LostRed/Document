package info.lostred.mapper;

import info.lostred.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int insert(User user);

    int update(User user);

    int countByCondition(@Param("conditions") Map<String, Object> conditions);

    User selectByUsername(@Param("username") String username);

    List<User> pageSelectByCondition(@Param("conditions") Map<String, Object> conditions, @Param("start") Integer start, @Param("end") Integer end);
}
