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
        response.setContentType("text/html");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Exercise exercise = (Exercise) request.getSession().getAttribute("exercise");
        int exerciseId = 0;
        if (exercise != null) {
            exerciseId = exercise.getId();
        }

        if (title != null && !"".equals(title) && description != null && !"".equals(description)) {

            Exercise updatedExercise = new Exercise(exerciseId, title, description);
            ExerciseDao.update(updatedExercise);

            //NIE DZIAŁA -SPRAWDZ
            request.setAttribute("isUpdated", true);
            request.getRequestDispatcher("/WEB-INF/admin-edit-exercise.jsp")
                    .forward(request, response);

        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String paramExerciseId = request.getParameter("exerciseId");

        if (paramExerciseId != null && !"".equals(paramExerciseId)) {
            try {
                int exerciseId = Integer.parseInt(paramExerciseId);
                Exercise founded = ExerciseDao.read(exerciseId);
                request.getSession().setAttribute("exercise", founded);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/admin-edit-exercise.jsp")
                .forward(request, response);
    }
}
