package pl.codeschool.controller;

import pl.codeschool.dao.ExerciseDao;
import pl.codeschool.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exercises/panel")
public class ExercisePanelController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        List<Exercise> allExercises = ExerciseDao.findAll();
        if (allExercises != null && allExercises.size() != 0) {
            request.setAttribute("exercises", allExercises);
        } else {
            request.setAttribute("exercisesNotFound", true);
        }

        request.getRequestDispatcher("/WEB-INF/exercises.jsp")
                .forward(request, response);
    }

}