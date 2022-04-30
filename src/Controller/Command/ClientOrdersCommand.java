package Controller.Command;

import Model.Entity.Client;
import Model.DAO.ClientDAO;
import Model.DAO.OrderDAO;
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

public class ClientOrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            switch ((Role)request.getSession().getAttribute("role")) {
                case USER: {
                    response.sendRedirect("orders");
                    break;
                }
                case ADMIN: {
                    String clientUsername = request.getParameter("client");
                    List<Order> clientOrders = new ArrayList<Order>();
                    if (clientUsername != null) {
                        OrderDAO orderDAO = new OrderDAO();
                        ClientDAO clientDAO = new ClientDAO();
                        Client client = clientDAO.getClientByUsername(clientUsername);
                        clientOrders = orderDAO.getAllOrdersByClient(client.getId());
                    }
                    request.setAttribute("orders", clientOrders);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(
                            "/WEB-INF/View/clientOrders.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                default: {
                    response.sendRedirect("login");
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
