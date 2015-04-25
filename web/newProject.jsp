<%-- 
    Document   : newProject
    Created on : 23 Σεπ 2012, 5:27:25 μμ
    Author     : Labis
--%>

<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Role"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.User"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.ProjectDao"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.UserDao"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Visibility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  UserDao userDao = new UserDao(); 
    User user = (User) request.getSession().getAttribute("user");
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
                        <h1>New Project</h1>
                    </div>
                    <div id="newProject">
                        <form action="./newProject" method="post">
                            <label for="name">Name:</label>
                            <input type="text" id="name" name="name" required="required" maxlength="16" size="16" />
                            <br />
                            <label for="description">Description:</label>
                            <textarea id="description" name="description" required="required" maxlength="256" cols="32" rows="8"></textarea>
                            <br />
                            <label for="visibility">Visibility:</label>
                            <select id="visibility" name="visibility">
                                <% for (Visibility visibility : Visibility.values()) { %>
                                    <option value="<%= visibility %>"><%= visibility %></option>
                                <% } %>
                            </select>
                            <br />
                            <label>Manager:</label>
                            <select id="manager" name="manager">
                                <% for (String manager : userDao.getManagers()) { %>
                                    <option value="<%= manager %>"><%= manager %></option>
                                <% } %>
                            </select>
                            <br />
                            <label>Staff:</label>
                            <!--multiple: gia na borei na epilegei pollous ws staff -->
                            <select id="staff" name="staff" multiple="multiple">
                                <% for (String staff : userDao.getStaff()) { %>
                                    <option value="<%= staff %>"><%= staff %></option>
                                <% } %>
                            </select>
                            <br />
                            <input type="submit" value="Create" class="button" />
                            <input type="reset" value="Clear" class="button" />
                            <input type="button" value="Cancel" class="button" onclick="window.location = './projectAdministration.jsp';" />
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