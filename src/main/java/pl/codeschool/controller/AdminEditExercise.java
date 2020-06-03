package pl.codeschool.controller;

import pl.codeschool.dao.ExerciseDao;
import pl.codeschool.dao.GroupDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.model.Exercise;
import pl.codeschool.model.Group;
import pl.codeschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminEditExercise")
public class AdminEditExercise extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String paramExerciseId = request.getParameter("exerciseId");

        Exercise exercise = (Exercise) request.getSession().getAttribute("exercise");
        int exerciseId = 0;
        if (exercise != null) {
            exerciseId = exercise.getId();
        }

        validateParams(title, description, request, response);

        if (!"".equals(title) && !"".equals(description)) {
            Exercise updatedExercise = new Exercise(exerciseId, title, description);
            ExerciseDao.update(updatedExercise);

            request.setAttribute("isUpdated", true);
            request.setAttribute("exerciseId", paramExerciseId);

            request.getRequestDispatcher("/WEB-INF/admin-edit-exercise.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String paramExerciseId = request.getParameter("exerciseId");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (paramExerciseId != null && !"".equals(paramExerciseId)) {
            try {
                int exerciseId = Integer.parseInt(paramExerciseId);
                Exercise founded = ExerciseDao.read(exerciseId);
                request.getSession().setAttribute("exercise", founded);
                fillTheFormWithLatestData(founded.getTitle(), founded.getDescription(), request);        fillTheFormWithLatestData(title, description, request);
                fillTheFormWithLatestData(title, description, request);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/admin-edit-exercise.jsp")
                .forward(request, response);
    }

    private void validateParams(String title, String description, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String EMPTY_FIELD = "this field cannot be empty";
        final String CHARS_CAPACITY_EXCEEDED = "allowed number of characters exceeded";

        int titleLength = title.length();
        int descriptionLength = description.length();

        if ("".equals(title) && "".equals(description)) {
            request.setAttribute("blankTitle", EMPTY_FIELD);
            request.setAttribute("blankDescription", EMPTY_FIELD);
            doGet(request, response);
        } else if ("".equals(title)) {
            request.setAttribute("blankTitle", EMPTY_FIELD);
            doGet(request, response);
        } else if ("".equals(description)) {
            request.setAttribute("blankDescription", EMPTY_FIELD);
            doGet(request, response);
        } else if (titleLength > 256 && descriptionLength > 65535) {
            request.setAttribute("tooManyCharsTitle", CHARS_CAPACITY_EXCEEDED);
            request.setAttribute("tooManyCharsDesc", CHARS_CAPACITY_EXCEEDED);
            doGet(request, response);
        } else if (titleLength > 256) {
            request.setAttribute("tooManyCharsTitle", CHARS_CAPACITY_EXCEEDED);
            doGet(request, response);
        } else if (descriptionLength > 65535) {
            request.setAttribute("tooManyCharsDesc", CHARS_CAPACITY_EXCEEDED);
            doGet(request, response);
        }
    }

    private void fillTheFormWithLatestData(String title, String description, HttpServletRequest request) {
        if (title != null && description != null) {
            if (!"".equals(title) && !"".equals(description)) {
                request.setAttribute("titleVal", title);
                request.setAttribute("descriptionVal", description);
            } else if (!"".equals(title)) {
                request.setAttribute("titleVal", title);
            } else if (!"".equals(description)) {
                request.setAttribute("descriptionVal", description);
            }
        }
    }
}
