/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uoa.di.ted.std08169.std09158.servlets;

import gr.uoa.di.ted.std08169.std09158.dao.TaskDao;
import gr.uoa.di.ted.std08169.std09158.domain.Task;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Labis
 */
public class NewTask extends HttpServlet {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String project = request.getParameter("project");
            new TaskDao().addTask(new Task(project, request.getParameter("name"), 
                    request.getParameter("description"), dateFormat.parse(request.getParameter("startDate")),
                    dateFormat.parse(request.getParameter("endDate")), Arrays.asList(request.getParameterValues("staff"))));
            response.sendRedirect("./project.jsp?name=" + project);
        } catch (SQLException ex) {
            throw new ServletException("Error adding task", ex);
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Error adding task", ex);
        } catch (ParseException ex) {
            throw new ServletException("Error adding task", ex);
        }
    }
}
