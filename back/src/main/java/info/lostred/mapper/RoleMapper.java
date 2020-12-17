package info.lostred.mapper;

import info.lostred.pojo.Role;

import java.util.List;

public interface RoleMapper {
    int insert(Role role);

    int update(Role role);

    int deleteById(int roleId);

    List<Role> selectAll();
}
