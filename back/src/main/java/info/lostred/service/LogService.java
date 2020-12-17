package info.lostred.service;

import java.util.Map;

public interface LogService {
    Map<String, Object> pageFindLogByCondition(Map<String, Object> conditions, int page, int recPerPage);
}
