package pl.codeschool.controller;

import pl.codeschool.dao.GroupDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.mapper.DataFiller;
import pl.codeschool.model.Group;
import pl.codeschool.model.User;
import pl.codeschool.validation.BlankValidation;
import pl.codeschool.validation.CapacityValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/adminAddUser")
public class AdminAddUserController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramUserName = request.getParameter("userName");
        String paramEmail = request.getParameter("userEmail");
        String paramPassword = request.getParameter("userPass");
        String paramRePassword = request.getParameter("rePass");
        String paramGroupName = request.getParameter("groupName");

        Map<String, String> fieldNames = Map.of("userName", paramUserName, "userEmail", paramEmail,
                "userPass", paramPassword, "rePass", paramRePassword, "groupName", paramGroupName);
        boolean hasBlankFields = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);
        boolean isUniqueUserEmail = checkUniqueUserEmail(paramEmail, request);

        Map<String, Map<Integer, String>> capacitiesOfFields = Map.of("userName", Map.of(256, paramUserName),
                "userEmail", Map.of(256, paramEmail), "userPass", Map.of(256, paramPassword));
        boolean hasCapacityExceededFields = CapacityValidation.hasCapacityErrorAttributes(capacitiesOfFields, request);

        if (!hasBlankFields && isUniqueUserEmail && !hasCapacityExceededFields
                && checkPasswordEquality(paramPassword, paramRePassword, request)) {
            Group selectedGroup = GroupDao.readByName(paramGroupName);
            if (selectedGroup != null) {
                User newUser = new User(paramUserName, paramEmail, paramPassword, selectedGroup);
                UserDao.create(newUser);
                request.setAttribute("userCreated", true);
            } else {
                request.setAttribute("hasGroups", false);
            }
            request.getRequestDispatcher("/WEB-INF/admin-add-user.jsp")
                    .forward(request, response);
        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        List<Group> groups = GroupDao.findAll();
        if (groups != null) {
            request.setAttribute("groups", groups);
        } else {
            request.setAttribute("hasGroups", false);
            request.getRequestDispatcher("/WEB-INF/admin-add-user.jsp")
                    .forward(request, response);
        }

        String paramUserName = request.getParameter("userName");
        String paramEmail = request.getParameter("userEmail");
        String paramGroupName = request.getParameter("groupName");

        if (paramUserName != null && paramEmail != null && paramGroupName != null) {
            Map<String, String> fieldNames = Map.of("userName", paramUserName, "userEmail", paramEmail);
            DataFiller.modelAttributesFiller(fieldNames, request);
        }

        request.getRequestDispatcher("/WEB-INF/admin-add-user.jsp")
                .forward(request, response);
    }

    private boolean checkUniqueUserEmail(String email, HttpServletRequest request) {
        final String NOT_UNIQUE = "email already taken";
        if (email != null && !"".equals(email)) {
            User founded = UserDao.findByEmail(email);
            if (founded != null) {
                request.setAttribute("notUniqueUserEmail", NOT_UNIQUE);
                return false;
            }
        }
        return true;
    }

    private boolean checkPasswordEquality(String password, String rePassword, HttpServletRequest request) {
        final String PASSWORDS_NOT_MATCH = "passwords must be identical";
        final String TOO_SHORT_PASSWORD = "password must be at least 8 characters";
        if (password.length() < 8) {
            request.setAttribute("passwordToShort", TOO_SHORT_PASSWORD);
            return false;
        } else if (!password.equals(rePassword)) {
            request.setAttribute("passwordsNotMatch", PASSWORDS_NOT_MATCH);
            return false;
        }
        return true;
    }

}
