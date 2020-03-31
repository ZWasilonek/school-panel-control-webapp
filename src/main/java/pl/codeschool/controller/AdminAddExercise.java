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
        response.setContentType("text/html");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (title != null && !"".equals(title) && description != null && !"".equals(description)) {

            Exercise newExercise = new Exercise(title, description);
            ExerciseDao.create(newExercise);

            //nie wyświetla się info o dodaniu użytkownika
            request.setAttribute("exerciseCreated", true);
            request.getRequestDispatcher("/WEB-INF/admin-add-exercise.jsp")
                    .forward(request, response);
        } else {
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        request.getRequestDispatcher("/WEB-INF/admin-add-exercise.jsp")
                .forward(request, response);
    }
}
