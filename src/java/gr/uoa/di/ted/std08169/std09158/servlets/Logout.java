/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uoa.di.ted.std08169.std09158.servlets;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class Logout extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user"); //afaireitai o xrhsths apo to session
        
        response.sendRedirect("./index.jsp");
    }
}
