package ua.training.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * interface implementing command pattern
 *
 * @author Zabudskyi Oleksandr
 */
public interface Command {
    /**
     * Execute some operations with request or response and return command name {@link ua.training.constant.NameCommands}
     * or jsp page {@link ua.training.constant.Pages}
     *
     * @param request request
     * @param response response
     * @return command name or jsp page
     * @throws ServletException throws in case of use methods generating such exception
     * @throws IOException throws in case of use methods generating such exception
     */
    String execute(HttpServletRequest request,
                   HttpServletResponse response) throws ServletException, IOException;
}
