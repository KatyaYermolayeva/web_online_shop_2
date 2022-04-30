package Controller;

import Controller.Command.*;
import Controller.Utility.ErrorMessageUtil;
import Model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Console;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.MainServlet.class);
    private Map<String, Command> commands = new HashMap<String, Command>();

    @Override
    public void init() throws ServletException {
        commands.put("/products", new ProductsCommand());
        commands.put("/home", new HomeCommand());
        commands.put("/", new HomeCommand());
        commands.put("/orders", new OrdersCommand());
        commands.put("/orders-products", new OrdersProductsCommand());
        commands.put("/add-order", new AddOrderCommand());
        commands.put("/add-product", new AddProductCommand());
        commands.put("/add-order-product", new AddOrderProductCommand());
        commands.put("/delayed-orders", new DelayedOrdersCommand());
        commands.put("/date-orders", new DateOrdersCommand());
        commands.put("/client-orders", new ClientOrdersCommand());
        commands.put("/log-in", new LogInCommand());
        commands.put("/log-out", new LogOutCommand());
        commands.put("/sign-up", new SignUpCommand());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getRequestURI().substring(request.getContextPath().length());
        Command command = commands.get(commandName);

        if (command == null) {
            request.setAttribute("error", ErrorMessageUtil.getMessage(ErrorMessageUtil.PAGE_NOT_FOUND));
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(
                    "/WEB-INF/View/error.jsp");
            dispatcher.forward(request, response);
        }

        if (!command.execute(request, response)) {
            try {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(
                        "/WEB-INF/View/error.jsp");
                dispatcher.forward(request, response);
            }
            catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
