package pl.codeschool.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="loginUserFilter", urlPatterns = {"/groups*", "/groups/*", "/exercises*", "/exercises/*", "/add*", "/add/*"})
public class LoginUserFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getRequestURI();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        boolean isUserAuthenticated = httpServletRequest.getSession().getAttribute("userId") != null;
        boolean isAdminAuthenticated = httpServletRequest.getSession().getAttribute("adminId") != null;

        System.out.println("testsout userLogin - income: " + httpServletRequest.getContextPath() + httpServletRequest.getServletPath());

        if (isAdminAuthenticated || isUserAuthenticated && !path.startsWith("/admin/")) {
            filterChain.doFilter(request,response);
        } else {
            LOGGER.info("");
            httpServletResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }

}
