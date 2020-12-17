package info.lostred.service.impl;

import info.lostred.mapper.StateMapper;
import info.lostred.pojo.State;
import info.lostred.service.StateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StateServiceImpl implements StateService {
    @Resource
    private StateMapper stateMapper;

    @Override
    public List<State> findAllState() {
        return stateMapper.selectAll();
    }
}
