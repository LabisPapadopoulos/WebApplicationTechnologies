package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import gr.uoa.di.ted.std08169.std09158.domain.Role;
import gr.uoa.di.ted.std08169.std09158.domain.User;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    //To session einai tou trexontos xrhsth - client.
    User user = (User) request.getSession().getAttribute("user");
    //xwris login 'h an den einai administrator phgainei stin arxikh selida
    if (user != null) {
        if (user.getRole() == Role.ADMINISTRATOR)
            response.sendRedirect("./administration.jsp");
        else
            response.sendRedirect("./projects.jsp");
    } else { 
      out.write("\n");
      out.write("        <!DOCTYPE html>\n");
      out.write("        <html>\n");
      out.write("            <head>\n");
      out.write("                <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("                <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\n");
      out.write("                <title>Project Management Web Application</title>\n");
      out.write("            </head>\n");
      out.write("            <body>\n");
      out.write("                <div id=\"wrapper\" >\n");
      out.write("                    <div id=\"header\">\n");
      out.write("                        <h1>Project Management Web Application</h1>\n");
      out.write("                    </div>\n");
      out.write("                    <div id=\"imgage\">\n");
      out.write("                        <img src=\"./project-management.jpg\" alt=\"pic1\" />\n");
      out.write("                    </div>\n");
      out.write("                    ");
 String error = request.getParameter("error"); 
                        if((error != null) && error.equals("user")) { 
      out.write("\n");
      out.write("                        <p id=\"error\">Error in username or password</p>\n");
      out.write("                     ");
 } 
      out.write("\n");
      out.write("                     <div id=\"register\">\n");
      out.write("                        <form action=\"./register.jsp\" method=\"get\">\n");
      out.write("                            <fieldset>\n");
      out.write("                                <legend>New user? Register here:</legend>\n");
      out.write("                                <input type=\"submit\" value=\"Register\" class=\"button\" />\n");
      out.write("                            </fieldset>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                     <div id=\"login\">\n");
      out.write("                        <form action=\"./login\" method=\"post\">\n");
      out.write("                            <fieldset>\n");
      out.write("                                <legend>If you already have an account, login here:</legend>\n");
      out.write("                                <label for=\"username\">Username:</label>\n");
      out.write("                                <input id=\"username\" type=\"text\" name=\"username\" required=\"required\" maxlength=\"16\" size=\"16\" />\n");
      out.write("                                <br />\n");
      out.write("                                <label for=\"password\">Password:</label>\n");
      out.write("                                <input id=\"password\" type=\"password\" name=\"password\" required=\"required\" maxlength=\"16\" size=\"16\" />\n");
      out.write("                                <br />\n");
      out.write("                                <input type=\"submit\" value=\"Login\" class=\"button\" />\n");
      out.write("                                <input type=\"reset\" value=\"Clear\" class=\"button\" />\n");
      out.write("                            </fieldset>\n");
      out.write("                       </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div>\n");
      out.write("                    <p id=\"myname\">Copyright &copy; 2012 - Designed by Xaralambos Papadopoulos (1115200800169) - Nikos Panagiotopoulos (1115200900158)</p>\n");
      out.write("                    <p style=\"text-align: center;\">\n");
      out.write("                        <a href=\"http://www.w3.org/html/logo/\">\n");
      out.write("                            <img src=\"http://www.w3.org/html/logo/badge/html5-badge-h-css3.png\" width=\"133\" height=\"64\" alt=\"HTML5 Powered with CSS3 / Styling\" title=\"HTML5 Powered with CSS3 / Styling\">\n");
      out.write("                        </a>\n");
      out.write("                    </p>\n");
      out.write("                </div>\n");
      out.write("            </body>\n");
      out.write("        </html>\n");
 } 
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
