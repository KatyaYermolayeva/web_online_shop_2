package Controller.Filter;

import Controller.Utility.ErrorMessageUtil;
import Model.DAO.ClientDAO;
import Model.Exception.DAOException;
import Model.Role;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class SignUpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            ClientDAO clientDAO = new ClientDAO();
            if (clientDAO.getClientByUsername(username) != null) {
                req.setAttribute("error", ErrorMessageUtil.getMessage(ErrorMessageUtil.USER_ALREADY_EXISTS));
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(
                        "/WEB-INF/View/error.jsp");
                dispatcher.forward(req, res);
            }
        }
        catch (DAOException e) {
            req.setAttribute("error", "Server error");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(
                    "/WEB-INF/View/error.jsp");
            dispatcher.forward(req, res);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
