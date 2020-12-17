package info.lostred.service;

import info.lostred.pojo.Admin;
import info.lostred.pojo.Perm;
import info.lostred.pojo.Role;

import java.util.List;

public interface RoleService {
    int addRole(Role role, Admin actionAdmin);

    int modifyRole(Role role, Admin actionAdmin);

    int deleteRole(Role role, Admin actionAdmin);

    int modifyPermByRoleId(int roleId, List<Integer> menuIds, Admin actionAdmin);

    List<Role> findAllRole();

    List<Perm> findPermByRoleId(int roleId);
}
