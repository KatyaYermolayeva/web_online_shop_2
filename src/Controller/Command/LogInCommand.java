package Controller.Command;

import Controller.Utility.ErrorMessageUtil;
import Model.DAO.ClientDAO;
import Model.Entity.Client;
import Model.Exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username == null && password == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(
                        "/WEB-INF/View/login.jsp");
                dispatcher.forward(request, response);
            }
            else {
                ClientDAO clientDAO = new ClientDAO();
                Client client = clientDAO.getClientByUsernameAndPassword(username, password);
                if (client != null) {
                    request.getSession().setAttribute("clientId", client.getId());
                    request.getSession().setAttribute("role", client.getRole());
                    response.sendRedirect("home");
                }
                else {
                    request.setAttribute("error", ErrorMessageUtil.getMessage(ErrorMessageUtil.WRONG_LOGIN_CREDENTIALS));
                    return false;
                }
            }
        }
        catch (Exception | DAOException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
