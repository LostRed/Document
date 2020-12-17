package info.lostred.mapper;

import info.lostred.pojo.Param;

public interface ParamMapper {
    Param selectByParamName(@org.apache.ibatis.annotations.Param("paramName") String paramName);

    int insert(Param param);

    int update(Param param);
}
