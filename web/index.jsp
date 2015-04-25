<%-- 
    Document   : index
    Created on : 15 Σεπ 2012, 11:43:50 πμ
    Author     : Labis
--%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.Role"%>
<%@page import="gr.uoa.di.ted.std08169.std09158.domain.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //To session einai tou trexontos xrhsth - client.
    User user = (User) request.getSession().getAttribute("user");
    //xwris login 'h an den einai administrator phgainei stin arxikh selida
    if (user != null) {
        if (user.getRole() == Role.ADMINISTRATOR)
            response.sendRedirect("./administration.jsp");
        else
            response.sendRedirect("./projects.jsp");
    } else { %>
        <!DOCTYPE html>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="style.css" />
                <title>Project Management Web Application</title>
            </head>
            <body>
                <div id="wrapper" >
                    <div id="header">
                        <h1>Project Management Web Application</h1>
                    </div>
                    <div id="imgage">
                        <img src="./project-management.jpg" alt="pic1" />
                    </div>
                    <% String error = request.getParameter("error"); 
                        if((error != null) && error.equals("user")) { %>
                        <p id="error">Error in username or password</p>
                     <% } %>
                     <div id="register">
                        <form action="./register.jsp" method="get">
                            <fieldset>
                                <legend>New user? Register here:</legend>
                                <input type="submit" value="Register" class="button" />
                            </fieldset>
                        </form>
                    </div>
                     <div id="login">
                        <form action="./login" method="post">
                            <fieldset>
                                <legend>If you already have an account, login here:</legend>
                                <label for="username">Username:</label>
                                <input id="username" type="text" name="username" required="required" maxlength="16" size="16" />
                                <br />
                                <label for="password">Password:</label>
                                <input id="password" type="password" name="password" required="required" maxlength="16" size="16" />
                                <br />
                                <input type="submit" value="Login" class="button" />
                                <input type="reset" value="Clear" class="button" />
                            </fieldset>
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