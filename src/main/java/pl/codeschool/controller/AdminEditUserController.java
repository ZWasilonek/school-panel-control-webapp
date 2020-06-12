package pl.codeschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.codeschool.dao.GroupDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.mapper.DataFiller;
import pl.codeschool.model.Admin;
import pl.codeschool.model.Group;
import pl.codeschool.model.User;
import pl.codeschool.validation.BlankValidation;
import pl.codeschool.validation.CapacityValidation;
import pl.codeschool.validation.UserRegistrationValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/admin/edit/user")
public class AdminEditUserController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminEditUserController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramUserName = request.getParameter("userName");
        String paramEmail = request.getParameter("userEmail");
        String paramPassword = request.getParameter("userPass");
        String paramRePassword = request.getParameter("rePass");
        String paramGroupName = request.getParameter("groupName");

        int userId = (int) request.getSession().getAttribute("userId");

        Map<String, String> fieldNames = Map.of("userName", paramUserName, "userEmail", paramEmail,
                "userPass", paramPassword, "rePass", paramRePassword, "groupName", paramGroupName);
        boolean hasBlankFields = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);
        boolean isUniqueUserEmail = checkUniqueUserEmail(paramEmail, userId, request);

        Map<String, Map<Integer, String>> capacitiesOfFields = Map.of("userName", Map.of(256, paramUserName),
                "userEmail", Map.of(256, paramEmail), "userPass", Map.of(256, paramPassword));
        boolean hasCapacityExceededFields = CapacityValidation.hasCapacityErrorAttributes(capacitiesOfFields, request);

        boolean isPasswordCorrect = checkPasswordEquality(paramPassword, paramRePassword, request);

        if (!hasBlankFields && isUniqueUserEmail && !hasCapacityExceededFields && isPasswordCorrect) {
            Group selectedGroup = GroupDao.readByName(paramGroupName);
            User foundedUser = UserDao.read(userId);

            if (foundedUser != null) {
            //Is the customer cheating with the admin user name?
                boolean isAdminUsernameSecured = UserRegistrationValidation.checkIfAdminUsernameIsSecured(request, paramUserName, foundedUser);
                if (isAdminUsernameSecured) {
                    User updatedUser = new User(userId, paramUserName, paramEmail, paramPassword, selectedGroup, foundedUser.isAdmin());
                    UserDao.update(updatedUser);
                    request.setAttribute("isUpdated", true);
                }
            }

            request.getRequestDispatcher("/WEB-INF/admin-edit-user.jsp")
                    .forward(request, response);
        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramUserName = request.getParameter("userName");
        String paramEmail = request.getParameter("userEmail");
        String paramUserId = request.getParameter("userId");

        if (!"".equals(paramUserId)) {
            try {
                int userId = Integer.parseInt(paramUserId);
                User founded = UserDao.read(userId);

                if (founded != null && founded.getUserName().equals(Admin.getAdminUsername())) {
                    request.setAttribute("ADMIN_USERNAME", Admin.getAdminUsername());
                    request.setAttribute("groups", List.of(founded.getGroup()));
                } else if (founded != null)
                    getAllGroupsOrSetError(founded, request, response);

                if (founded == null) {
                    request.setAttribute("userNotExists", true);
                } else if (paramUserName == null && paramEmail == null) {
                    request.getSession().setAttribute("userId", userId);
                    fillInTheFieldsWithTheGivenData(request, founded.getUserName(), founded.getEmail());
                } else {
                    fillInTheFieldsWithTheGivenData(request, paramUserName, paramEmail);
                }
                request.setAttribute("user", founded);

            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
            }
        }
        request.getRequestDispatcher("/WEB-INF/admin-edit-user.jsp")
                .forward(request, response);
    }

    private void fillInTheFieldsWithTheGivenData(HttpServletRequest request, String userName, String email) {
        if (userName != null && email != null) {
            Map<String, String> fieldNames = Map.of("userName", userName, "userEmail", email);
            DataFiller.modelAttributesFiller(fieldNames, request);
        }
    }

    private void getAllGroupsOrSetError(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groups = GroupDao.findAll();
        if (groups != null && groups.size() != 0) {
            if (user != null) {
                Group userGroup = user.getGroup();
                groups.forEach(group -> {
                    if (group.equals(userGroup)) {
                        Collections.swap(groups, 0, groups.indexOf(group));
                    }
                });
                Collections.sort(groups.subList(1, groups.size()));
            }
            request.setAttribute("groups", groups);
        } else {
            request.setAttribute("hasGroups", false);
            request.getRequestDispatcher("/WEB-INF/admin-edit-user.jsp")
                    .forward(request, response);
        }
    }

    private boolean checkUniqueUserEmail(String email, int userId, HttpServletRequest request) {
        final String NOT_UNIQUE = "email already taken";
        if (email != null && !"".equals(email)) {
            User founded = UserDao.findByEmail(email);
            if (founded != null && founded.getId() != userId) {
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