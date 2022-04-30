package Controller.Command;

import Controller.Utility.ErrorMessageUtil;
import Model.DAO.OrderDAO;
import Model.DAO.ProductDAO;
import Model.Entity.Order;
import Model.Exception.DAOException;
import Model.Entity.Product;
import Model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class OrdersProductsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int clientId = (int)request.getSession().getAttribute("clientId");
            ProductDAO productDAO = new ProductDAO();
            OrderDAO orderDAO = new OrderDAO();
            Integer orderId = Integer.valueOf(request.getParameter("id"));
            Order order = orderDAO.getOrder(orderId);
            if (order == null ||
                    (request.getSession().getAttribute("role") == Role.USER && order.getClient().getId() != clientId)) {
                request.setAttribute("error", ErrorMessageUtil.getMessage(ErrorMessageUtil.ORDER_NOT_FOUND));
                return false;
            }
            Map<Product, Integer> ordersProducts = productDAO.getAllProductsByOrder(orderId);
            request.setAttribute("ordersProducts", ordersProducts);
            request.setAttribute("id", orderId);
            request.setAttribute("isPending", orderDAO.isPending(orderId));
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/View/orderProducts.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception | DAOException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
