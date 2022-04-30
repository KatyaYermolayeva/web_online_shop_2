package Controller.Command;

import Model.DAO.ClientDAO;
import Model.DAO.OrderDAO;
import Model.DAO.StatusDAO;
import Model.Exception.DAOException;
import Model.Entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class AddOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            OrderDAO orderDAO = new OrderDAO();
            ClientDAO clientDAO = new ClientDAO();
            StatusDAO statusDAO = new StatusDAO();
            Order order = new Order(
                    0,
                    clientDAO.getClient((Integer) request.getSession().getAttribute("clientId")),
                    Date.valueOf(request.getParameter("deliveryDate")),
                    statusDAO.getStatus("pending"));
            orderDAO.addOrder(order);
            response.sendRedirect("orders");
        }
        catch (Exception | DAOException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
