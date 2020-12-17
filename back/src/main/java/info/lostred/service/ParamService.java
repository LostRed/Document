package info.lostred.service;

import info.lostred.pojo.Admin;
import info.lostred.pojo.Param;

public interface ParamService {
    int modifyParam(Param param, Admin actionAdmin);
}
