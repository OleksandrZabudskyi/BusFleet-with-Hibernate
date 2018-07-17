package ua.training.controller.filter;

import ua.training.constant.Attributes;
import ua.training.constant.Pages;
import ua.training.model.entity.Employee;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Class for authorization driver
 *
 * @author Zabudskyi Oleksandr
 * @see Filter
 * @see Attributes
 * @see Pages
 */
@WebFilter(urlPatterns = {"/bus-fleet/driver/*"})
public class DriverAuthorizationFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String role = (String) httpRequest.getSession().getAttribute(Attributes.ROLE);
        if (role.equals(Employee.ROLE.DRIVER.toString())) {
            chain.doFilter(request, response);
        } else {
            httpRequest.getRequestDispatcher(Pages.INDEX_PAGE).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
