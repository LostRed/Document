package info.lostred.mapper;

import info.lostred.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int insert(Admin admin);

    int update(Admin admin);

    int deleteById(@Param("adminId") Integer adminId);

    int countByUsername(@Param("username") String username);

    Admin selectByUsername(@Param("username") String username);

    List<Admin> pageSelectByUsername(@Param("username") String username, @Param("start") Integer start, @Param("end") Integer end);
}
