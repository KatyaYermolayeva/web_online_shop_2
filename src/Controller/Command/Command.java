package Controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command interface.
 */
public interface Command {
    /**
     * Executes a command.
     * @param request request
     * @param response response
     * @return true if execution was successful
     */
    boolean execute(HttpServletRequest request, HttpServletResponse response);
}
