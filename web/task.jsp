<%-- 
    Document   : task
    Created on : 30 Σεπ 2012, 3:01:54 μμ
    Author     : Labis
--%>

<%@page import="gr.uoa.di.ted.std08169.std09158.domain.State"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Project"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.ProjectDao"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Role"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.User"%>
<%@page import="java.util.List"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Comment"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Task"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.TaskDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User user = (User) session.getAttribute("user");
if ((user == null) || (user.getRole() == Role.ADMINISTRATOR)) {
    response.sendRedirect("./index.jsp");
} else {
    String projectName = request.getParameter("project");
    ProjectDao projectDao = new ProjectDao();
    Project project = projectDao.getProject(projectName);
    TaskDao taskDao = new TaskDao(); 
    Task task = taskDao.getTask(projectName, request.getParameter("task")); %>
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
                    <h1>Task '<%= task.getName() %>' of Project '<%= task.getProject() %>'</h1>
                </div>
                <form action="./logout" method="post" class="breadcrumbs">
                    You are here: <a href="./projects.jsp">Projects</a> -> <a href="./project.jsp?name=<%= project.getName() %>"><%= project.getName() %></a> -> <%= task.getName() %>
                    <br />
                    You are logged in as: <%= user.getUsername() %>
                    <br />
                    <input type="submit" value="Logout" class="button" /> <!-- element xwris paidi -->
                </form>
                <div id="taskStory">
                    <div id="taskTable">
                        <h2 style="text-align: center;">Task Info</h2>
                        <table border="1" cellpadding="6" id="tableform2">
                            <tr>
                                <td><strong>Description:</strong></td>
                                <td><%= task.getDescription() %></td>
                            </tr>
                            <tr>
                                <td><strong>Start Date:</strong></td>
                                <td><%= task.getStartDate() %></td>
                            </tr>
                            <tr>
                                <td><strong>End Date:</strong></td>
                                <td><%= task.getEndDate() %></td>
                            </tr>
                            <tr>
                                <td><strong>State:</strong></td>
                                <td>
                                    <%//an sti lista autwn pou douleuoun emperiexetai o xrhsths me sugkekrimeno username
                                    if (task.getWorks().contains(user.getUsername()) || // mono osoi douleuoun s' auto to task
                                            (user.getUsername().equals(project.getManager())) ) { //'h o manager tou project allazoun to state %>
                                        <form action="./editTask" method="post" id="editTask" style="display: inline;">
                                            <input type="hidden" name="project" value="<%= task.getProject() %>" />
                                            <input type="hidden" name="task" value="<%= task.getName() %>" />
                                            <select name="state" onchange="document.getElementById('editTask').submit();">
                                                <% for (State state : State.values()) { %>
                                                <option value="<%= state %>"<%= (state == task.getState()) ? "selected=\"selected\"" : "" %>><%= state %></option>
                                                <% } %>
                                            </select>
                                        </form>
                                    <% } else {%>
                                        <%= task.getState() %>
                                    <% } %>
                                </td>
                            </tr>
                            <tr>
                                <td><strong>Works:</strong></td>
                                <td>
                                    <% for (String username : task.getWorks()) { %>
                                        <%= username %>
                                    <% } %>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div id="comment">
                        <%//an sti lista autwn pou douleuoun emperiexetai o xrhsths me sugkekrimeno username
                        if (task.getWorks().contains(user.getUsername()) || // mono osoi douleuoun s' auto to task
                            (user.getUsername().equals(project.getManager())) ) { //'h o manager tou project vazoun sxolia  %>
                            <form action="./newComment" method="post">
                                <h2 style="text-align: center;">Add Comment:</h2>
                                <%-- Perasma parametrou project kai task sto servlet newComment --%>
                                <input type="hidden" name="project" value="<%= task.getProject() %>" />
                                <input type="hidden" name="task" value="<%= task.getName() %>" />
                                <textarea name="text" required="required" maxlength="256" cols="32" rows="8"></textarea>
                                <br />
                                <input type="submit" value="Add Comment" class="button" />
                                <input type="reset" value="Clear" class="button" />
                            </form>
                        <% } %>
                    </div>
                    <div id="commentTable">
                        <h2 style="text-align: center;">Comments</h2>
                        <table border="1" cellpadding="5" id="tableform3">
                            <thead>
                                <tr>
                                    <th>User</th>
                                    <th>Date</th>
                                    <th>Text</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% List<Comment> comments = taskDao.getComments(task.getProject(), task.getName());
                                for (Comment comment : comments) { %>
                                    <tr>
                                        <td><%= comment.getUser() %></td>
                                        <td><%= comment.getDate() %></td>
                                        <td><%= comment.getText() %></td>
                                    </tr>
                                <% } %>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="3">Found <%= comments.size() %> comments</td>
                                </tr>
                            </tfoot>
                        </table>
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