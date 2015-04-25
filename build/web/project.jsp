<%-- 
    Document   : project
    Created on : 29 Σεπ 2012, 2:00:33 μμ
    Author     : Labis
--%>

<%@page import="gr.uoa.di.ted.std08169.std09158.dao.TaskDao"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Role"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.User"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.ProjectDao"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Project"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Project project = new ProjectDao().getProject(request.getParameter("name")); 
User user = (User) request.getSession().getAttribute("user");
if ((user == null) || (user.getRole() == Role.ADMINISTRATOR))
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
                    <h1>Project '<%= project.getName() %>'</h1>
                </div>
                <form action="./logout" method="post" class="breadcrumbs">
                    You are here: <a href="./projects.jsp">Projects</a> -> <%= project.getName() %>
                    <br />
                    You are logged in as: <%= user.getUsername() %>
                    <br />
                    <input type="submit" value="Logout" class="button" /> <!-- element xwris paidi -->
                </form>
                <div id="projectStory">
                    <div id="projectTable">
                        <h2 style="text-align: center;">Project Info</h2>
                        <table border="1" cellpadding="6" id="tableform2">
                            <tbody>
                                <tr>
                                    <td><strong>Description:</strong></td>
                                    <td><%= project.getDescription() %></td>
                                </tr>
                                <tr>
                                    <td><strong>Visibility:</strong></td>
                                    <td><%= project.getVisibility() %></td>
                                </tr>
                                <tr>
                                    <td><strong>Manager:</strong></td>
                                    <td><%= project.getManager() %></td>
                                </tr>
                                <tr>
                                    <td><strong>Staff:</strong></td>
                                    <td>
                                        <% for(String staff : project.getStaff()) { %>
                                            <%= staff %>
                                        <% } %>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="newTaskButton">
                        <%-- Mono o manager tou project borei na ftiaxei kainouria tasks panw s' auto to project --%>
                        <% if (user.getUsername().equals(project.getManager())) { %>
                            <form action="./newTask.jsp" method="get">
                                <input type="hidden" name="project" value="<%= project.getName() %>" />
                                <input type="submit" value="New Task" class="button" />
                            </form>
                        <% } %>
                    </div>
                    <div id="taskList">
                        <h2>Available Tasks</h2>
                        <ol>
                            <% for (String task : new TaskDao().getTasks(project.getName())) { %>
                                <%-- Apostolh parametrwn project, task stin task.jsp --%>
                                <li><a href="./task.jsp?project=<%= project.getName() %>&amp;task=<%= task %>"><%= task %></a></li>
                            <% } %>
                        </ol>
                    </div>
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