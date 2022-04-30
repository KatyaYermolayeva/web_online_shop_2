package Controller.Command;

import Model.DAO.ProductDAO;
import Model.Exception.DAOException;
import Model.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductCommand implements Command{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProductDAO productDAO = new ProductDAO();
            Product product = new Product(
                    0,
                    request.getParameter("name"),
                    request.getParameter("description"),
                    Double.valueOf(request.getParameter("price")));
            productDAO.addProduct(product);
            response.sendRedirect("products");
        }
        catch (Exception | DAOException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
