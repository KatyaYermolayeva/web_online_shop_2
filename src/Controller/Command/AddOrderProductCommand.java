package Controller.Command;

import Model.DAO.OrderDAO;
import Model.Exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddOrderProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.addProductToOrder(
                    Integer.parseInt(request.getParameter("product")),
                    Integer.parseInt(request.getParameter("id")),
                    Integer.parseInt(request.getParameter("amount")));
            response.sendRedirect("orders-products?id=" + request.getParameter("id"));
        }
        catch (Exception | DAOException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
