package Controller.Command;

import Model.DAO.ProductDAO;
import Model.Exception.DAOException;
import Model.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/View/products.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception | DAOException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
