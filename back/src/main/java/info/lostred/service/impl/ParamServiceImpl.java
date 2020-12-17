package info.lostred.service.impl;

import info.lostred.mapper.LogMapper;
import info.lostred.mapper.ParamMapper;
import info.lostred.pojo.Admin;
import info.lostred.pojo.Log;
import info.lostred.pojo.Param;
import info.lostred.service.ParamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ParamServiceImpl implements ParamService {
    @Resource
    private ParamMapper paramMapper;
    @Resource
    private LogMapper logMapper;

    @Override
    public int modifyParam(Param param, Admin actionAdmin) {
        int rs;
        Param one = paramMapper.selectByParamName(param.getParamName());
        if (one == null) {
            rs = paramMapper.insert(param);
        } else {
            param.setParamId(one.getParamId());
            rs = paramMapper.update(param);
        }
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("修改参数");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }
}
