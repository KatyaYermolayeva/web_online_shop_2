package Controller.Filter;

import Model.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BannedAccessFilter implements Filter {
    private HashSet<String> requireLoginCommands;

    public void init(FilterConfig config)
            throws ServletException {
        requireLoginCommands = new HashSet<String>();
        String requireLogin =
                config.getInitParameter("requireLogin");
        StringTokenizer tok =
                new StringTokenizer(requireLogin);
        while(tok.hasMoreTokens()) {
            String requireLoginCommand = tok.nextToken();
            requireLoginCommands.add(requireLoginCommand);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession(true);
        if ((session.getAttribute("role") == null)
                || (session.getAttribute("role") == Role.GUEST
                && requireLoginCommands.contains(req.getRequestURI().substring(contextPath.length()))))
        {
            res.sendRedirect("home");
        }
        else
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
