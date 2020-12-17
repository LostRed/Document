package info.lostred.service.impl;

import info.lostred.bo.Pager;
import info.lostred.mapper.AdminMapper;
import info.lostred.mapper.LogMapper;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Log;
import info.lostred.pojo.State;
import info.lostred.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private LogMapper logMapper;

    @Override
    public int addAdmin(Admin admin, Admin actionAdmin) {
        int rs = 0;
        if (adminMapper.selectByUsername(admin.getUsername()) == null) {
            admin.setState(new State(1, null, null));
            rs = adminMapper.insert(admin);
            Log log = new Log();
            log.setAdmin(actionAdmin);
            log.setOperation("新增管理员");
            log.setTime(new Date());
            logMapper.insert(log);
        }
        return rs;
    }

    @Override
    public int modifyAdmin(Admin admin, Admin actionAdmin) {
        int rs = adminMapper.update(admin);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("修改管理员");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public int deleteAdmin(Admin admin, Admin actionAdmin) {
        int rs = adminMapper.deleteById(admin.getAdminId());
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("删除管理员");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public Admin doLogin(String username) {
        return adminMapper.selectByUsername(username);
    }

    @Override
    public Map<String, Object> pageFindAdminByUsername(String username, int page, int recPerPage) {
        Map<String, Object> map = new HashMap<>();
        if (username != null) {
            username = "%" + username + "%";
        }
        int recTotal = adminMapper.countByUsername(username);
        Pager pager = new Pager(page, recPerPage, recTotal);
        List<Admin> list = adminMapper.pageSelectByUsername(username, pager.getStart(), pager.getEnd());
        map.put("recTotal", pager.getRecTotal());
        map.put("list", list);
        return map;
    }
}
