/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uoa.di.ted.std08169.std09158.servlets;

import gr.uoa.di.ted.std08169.std09158.dao.ProjectDao;
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
public class DeleteProject extends HttpServlet {

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
            
            projectDao.deleteProject(request.getParameter("name"));
            
            response.sendRedirect("./projectAdministration.jsp");
            
        } catch (SQLException ex) {
            throw new ServletException("Error deleting project", ex);
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Error deleting project", ex);
        }
        
        
                
    }
}
