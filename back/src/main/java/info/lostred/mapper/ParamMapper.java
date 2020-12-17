package info.lostred.mapper;

import info.lostred.pojo.Param;

public interface ParamMapper {
    int insert(Param param);

    int update(Param param);

    Param selectByParamName(@org.apache.ibatis.annotations.Param("paramName") String paramName);
}
