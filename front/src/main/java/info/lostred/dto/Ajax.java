package info.lostred.dto;

import com.alibaba.fastjson.JSON;

public class Ajax {
    public static String error(String errMsg) {
        Resp resp = new Resp();
        resp.setErrCode(40000);
        resp.setErrMsg(errMsg);
        return JSON.toJSONString(resp);
    }

    public static String error(int errCode, String errMsg) {
        Resp resp = new Resp();
        resp.setErrCode(errCode);
        resp.setErrMsg(errMsg);
        return JSON.toJSONString(resp);
    }

    public static String success(Object data) {
        Resp resp = new Resp();
        resp.setErrCode(0);
        resp.setErrMsg("ok");
        resp.setData(data);
        return JSON.toJSONString(resp);
    }
}
