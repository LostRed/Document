package info.lostred.service.impl;

import info.lostred.mapper.*;
import info.lostred.pojo.*;
import info.lostred.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private DocMapper docMapper;
    @Resource
    private LogMapper logMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private PointRecordMapper pointRecordMapper;
    @Resource
    private DownloadRecordMapper downloadRecordMapper;

    @Override
    public int uploadFile(Doc doc, User user) {
        doc.setState(new State(9, null, null));
        doc.setUploadUser(user);
        doc.setUploadTime(new Date());
        doc.setDownloadTotal(0);
        int rs = docMapper.insert(doc);
        Log log = new Log();
        log.setUser(user);
        log.setOperation(user.getUsername() + "上传了《" + doc.getDocTitle() + "》");
        log.setTime(new Date());
        logMapper.insert(log);
        user.setPoint(user.getPoint() + doc.getDocType().getUploadPoint());
        userMapper.update(user);
        PointRecord pointRecord = new PointRecord();
        pointRecord.setUser(user);
        pointRecord.setName("上传文档");
        pointRecord.setPoint(doc.getDocType().getUploadPoint());
        pointRecord.setType("增加");
        pointRecord.setTime(new Date());
        pointRecordMapper.insert(pointRecord);
        return rs;
    }

    @Override
    public int downloadFile(Doc doc, User user) {
        int rs = downloadRecordMapper.countByUserIdAndDocId(doc.getDocId(), user.getUserId());
        if (rs == 0) {
            user = userMapper.selectByUsername(user.getUsername());
            if (user.getPoint() < doc.getDownloadPoint()) {
                return 0;
            }
            user.setPoint(user.getPoint() - doc.getDownloadPoint());
            userMapper.update(user);
        }
        DownloadRecord downloadRecord = new DownloadRecord();
        downloadRecord.setUser(user);
        downloadRecord.setDoc(doc);
        downloadRecord.setDownloadTime(new Date());
        downloadRecordMapper.insert(downloadRecord);
        Log log = new Log();
        log.setUser(user);
        log.setOperation(user.getUsername() + "下载了《" + doc.getDocTitle() + "》");
        log.setTime(new Date());
        logMapper.insert(log);
        return docMapper.updateDownloadTotal(doc);
    }
}
