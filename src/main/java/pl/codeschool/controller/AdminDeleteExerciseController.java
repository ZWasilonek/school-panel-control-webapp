package pl.codeschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.codeschool.dao.ExerciseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete/exercise")
public class AdminDeleteExerciseController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDeleteExerciseController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramExerciseId = request.getParameter("exerciseId");

        if (paramExerciseId != null && !"".equals(paramExerciseId)) {
            try {
                int exerciseId = Integer.parseInt(paramExerciseId);
                ExerciseDao.delete(exerciseId);
            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
            }
        }
        request.getRequestDispatcher("/admin/exercises").forward(request, response);
    }

}