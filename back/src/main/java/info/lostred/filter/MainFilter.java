package info.lostred.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String servletPath = ((HttpServletRequest) req).getServletPath();
        //放过静态资源和登录相关请求
        if (servletPath.startsWith("/static") || servletPath.startsWith("/dist")
                || servletPath.startsWith("/layer") || servletPath.startsWith("/captcha")
                || servletPath.startsWith("/login") || servletPath.endsWith(".html")
                || servletPath.equals("/") || servletPath.equals("/login.html")) {
            chain.doFilter(req, resp);
            return;
        }
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = ((HttpServletRequest) req).getSession();
        //判断是否登录
        if (session.getAttribute("loginAdmin") == null) {
            ((HttpServletResponse) resp).sendRedirect("login.html");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
