package info.lostred.service;

import info.lostred.pojo.Doc;
import info.lostred.pojo.User;

public interface FileService {
    int uploadFile(Doc doc, User user);

    int downloadFile(Doc doc, User user);
}
