package info.lostred.service.impl;

import info.lostred.mapper.LogMapper;
import info.lostred.mapper.MenuMapper;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Log;
import info.lostred.pojo.Menu;
import info.lostred.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private LogMapper logMapper;

    @Override
    public int addMenu(Menu menu, Admin actionAdmin) {
        int rs = menuMapper.insert(menu);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("新增菜单");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public int modifyMenu(Menu menu, Admin actionAdmin) {
        int rs = menuMapper.update(menu);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("修改菜单");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public int deleteMenu(Menu menu, Admin actionAdmin) {
        int rs = menuMapper.deleteById(menu.getMenuId());
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("删除菜单");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.selectAll();
    }

    @Override
    public List<Menu> findMenuByRoleId(int roleId) {
        return menuMapper.selectByRoleId(roleId);
    }
}
