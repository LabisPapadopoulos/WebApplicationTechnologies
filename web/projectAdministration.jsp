<%-- 
    Document   : projectAdministration
    Created on : 20 Σεπ 2012, 9:46:20 μμ
    Author     : Labis
--%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Role"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.User"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.ProjectDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ProjectDao projectDao = new ProjectDao();
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
                        <h1>Project Administration</h1>
                    </div>
                    <form action="./logout" method="post" class="breadcrumbs">
                        You are here: <a href="./administration.jsp">Administration</a> -> Project Administration
                        <br />
                        You are logged in as: <%= user.getUsername() %>
                        <br />
                        <input type="submit" value="Logout" class="button" /> <!-- element xwris paidi -->
                    </form>
                    <div id="projectAdmin"> 
                        <div id="availableProjects">
                            <h2>Available Projects</h2>
                            <ol>
                                <% for (String project : projectDao.getProjects()) { %>
                                <li><a href="./adminProject.jsp?name=<%= project %>"><%= project %></a></li>
                                <% } %>
                            </ol>
                        </div>
                        <!-- methodos get giati apla pame se allh selida, den stelnei dedomena
                        pros apothikeush-->
                        <div id="newProjectButton">
                            <form action="./newProject.jsp" method="get" >
                                <input type="submit" value="New Project" class="button" />
                            </form>
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