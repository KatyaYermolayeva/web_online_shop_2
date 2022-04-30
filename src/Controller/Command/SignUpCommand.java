package Controller.Command;

import Controller.Utility.ErrorMessageUtil;
import Model.DAO.ClientDAO;
import Model.DAO.OrderDAO;
import Model.DAO.StatusDAO;
import Model.Entity.Client;
import Model.Entity.Order;
import Model.Exception.DAOException;
import Model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (username == null && password == null && email == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(
                        "/WEB-INF/View/signup.jsp");
                dispatcher.forward(request, response);
            }
            else {
                ClientDAO clientDAO = new ClientDAO();
                Client client = clientDAO.getClientByUsername(username);
                if (client != null) {
                    request.setAttribute("error", ErrorMessageUtil.getMessage(ErrorMessageUtil.USER_ALREADY_EXISTS));
                    return false;
                }
                client = new Client(0, username, email, password, Role.USER);
                clientDAO.addClient(client);
                client = clientDAO.getClientByUsername(username);
                request.getSession().setAttribute("clientId", client.getId());
                request.getSession().setAttribute("role", client.getRole());
                response.sendRedirect("home");
            }
        }
        catch (Exception | DAOException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
