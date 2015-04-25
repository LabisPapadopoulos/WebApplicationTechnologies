<%-- 
    Document   : userAdministration
    Created on : 20 Σεπ 2012, 9:44:56 μμ
    Author     : Labis
--%>

<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Role"%>
<%@page import="java.util.List"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.User"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% User user = (User) request.getSession().getAttribute("user");
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
                    <h1>User Administration</h1>
                </div>
                <form action="./logout" method="post" class="breadcrumbs">
                    You are here: <a href="./administration.jsp">Administration</a> -> User Administration
                    <br />
                    You are logged in as: <%= user.getUsername() %>
                    <br />
                    <input type="submit" value="Logout" class="button" /> <!-- element xwris paidi -->
                </form>
                    <h2 style="text-align: center;">Available Users</h2>
                <% List<User> users = new UserDao().getUsers(); %>
                <table border="1" cellpadding="5" cellspacing="1" id="tableform">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>e-mail</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for( User u : users ) { %>
                            <tr>
                                <td><%= u.getUsername() %></td>
                                <td><%= u.getName() %></td>
                                <td><%= u.getSurname() %></td>
                                <td><%= u.getEmail() %></td>
                                <td>
                                    <form method="post" action="./changeRole" id="changeRoleForm-<%= u.getUsername() %>">
                                        <input type="hidden" name="username" value="<%= u.getUsername() %>" />
                                        <%-- Ginetai automath enhmerwsh tou rolou tou xrhsth me tin epilogh apo tin lista --%>
                                        <select name="role" onchange="document.getElementById('changeRoleForm-<%= u.getUsername() %>').submit();">
                                            <% for (Role role : Role.values()) { %>
                                                <%-- An o rolos tairiazei me ton rolo tou xrhstsh tote emfanizetai ws epilegmenos --%>
                                                <option value="<%= role %>"<%= (role == u.getRole()) ? " selected=\"selected\"" : "" %>><%= role %></option>
                                            <% } %>
                                        </select>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="5"><%= users.size() %> users found</td>
                        </tr>
                    </tfoot>
                </table>
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