package gr.uoa.di.ted.std08169.std09158.servlets;

import gr.uoa.di.ted.std08169.std09158.dao.UserDao;
import gr.uoa.di.ted.std08169.std09158.domain.Role;
import gr.uoa.di.ted.std08169.std09158.domain.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet
{
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            UserDao userDao = new UserDao();
            User user = userDao.getUser(request.getParameter("username"), request.getParameter("password"));
            if (user == null)
                response.sendRedirect("./index.jsp?error=user");
            else {
                //Prosthikh xrhsth sto Session (hashMap) me to onoma "user" pou antistoixei ston epistrefomeno user
                request.getSession().setAttribute("user", user);
                
                if(user.getRole() == Role.ADMINISTRATOR)
                    //Se periptwsh pou einai diaxeirisths, paei se selida diaxeiristh
                    response.sendRedirect("./administration.jsp");
                else
                    //Oloi oi upoloipoi xrhstes pane sta erga
                    response.sendRedirect("./projects.jsp");
                    
            }  
        } catch (SQLException ex) {
                                    //perigrafh exception
            throw new ServletException("Error logging in", ex);
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Error logging in", ex);
        }
        
        
    }
}
