package Controller.Command;

import Model.DAO.ClientDAO;
import Model.Entity.Client;
import Model.Exception.DAOException;
import Model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements  Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("role", Role.GUEST);
            request.getSession().setAttribute("clientId", null);
            response.sendRedirect("home");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
