package pl.codeschool.dao;

import pl.codeschool.model.Solution;
import pl.codeschool.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {

    private static final String CREATE_SOLUTION_QUERY =
        "INSERT INTO solutions(created, user_id, excercise_id) VALUES (?,?,?)";
    private static final String READ_USER_SOLUTION_QUERY =
        "SELECT * FROM solutions WHERE id = ? user_id = ?";
    private static final String READ_SOLUTION_QUERY =
        "SELECT * FROM solutions WHERE id = ?;";
    private static final String UPDATE_SOLUTION_QUERY =
        "UPDATE solutions SET updated = ?, description = ? where id = ?;";
    private static final String DELETE_SOLUTION_QUERY =
        "DELETE FROM solutions WHERE id = ?;";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
        "SELECT * FROM solutions;";
    private static final String FIND_ALL_SOLUTIONS_BY_USER_QUERY =
        "SELECT s.* FROM solutions s join users u on s.user_id = u.id where u.id = ?;";
    private static final String FIND_ALL_SOLUTIONS_BY_EXERCISE_QUERY =
        "SELECT s.* FROM solutions s join exercises e on s.exercise_id = e.id where e.id = ?;";
    private static final String FIND_RECENT_SOLUTIONS =
        "SELECT * FROM solutions WHERE updated IS NOT NULL ORDER BY updated DESC LIMIT ?;";

    public static Solution create(Solution solution) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, Timestamp.valueOf(solution.getCreated()));
            statement.setInt(2, solution.getUser().getId());
            statement.setInt(3, solution.getExercise().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Solution read(int solutionId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                solution.setUpdated(resultSet.getTimestamp("updated") != null ? resultSet.getTimestamp("updated").toLocalDateTime() : null);
                solution.setDescription(resultSet.getString("description"));
                solution.setUser(UserDao.read(resultSet.getInt("user_id")));
                solution.setExercise(ExerciseDao.read(resultSet.getInt("exercise_id")));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static Solution read(int solutionId, int userId) {
//        try (Connection conn = DBUtil.getConnection()) {
//            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
//            statement.setInt(1, solutionId);
//            statement.setInt(2, userId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                Solution solution = new Solution();
//                solution.setId(resultSet.getInt("id"));
//                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
//                solution.setUpdated(resultSet.getTimestamp("updated") != null ? resultSet.getTimestamp("updated").toLocalDateTime() : null);
//                solution.setDescription(resultSet.getString("description"));
//                solution.setUser(UserDao.read(resultSet.getInt("user_id")));
//                solution.setExercise(ExerciseDao.read(resultSet.getInt("exercise_id")));
//                return solution;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static void update(Solution solution) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(2, solution.getDescription());
            statement.setInt(3, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int solutionId, int userId) {
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY)) {
            statement.setInt(1, solutionId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Solution> addToArray(Solution s, List<Solution> solutions) {
        solutions.add(s);
        return solutions;
    }

    public static List<Solution> findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                solution.setDescription(resultSet.getString("description"));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Solution> findAllByUserId(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                solution.setUpdated(resultSet.getTimestamp("updated") != null ? resultSet.getTimestamp("updated").toLocalDateTime() : null);
                solution.setDescription(resultSet.getString("description"));
                solution.setUser(UserDao.read(resultSet.getInt("user_id")));
                solution.setExercise(ExerciseDao.read(resultSet.getInt("exercise_id")));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Solution> findAllByExerciseId(int exerciseId) {
        try (Connection conn = DBUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                solution.setDescription(resultSet.getString("description"));
                solution.setUser(UserDao.read(resultSet.getInt("user_id")));
                solution.setExercise(ExerciseDao.read(resultSet.getInt("exercise_id")));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Solution> findRecent(int limit) {
        try (Connection conn = DBUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_RECENT_SOLUTIONS);
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                solution.setUpdated(resultSet.getTimestamp("updated") != null ? resultSet.getTimestamp("updated").toLocalDateTime() : null);
                solution.setDescription(resultSet.getString("description"));
                solution.setUser(UserDao.read(resultSet.getInt("user_id")));
                solution.setExercise(ExerciseDao.read(resultSet.getInt("exercise_id")));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
