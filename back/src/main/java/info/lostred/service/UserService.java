package info.lostred.service;

import info.lostred.pojo.Admin;
import info.lostred.pojo.User;

import java.util.Map;

public interface UserService {
    int doRegister(User user);

    int modifyUser(User user, Admin actionAdmin);

    User doLogin(String username);

    Map<String, Object> pageFindUserByCondition(Map<String, Object> conditions, int page, int recPerPage);
}
