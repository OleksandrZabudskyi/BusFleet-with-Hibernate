package ua.training.controller.filter;

import ua.training.constant.Attributes;
import ua.training.util.LocaleManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

/**
 * Language filter for messages
 *
 * @author Zabudskyi Oleksandr
 * @see LocaleManager
 * @see Locale
 */
@WebFilter(urlPatterns = {"/*"})
public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String sessionLocale = (String) httpRequest.getSession().getAttribute(Attributes.LANGUAGE);
        if (Objects.nonNull(sessionLocale)) {
            Locale locale = new Locale(sessionLocale);
            LocaleManager.setLocale(locale);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
