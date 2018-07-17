package ua.training.controller.command;

import ua.training.constant.NameCommands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for redirecting to index page since index page for admin and driver has profile
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see NameCommands
 */
public class ProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return NameCommands.INDEX_PAGE;
    }
}
