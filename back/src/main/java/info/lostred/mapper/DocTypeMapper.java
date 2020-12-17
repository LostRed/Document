package info.lostred.mapper;

import info.lostred.pojo.DocType;

import java.util.List;

public interface DocTypeMapper {
    int insert(DocType docType);

    int update(DocType docType);

    int deleteById(int typeId);

    List<DocType> selectAll();
}
