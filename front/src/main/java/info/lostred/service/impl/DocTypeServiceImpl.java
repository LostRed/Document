package info.lostred.service.impl;

import info.lostred.mapper.DocTypeMapper;
import info.lostred.pojo.DocType;
import info.lostred.service.DocTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocTypeServiceImpl implements DocTypeService {
    @Resource
    private DocTypeMapper docTypeMapper;

    @Override
    public List<DocType> findAllDocType() {
        return docTypeMapper.selectAll();
    }
}
