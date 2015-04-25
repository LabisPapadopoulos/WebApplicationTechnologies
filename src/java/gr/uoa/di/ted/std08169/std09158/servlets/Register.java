/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uoa.di.ted.std08169.std09158.servlets;

import gr.uoa.di.ted.std08169.std09158.dao.UserDao;
import gr.uoa.di.ted.std08169.std09158.domain.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Labis
 */
public class Register extends HttpServlet {
    /**
     * Ylopoihsh ths doPost epeidh h methodos tis formas einai post
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Dao twn xrhstwn: antikeimeno pou xerei na xeirizetai xrhstes stin vash
            UserDao userDao = new UserDao();
            if (userDao.usernameExists(request.getParameter("username"))) //elenxo gia username
                response.sendRedirect("./register.jsp?error=username");
            else if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) //elenxo gia password
                response.sendRedirect("./register.jsp?error=password");
            else { //prosthikh xrhsth sth vash
                                //Dhmiourgeia neou xrhsth
                userDao.addUser(new User(request.getParameter("username"), request.getParameter("password"), 
                        request.getParameter("name"), request.getParameter("surname"), request.getParameter("email")));
                response.sendRedirect("./index.jsp"); //epistrofh stin arxikh selida
            }
        } catch (SQLException ex) {
            throw new ServletException("Error registering user", ex);
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Error registering user", ex);
        }
        
    } 
}
