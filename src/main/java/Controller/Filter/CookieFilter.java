package Controller.Filter;

import Model.Role;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class CookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);
        int visitCount = 0;
        if (!session.isNew()) {
            Object visitCount1 = session.getAttribute("visitCount");
            if(visitCount1 != null){
                visitCount = (int)visitCount1;
                visitCount = visitCount + 1;
            }
        }
        else {
            session.setAttribute("role", Role.GUEST);
        }
        session.setAttribute("visitCount",  visitCount);
        Date date = new Date(session.getLastAccessedTime());
        Cookie cookie = new Cookie(
                "cookie",
                "counts=" + visitCount + ":time=" + date.toString().replaceAll("\\s+","-"));
        res.addCookie(cookie);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
