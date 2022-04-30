package Controller.Command;

import Model.DAO.ClientDAO;
import Model.DAO.OrderDAO;
import Model.Entity.Client;
import Model.Exception.DAOException;
import Model.Entity.Order;
import Model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class OrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            switch ((Role)request.getSession().getAttribute("role")) {
                case USER: {
                    OrderDAO orderDAO = new OrderDAO();
                    int clientId = (int) request.getSession().getAttribute("clientId");
                    List<Order> orders = orderDAO.getAllOrdersByClient(clientId);
                    request.setAttribute("orders", orders);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(
                            "/WEB-INF/View/order.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case ADMIN: {
                    OrderDAO orderDAO = new OrderDAO();
                    List<Order> orders = orderDAO.getAllOrders();
                    request.setAttribute("orders", orders);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(
                            "/WEB-INF/View/order.jsp");
                    dispatcher.forward(request, response);
                    break;
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
