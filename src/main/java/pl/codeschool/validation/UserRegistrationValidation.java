package pl.codeschool.validation;

import pl.codeschool.dao.UserDao;
import pl.codeschool.model.Admin;
import pl.codeschool.model.User;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrationValidation {

    public static boolean checkUniqueUserEmail(String email, HttpServletRequest request) {
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

    public static boolean checkPasswordEquality(String password, String rePassword, HttpServletRequest request) {
        String PASSWORDS_NOT_MATCH = "passwords must be identical";
        String TOO_SHORT_PASSWORD = "password must be at least 8 characters";
        if (password.length() < 8) {
            request.setAttribute("passwordToShort", TOO_SHORT_PASSWORD);
            return false;
        } else if (!password.equals(rePassword)) {
            request.setAttribute("passwordsNotMatch", PASSWORDS_NOT_MATCH);
            return false;
        }
        return true;
    }

    public static boolean isUserNameNotADMIN(String paramUserName, HttpServletRequest request) {
        String USED_ADMIN_USERNAME = "the name \"ADMIN\" is reserved";
        boolean isADMIN = paramUserName.toUpperCase().equals(Admin.getAdminUsername());
        if (isADMIN) {
            request.setAttribute("adminNameWasUsed", USED_ADMIN_USERNAME);
            return false;
        }
        return true;
    }

    public static boolean checkIfAdminUsernameIsSecured(HttpServletRequest request, String paramUserName, User foundedUser) {
        String foundedUserName = foundedUser.getUserName();
        if (foundedUserName.equals(Admin.getAdminUsername())) {
            if (!foundedUserName.equals(paramUserName)) {
                return UserRegistrationValidation.isUserNameNotADMIN(foundedUserName, request);
            }
        }
        return true;
    }

}