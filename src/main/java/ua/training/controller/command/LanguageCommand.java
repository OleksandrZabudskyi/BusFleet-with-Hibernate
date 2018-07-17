package ua.training.controller.command;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.util.LocaleManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter(Attributes.LANGUAGE);
        if (Attributes.UA.equals(lang)) {
            request.getSession().setAttribute(Attributes.LANGUAGE, Attributes.UA);
        } else {
            request.getSession().setAttribute(Attributes.LANGUAGE, Attributes.EN);
        }
        return NameCommands.REDIRECT.concat(request.getHeader(Attributes.REFERER));
    }
}
