/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uoa.di.ted.std08169.std09158.servlets;

import gr.uoa.di.ted.std08169.std09158.dao.TaskDao;
import gr.uoa.di.ted.std08169.std09158.domain.State;
import java.io.IOException;
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
public class EditTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String project = request.getParameter("project");
            String task = request.getParameter("task");
            new TaskDao().editTask(project, task, State.valueOf(request.getParameter("state")));
            response.sendRedirect("./task.jsp?project=" + project + "&task=" + task);
        } catch (ClassNotFoundException ex) {
            throw  new ServletException("Error editing task state", ex);
        } catch (SQLException ex) {
            throw  new ServletException("Error editing task state", ex);
        }
    }
}
