package info.lostred.service;

import info.lostred.pojo.Admin;
import info.lostred.pojo.Doc;

import java.util.Map;

public interface DocService {
    int modifyDoc(Doc doc, Admin actionAdmin);

    Doc findDocById(int docId);

    Map<String, Object> pageFindDocByCondition(Map<String, Object> conditions, int page, int recPerPage, String orderField, String orderType);
}
