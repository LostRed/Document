package info.lostred.service.impl;

import info.lostred.mapper.EduMapper;
import info.lostred.pojo.Edu;
import info.lostred.service.EduService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EduServiceImpl implements EduService {
    @Resource
    private EduMapper eduMapper;

    @Override
    public List<Edu> findAllEdu() {
        return eduMapper.selectAll();
    }
}
