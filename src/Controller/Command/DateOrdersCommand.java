package Controller.Command;

import Controller.Utility.ErrorMessageUtil;
import Model.DAO.OrderDAO;
import Model.Exception.DAOException;
import Model.Entity.Order;
import Model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DateOrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            if ((Role)request.getSession().getAttribute("role") != Role.ADMIN) {
                request.setAttribute("error", ErrorMessageUtil.getMessage(ErrorMessageUtil.PAGE_NOT_FOUND));
                return false;
            }
            String par = request.getParameter("deliveryDate");
            List<Order> dateOrders = new ArrayList<Order>();
            if (par != null) {
                OrderDAO orderDAO = new OrderDAO();
                Date deliveryDate = Date.valueOf(par);
                dateOrders = orderDAO.getAllOrdersByDeliveryDate(deliveryDate);
            }
            request.setAttribute("orders", dateOrders);
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/View/dateOrders.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception | DAOException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
