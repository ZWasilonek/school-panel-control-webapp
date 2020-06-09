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

@WebServlet("/admin/exercises")
public class AdminExercisesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        List<Exercise> exercises = ExerciseDao.findAll();
        if (exercises != null) {
            request.setAttribute("exercises", exercises);
        }

        request.getRequestDispatcher("/WEB-INF/admin-exercises.jsp")
                .forward(request, response);
    }

}
