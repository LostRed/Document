package info.lostred.service;

import info.lostred.factory.BeanFactory;
import info.lostred.pojo.Perm;
import info.lostred.pojo.Role;
import org.junit.Test;

import java.util.List;

public class RoleServiceTest {
    private final RoleService roleService = BeanFactory.getBean(RoleService.class);

    @Test
    public void testFindAllRole() {
        List<Role> allRole = roleService.findAllRole();
        for (Role Role : allRole) {
            System.out.println(Role);
        }
    }

    @Test
    public void testFindMenuByRoleId() {
        List<Perm> perms = roleService.findPermByRoleId(1);
        for (Perm perm : perms) {
            System.out.println(perm);
        }
    }
}

