package pl.codeschool.dao;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.codeschool.model.User;
import pl.codeschool.util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password, group_id, is_admin) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ?, group_id = ?, is_admin = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";
    private static final String FIND_ALL_USERS_BY_GROUP_QUERY =
            "SELECT * FROM users u JOIN users_groups ug ON u.group_id = ug.id WHERE ug.id = ?";
    private static final String FIND_BY_EMAIL_QUERY =
            "SELECT * FROM users WHERE email = ?";

    public static User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getGroup().getId());
            statement.setBoolean(5, user.isAdmin());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    public static User read(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setGroup(GroupDao.read(resultSet.getInt("group_id")));
                user.setAdmin(resultSet.getBoolean("is_admin"));
                return user;
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return null;
    }

    public static void update(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getGroup().getId());
            statement.setBoolean(5, user.isAdmin());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public static void delete(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public static List<User> findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setGroup(GroupDao.read(resultSet.getInt("group_id")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    public static List<User> findAllByGroupId(int groupId) {
        try (Connection conn = DBUtil.getConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_BY_GROUP_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setGroup(GroupDao.read(resultSet.getInt("group_id")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    public static User findByEmail(String email) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_BY_EMAIL_QUERY);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setGroup(GroupDao.read(resultSet.getInt("group_id")));
                user.setAdmin(resultSet.getBoolean("is_admin"));
                return user;
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return null;
    }

    public static boolean verifyUserPassword(User user, String password) {
        return BCrypt.checkpw(password, user.getPassword());
    }

    public static void authorizeUser(User user, HttpServletRequest request) {
        if (user != null) {
            if (user.isAdmin())
                request.getSession().setAttribute("adminId", user.getId());
            else
                request.getSession().setAttribute("userId", user.getId());
        }
    }

}