package pl.codeschool.controller;

import pl.codeschool.dao.ExerciseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete/exercise")
public class AdminDeleteExerciseController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramExerciseId = request.getParameter("exerciseId");

        if (paramExerciseId != null && !"".equals(paramExerciseId)) {
            try {
                int exerciseId = Integer.parseInt(paramExerciseId);
                ExerciseDao.delete(exerciseId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/admin/exercises").forward(request, response);
    }

}
