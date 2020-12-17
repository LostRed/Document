package info.lostred.service;

import info.lostred.pojo.Admin;

import java.util.Map;

public interface AdminService {
    int addAdmin(Admin admin, Admin actionAdmin);

    int modifyAdmin(Admin admin, Admin actionAdmin);

    int deleteAdmin(Admin admin, Admin actionAdmin);

    Admin doLogin(String username);

    Map<String, Object> pageFindAdminByUsername(String username, int page, int recPerPage);
}
