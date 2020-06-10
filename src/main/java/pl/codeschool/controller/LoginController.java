package pl.codeschool.controller;

import pl.codeschool.dao.UserDao;
import pl.codeschool.mapper.DataFiller;
import pl.codeschool.model.User;
import pl.codeschool.validation.BlankValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramEmail = request.getParameter("userEmail");
        String paramPassword = request.getParameter("userPass");

        Map<String, String> fieldNames = Map.of("userEmail", paramEmail, "userPass", paramPassword);
        boolean hasBlankFields = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);

        if (!hasBlankFields) {
            User foundedUser = UserDao.findByEmail(paramEmail);
            if (foundedUser == null) {
                request.setAttribute("failedLogin", true);
                doGet(request, response);
            } else {
                boolean correctPassword = UserDao.verifyUserPassword(foundedUser, paramPassword);
                if (correctPassword) {
                    UserDao.authorizeUser(foundedUser, request);
                    response.sendRedirect("/");
                } else {
                    request.setAttribute("failedLogin", true);
                    doGet(request, response);
                }
            }
        } else doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramEmail = request.getParameter("userEmail");

        if (paramEmail != null && !"".equals(paramEmail)) {
            DataFiller.modelAttributesFiller(Map.of("userEmail", paramEmail), request);
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

}
