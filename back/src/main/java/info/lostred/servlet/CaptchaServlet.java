package info.lostred.servlet;

import info.lostred.utils.CaptchaUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CaptchaUtils captchaUtils = new CaptchaUtils(70, 35, 4);//用我们的验证码类，生成验证码类对象
        BufferedImage image = captchaUtils.getImage();//获取验证码
        req.getSession().setAttribute("captcha", captchaUtils.getCode());//将验证码的文本存在session中
        captchaUtils.output(image, resp.getOutputStream());//将验证码图片响应给客户端
    }
}
