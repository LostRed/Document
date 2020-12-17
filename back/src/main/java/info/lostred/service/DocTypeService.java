package info.lostred.service;

import info.lostred.pojo.Admin;
import info.lostred.pojo.DocType;

import java.util.List;

public interface DocTypeService {
    int addDocType(DocType docType, Admin actionAdmin);

    int modifyDocType(DocType docType, Admin actionAdmin);

    int deleteDocType(DocType docType, Admin actionAdmin);

    List<DocType> findAllDocType();
}
