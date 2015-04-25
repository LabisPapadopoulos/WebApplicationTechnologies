<%-- 
    Document   : adminProject
    Created on : 27 Σεπ 2012, 9:43:00 μμ
    Author     : Labis
--%>

<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Role"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.User"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.UserDao"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Visibility"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Project"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.ProjectDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  ProjectDao projectDao = new ProjectDao(); 
    UserDao userDao = new UserDao();
                    //h parametros pou perasteike sto url
    Project project = projectDao.getProject(request.getParameter("name"));
    
    //To session einai tou trexontos xrhsth - client.
    User user = (User) request.getSession().getAttribute("user");
    //xwris login 'h an den einai administrator phgainei stin arxikh selida
    if ((user == null) || (user.getRole() != Role.ADMINISTRATOR))
        response.sendRedirect("./index.jsp");
    else { %>
        <!DOCTYPE html>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="style.css" />
                <title>Project Management</title>
            </head>
            <body>
                <div id="wrapper" >
                    <div id="header">
                        <h1>Project '<%= project.getName() %>' Administration</h1>
                    </div>
                    <form action="./logout" method="post" class="breadcrumbs">
                        You are here: <a href="./administration.jsp">Administration</a> -> <a href="./projectAdministration.jsp">Project Administration</a> -> <%= project.getName() %>
                        <br />
                        You are logged in as: <%= user.getUsername() %>
                        <br />
                        <input type="submit" value="Logout" class="button" /> <!-- element xwris paidi -->
                    </form>
                    <div id="projectEdit">
                        <form method="post">
                            <input type="hidden" name="name" value="<%= project.getName() %>" />
                            <label for="description">Description:</label>
                            <textarea id="description" name="description" required="required" maxlength="256" cols="32" rows="8"><%= project.getDescription() %></textarea>
                            <br />
                            <label for="visibility">Visibility:</label>
                            <select id="visibility" name="visibility">
                                <% for (Visibility visibility : Visibility.values()) { %>
                                <option value="<%= visibility %>"<%= (visibility == project.getVisibility()) ? " selected=\"selected\"" : "" %>><%= visibility %></option>
                                <% } %>
                            </select>
                            <br />
                            <label>Manager:</label>
                            <select id="manager" name="manager">
                                <% for (String manager : userDao.getManagers()) { %>
                                    <%-- An o trexon manager einai isos me ton manager tou project, tote epelexe ton --%>
                                    <option value="<%= manager %>"<%= manager.equals(project.getManager()) ? " selected=\"selected\"" : "" %>><%= manager %></option>
                                <% } %>
                            </select>
                            <br />
                            <label>Staff:</label>
                            <!--multiple: gia na borei na epilegei pollous ws staff -->
                            <select id="staff" name="staff" multiple="multiple">
                                <% for (String staff : userDao.getStaff()) { %>
                                    <%-- An o xrhsths staff, periexetai mesa stin lista tou staff tou project, tote selected --%>
                                    <option value="<%= staff %>"<%= project.getStaff().contains(staff) ? " selected=\"selected\"" : "" %>><%= staff %></option>
                                <% } %>
                            </select>
                            <br />
                            <input type="submit" value="Edit" formaction="./editProject" class="button" />
                            <input type="reset" value="Reset" class="button" />
                            <input type="submit" value="Delete" formaction="./deleteProject" class="button" onclick="return confirm('Are you sure you want to delete this project ?');" />
                        </form>
                    </div>
                </div>
                <div>
                    <p id="myname">Copyright &copy; 2012 - Designed by Xaralambos Papadopoulos (1115200800169) - Nikos Panagiotopoulos (1115200900158)</p>
                    <p style="text-align: center;">
                        <a href="http://www.w3.org/html/logo/">
                            <img src="http://www.w3.org/html/logo/badge/html5-badge-h-css3.png" width="133" height="64" alt="HTML5 Powered with CSS3 / Styling" title="HTML5 Powered with CSS3 / Styling">
                        </a>
                    </p>
                </div>
            </body>
        </html>
<% } %>
