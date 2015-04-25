package gr.uoa.di.ted.std08169.std09158.servlets;

import gr.uoa.di.ted.std08169.std09158.dao.TaskDao;
import gr.uoa.di.ted.std08169.std09158.domain.Comment;
import gr.uoa.di.ted.std08169.std09158.domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Labis
 */
public class NewComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //pairnoume ton xrhsth apo to session
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("./index.jsp");
        } else {
            try {
                String project = request.getParameter("project");
                String task = request.getParameter("task");
                new TaskDao().addComment(new Comment(user.getUsername(), project, task, request.getParameter("text")));
                response.sendRedirect("./task.jsp?project=" + project + "&task=" + task);
            } catch (SQLException ex) {
                throw new ServletException("Error adding comment", ex);
            } catch (ClassNotFoundException ex) {
                throw new ServletException("Error adding comment", ex);
            }
        }
    }

}
