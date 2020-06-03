package pl.codeschool.controller;

import pl.codeschool.dao.ExerciseDao;
import pl.codeschool.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminAddExercise")
public class AdminAddExercise extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        validateParams(title, description ,request, response);

        if (!"".equals(title) && !"".equals(description)) {
            Exercise newExercise = new Exercise(title, description);
            ExerciseDao.create(newExercise);
            request.setAttribute("exerciseCreated", true);
            request.getRequestDispatcher("/WEB-INF/admin-add-exercise.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        fillTheFormWithLatestData(title, description, request);

        request.getRequestDispatcher("/WEB-INF/admin-add-exercise.jsp")
            .forward(request,response);
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
