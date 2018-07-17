package ua.training.controller;

import ua.training.constant.GlobalConstants;
import ua.training.constant.NameCommands;
import ua.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Class realizes the controller of the MVC pattern
 *
 * @author Zabudskyi Oleksandr
 * @see HttpServlet
 */
@WebServlet(urlPatterns = {"/bus-fleet/*"})
public class Servlet extends HttpServlet {
    private CommandExtractor commandExtractor;

    @Override
    public void init() {
        commandExtractor = CommandExtractor.getInstance();
    }

    /**
     * Gets command name from request uri and do some action depends on command name.
     * Then forward or redirect
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = commandExtractor.getCommand(request);
        String page = command.execute(request, response);
        if (page.contains(NameCommands.REDIRECT)) {
            response.sendRedirect(page.replace(NameCommands.REDIRECT, GlobalConstants.EMPTY_SIGN));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

