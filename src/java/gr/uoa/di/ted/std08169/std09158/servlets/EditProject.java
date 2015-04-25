/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uoa.di.ted.std08169.std09158.servlets;

import gr.uoa.di.ted.std08169.std09158.dao.ProjectDao;
import gr.uoa.di.ted.std08169.std09158.domain.Project;
import gr.uoa.di.ted.std08169.std09158.domain.Visibility;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Labis
 */
public class EditProject extends HttpServlet {
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProjectDao projectDao = new ProjectDao();
            
            List<String> staff = Arrays.asList((request.getParameterValues("staff") == null) ? new String[0] : request.getParameterValues("staff"));
            
            projectDao.editProject(new Project(request.getParameter("name"), request.getParameter("description"), 
                    Visibility.valueOf(request.getParameter("visibility")), request.getParameter("manager"), staff));
            response.sendRedirect("./projectAdministration.jsp");
            
        } catch (SQLException ex) {
            throw new ServletException("Error editing project", ex);
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Error editing project", ex);
        }
        
        
        
    }
}
