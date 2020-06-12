package pl.codeschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.codeschool.dao.SolutionDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.model.Solution;
import pl.codeschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solution/delete")
public class SolutionDeleteController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SolutionDeleteController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramSolutionId = request.getParameter("solutionId");

        int exerciseId = 0;

        if (paramSolutionId != null && !"".equals(paramSolutionId)) {
            try {
                int solutionId = Integer.parseInt(paramSolutionId);
                Solution foundedSolution = SolutionDao.read(solutionId);
                Integer userId = getUserId(request);
                User foundedUser = UserDao.read(userId);

                if (foundedSolution != null && foundedUser != null) {
                    if (foundedUser.getId() == foundedSolution.getUser().getId() || foundedUser.isAdmin()) {
                        SolutionDao.delete(solutionId);
                        exerciseId = foundedSolution.getExercise().getId();
                    } else request.setAttribute("hasPermissionToDelete", false);
                }
            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
            }
        }
        request.getRequestDispatcher("/exercise/solutions/info?exerciseId=" + exerciseId).forward(request, response);
    }

    private Integer getUserId(HttpServletRequest request) {
        Integer userId;
        userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            userId = (Integer) request.getSession().getAttribute("adminId");
        }
        return userId;
    }

}