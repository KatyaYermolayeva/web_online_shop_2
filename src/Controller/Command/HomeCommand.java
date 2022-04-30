package Controller.Command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(
                    "/WEB-INF/View/index.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
