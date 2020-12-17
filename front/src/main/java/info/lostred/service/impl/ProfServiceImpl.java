package info.lostred.service.impl;

import info.lostred.mapper.ProfMapper;
import info.lostred.pojo.Prof;
import info.lostred.service.ProfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProfServiceImpl implements ProfService {
    @Resource
    private ProfMapper profMapper;

    @Override
    public List<Prof> findAllProf() {
        return profMapper.selectAll();
    }
}
