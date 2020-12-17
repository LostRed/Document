package info.lostred.service.impl;

import info.lostred.mapper.DocTypeMapper;
import info.lostred.mapper.LogMapper;
import info.lostred.pojo.Admin;
import info.lostred.pojo.DocType;
import info.lostred.pojo.Log;
import info.lostred.pojo.State;
import info.lostred.service.DocTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DocTypeServiceImpl implements DocTypeService {
    @Resource
    private DocTypeMapper docTypeMapper;
    @Resource
    private LogMapper logMapper;

    @Override
    public int addDocType(DocType docType, Admin actionAdmin) {
        docType.setState(new State(7, null, null));
        int rs = docTypeMapper.insert(docType);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("新增文档类型");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public int modifyDocType(DocType docType, Admin actionAdmin) {
        int rs = docTypeMapper.update(docType);
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("修改文档类型");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public int deleteDocType(DocType docType, Admin actionAdmin) {
        int rs = docTypeMapper.deleteById(docType.getTypeId());
        Log log = new Log();
        log.setAdmin(actionAdmin);
        log.setOperation("删除文档类型");
        log.setTime(new Date());
        logMapper.insert(log);
        return rs;
    }

    @Override
    public List<DocType> findAllDocType() {
        return docTypeMapper.selectAll();
    }
}
