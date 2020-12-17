package info.lostred.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    public static void downloadFile(HttpServletResponse response, String path) throws IOException {
        int idx = path.lastIndexOf("/");
        String name = path.substring(idx + 1);
        InputStream is = new FileInputStream(path);
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((name).getBytes(StandardCharsets.UTF_8), "ISO8859-1"));//下载文件的名称
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ServletOutputStream os = response.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        byte[] buff = new byte[2048];
        int bytesRead;
        while ((bytesRead = bis.read(buff, 0, buff.length)) > 0) {
            bos.write(buff, 0, bytesRead);
        }
        bos.flush();
        bos.close();
        bis.close();
    }
}