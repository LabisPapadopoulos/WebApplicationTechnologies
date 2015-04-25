<%-- 
    Document   : newTask
    Created on : 29 Σεπ 2012, 11:51:32 μμ
    Author     : Labis
--%>

<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Role"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.User"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.ProjectDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String project = request.getParameter("project"); 
    User user = (User) request.getSession().getAttribute("user");
    //xwris login 'h an den einai manager phgainei stin arxikh selida
    if ((user == null) || (user.getRole() != Role.MANAGER))
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
                        <h1>New Task</h1>
                    </div>
                    <div id="newTask">
                        <form action="./newTask" method="post">
                            <input type="hidden" name="project" value="<%= project %>" />
                            <label for="name">Name:</label>
                            <input type="text" id="name" name="name" required="required" maxlength="16" size="16" />
                            <br />
                            <label for="description">Description:</label>
                            <textarea id="description" name="description" required="required" maxlength="256" cols="32" rows="8"></textarea>
                            <br />
                            <label for="startDate">Start Date:</label>
                            <input type="date" name="startDate">
                            <br />
                            <label for="endDate">End Date:</label>
                            <input type="date" name="endDate">
                            <br />
                            <label>Staff:</label>
                            <select id="staff" name="staff" multiple="multiple">
                                <% ProjectDao projectDao = new ProjectDao(); 
                                //emfanizei to staff pou douleuei sto sugkekrimeno project
                                for(String staff : projectDao.getStaff(project)) { %>
                                    <option value="<%= staff %>"><%= staff %></option>
                                <% } %>
                            </select>
                            <br />
                            <input type="submit" value="Create" class="button" />
                            <input type="reset" value="Clear" class="button" />
                            <input type="button" value="Cancel" class="button" onclick="window.location = './project.jsp?name=<%= project %>';" />
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