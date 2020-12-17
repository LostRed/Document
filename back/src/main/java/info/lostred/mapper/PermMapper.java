package info.lostred.mapper;

import info.lostred.pojo.Perm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermMapper {
    int batchInsert(List<Perm> perms);

    int deleteByRoleId(@Param("roleId") int roleId);

    List<Perm> selectByRoleId(@Param("roleId") int roleId);
}
