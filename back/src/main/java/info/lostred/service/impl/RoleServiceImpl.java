package info.lostred.service.impl;

import info.lostred.mapper.LogMapper;
import info.lostred.mapper.PermMapper;
import info.lostred.mapper.RoleMapper;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Log;
import info.lostred.pojo.Perm;
import info.lostred.pojo.Role;
import info.lostred.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermMapper permMapper;
    @Resource
    private LogMapper logMapper;

    @Override
    public int addRole(Role role, Admin actionAdmin) {
        int rs = roleMapper.insert(role);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("新增角色");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public int modifyRole(Role role, Admin actionAdmin) {
        int rs = roleMapper.update(role);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("修改角色");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public int deleteRole(Role role, Admin actionAdmin) {
        int rs = roleMapper.deleteById(role.getRoleId());
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("删除角色");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public int modifyPermByRoleId(int roleId, List<Integer> menuIds, Admin actionAdmin) {
        int rs = 0;
        rs += permMapper.deleteByRoleId(roleId);
        List<Perm> perms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            Perm perm = new Perm(null, roleId, menuId);
            perms.add(perm);
        }
        rs += permMapper.batchInsert(perms);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("修改权限");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Perm> findPermByRoleId(int roleId) {
        return permMapper.selectByRoleId(roleId);
    }
}
