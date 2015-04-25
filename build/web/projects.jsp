<%-- 
    Document   : projects
    Created on : 20 Σεπ 2012, 6:18:38 μμ
    Author     : Labis
--%>

<%@page import="gr.uoa.di.ted.std08169.std09158.dao.TaskDao"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.TaskSummary"%>
<%@page import="java.util.List"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Role"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.User"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.ProjectDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  //pairnw ton user apo to session
    User user = (User) request.getSession().getAttribute("user");
    if ((user == null) || (user.getRole() == Role.ADMINISTRATOR))
        response.sendRedirect("./index.jsp");
    else {
        ProjectDao projectDao = new ProjectDao();  %>
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
                        <h1>Projects</h1>
                    </div>
                    <form action="./logout" method="post" class="breadcrumbs">
                            You are here: Projects
                            <br />
                            You are logged in as: <%= user.getUsername() %>
                            <br />
                        <input type="submit" value="Logout" class="button" /> <!-- element xwris paidi -->
                    </form>
                    <div id="projects">
                        <div id="myProjects">
                            <% List<String> projects = projectDao.getUserProjects(user.getUsername());
                            //an uparxoun project mesa stin lista sta opoia o 
                            //sugkekrimenos xrhsths douleuei 'h einai manager tote ta emfanzei
                            if (projects.size() > 0) { %>
                                <h2>My Projects</h2>
                                <ol>
                                    <% for (String project : projects) { %>
                                        <%-- project.jsp?name=project: pernietai ws parametros to kathe project sti selida project.jsp --%>
                                        <li><a href="./project.jsp?name=<%= project%>"><%= project %></a></li>
                                    <% } %>
                                </ol>
                            <% } else { %>
                                <h2>You have no projects</h2>
                            <% } %>
                        </div>
                        <div id="publicProjects">
                            <% List<String> publicProjects = projectDao.getPublicProjects(user.getUsername());
                            if (publicProjects.size() > 0) { %>
                                <h2>Public Projects</h2>
                                <ol>
                                    <% for (String project : projectDao.getPublicProjects(user.getUsername())) { %>
                                        <%-- project.jsp?name=project: pernietai ws parametros to kathe project sti selida project.jsp --%>
                                        <li><a href="./project.jsp?name=<%= project%>"><%= project %></a></li>
                                    <% } %>
                                </ol>
                            <% } else { %>
                                <h2>There are no public projects</h2>
                            <% } %>
                        </div>
                        <div id="myTasks">
                            <% TaskDao taskDao = new TaskDao(); 
                            List<TaskSummary> taskSummaries = taskDao.getUserTasks(user.getUsername()); 
                            //An o sugkekrimenos xrhsths exei tasks tote tou emfanizontai
                            if (taskSummaries.size() > 0) { %>
                                <h2>My Tasks</h2>
                                <ol>
                                    <% for (TaskSummary taskSummary : taskSummaries) { %>
                                    <li><a href="./task.jsp?project=<%= taskSummary.getProject() %>&amp;task=<%= taskSummary.getName() %>"><%= taskSummary.getName() %></a></li>
                                    <% } %>
                                </ol>
                            <% } else { %>
                            <h2>You have no tasks</h2>
                            <% } %>
                        </div>
                    </div>
                </div> <!-- end of wrapper -->
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