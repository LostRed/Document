package info.lostred.service.impl;

import info.lostred.bo.Pager;
import info.lostred.mapper.LogMapper;
import info.lostred.mapper.ParamMapper;
import info.lostred.mapper.UserMapper;
import info.lostred.pojo.*;
import info.lostred.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ParamMapper paramMapper;
    @Resource
    private LogMapper logMapper;

    @Override
    public int doRegister(User user) {
        int rs = 0;
        if (userMapper.selectByUsername(user.getUsername()) == null) {
            Param param = paramMapper.selectByParamName("注册奖励");
            user.setPoint(param.getParamValue());
            user.setState(new State(4, null, null));
            user.setRegTime(new Date());
            rs = userMapper.insert(user);
            Log log = new Log();
            log.setUser(user);
            log.setOperation("用户注册");
            log.setTime(new Date());
            logMapper.insert(log);
        }
        return rs;
    }

    @Override
    public int modifyUser(User user) {
        return modifyUser(user, null);
    }

    @Override
    public int modifyUser(User user, Admin actionAdmin) {
        int rs = userMapper.update(user);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("修改用户");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public User doLogin(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Map<String, Object> pageFindUserByCondition(Map<String, Object> conditions, int page, int recPerPage) {
        Map<String, Object> map = new HashMap<>();
        if (conditions.get("username") != null) {
            conditions.put("username", "%" + conditions.get("username") + "%");
        }
        if (conditions.get("mobilePhone") != null) {
            conditions.put("mobilePhone", "%" + conditions.get("mobilePhone") + "%");
        }
        int recTotal = userMapper.countByCondition(conditions);
        Pager pager = new Pager(page, recPerPage, recTotal);
        List<User> list = userMapper.pageSelectByCondition(conditions, pager.getStart(), pager.getEnd());
        map.put("recTotal", pager.getRecTotal());
        map.put("list", list);
        return map;
    }
}
