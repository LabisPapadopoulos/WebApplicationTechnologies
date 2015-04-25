package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import gr.uoa.di.ted.std08169.std09158.domain.Role;
import gr.uoa.di.ted.std08169.std09158.domain.User;
import gr.uoa.di.ted.std08169.std09158.dao.ProjectDao;
import gr.uoa.di.ted.std08169.std09158.dao.UserDao;
import gr.uoa.di.ted.std08169.std09158.domain.Visibility;

public final class newProject_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
  UserDao userDao = new UserDao(); 
    User user = (User) request.getSession().getAttribute("user");
    if ((user == null) || (user.getRole() != Role.ADMINISTRATOR))
        response.sendRedirect("./index.jsp");
    else { 
      out.write("\n");
      out.write("        <!DOCTYPE html>\n");
      out.write("        <html>\n");
      out.write("            <head>\n");
      out.write("                <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("                <title>Project Management</title>\n");
      out.write("            </head>\n");
      out.write("            <body>\n");
      out.write("                <h1>New Project</h1>\n");
      out.write("                <form action=\"./newProject\" method=\"post\">\n");
      out.write("                    <label for=\"name\">Name:</label>\n");
      out.write("                    <input type=\"text\" id=\"name\" name=\"name\" required=\"required\" maxlength=\"16\" size=\"16\" />\n");
      out.write("                    <br />\n");
      out.write("                    <label for=\"description\">Description:</label>\n");
      out.write("                    <textarea id=\"description\" name=\"description\" required=\"required\" maxlength=\"256\" cols=\"32\" rows=\"8\"></textarea>\n");
      out.write("                    <br />\n");
      out.write("                    <label for=\"visibility\">Visibility:</label>\n");
      out.write("                    <select id=\"visibility\" name=\"visibility\">\n");
      out.write("                        ");
 for (Visibility visibility : Visibility.values()) { 
      out.write("\n");
      out.write("                            <option value=\"");
      out.print( visibility );
      out.write('"');
      out.write('>');
      out.print( visibility );
      out.write("</option>\n");
      out.write("                        ");
 } 
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                    <br />\n");
      out.write("                    <label>Manager:</label>\n");
      out.write("                    <select id=\"manager\" name=\"manager\">\n");
      out.write("                        ");
 for (String manager : userDao.getManagers()) { 
      out.write("\n");
      out.write("                            <option value=\"");
      out.print( manager );
      out.write('"');
      out.write('>');
      out.print( manager );
      out.write("</option>\n");
      out.write("                        ");
 } 
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                    <br />\n");
      out.write("                    <label>Staff:</label>\n");
      out.write("                    <!--multiple: gia na borei na epilegei pollous ws staff -->\n");
      out.write("                    <select id=\"staff\" name=\"staff\" multiple=\"multiple\">\n");
      out.write("                        ");
 for (String staff : userDao.getStaff()) { 
      out.write("\n");
      out.write("                            <option value=\"");
      out.print( staff );
      out.write('"');
      out.write('>');
      out.print( staff );
      out.write("</option>\n");
      out.write("                        ");
 } 
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                    <br />\n");
      out.write("                    <input type=\"submit\" value=\"Create\" />\n");
      out.write("                    <input type=\"reset\" value=\"Clear\" />\n");
      out.write("                </form>\n");
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
