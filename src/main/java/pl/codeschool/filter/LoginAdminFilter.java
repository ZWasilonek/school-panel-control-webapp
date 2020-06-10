package pl.codeschool.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginAdminFilter", urlPatterns = {"/admin*", "/admin/*", "/admin/*/*"})
public class LoginAdminFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        boolean isAdminAuthenticated = httpServletRequest.getSession().getAttribute("adminId") != null;
        System.out.println("testsout adminLogin - income: " + httpServletRequest.getContextPath() + " / " + httpServletRequest.getServletPath());

        if (isAdminAuthenticated) {
            filterChain.doFilter(request, response);
        } else {
            LOGGER.debug("");
            ((HttpServletResponse) response).sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }

}
