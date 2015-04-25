<%-- 
    Document   : register
    Created on : 15 Σεπ 2012, 1:03:17 μμ
    Author     : Labis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css" />
        <title>Project Management Web Application</title>
    </head>
    <body>
        <div id="wrapper" >
            <div id="register">
                <div id="header">
                    <h1>Register</h1>
                </div>
                <%-- Apo to servlet Register elenxei gia tuxon error kai emfanizei to analogo mhnuma --%>
                <% String error = request.getParameter("error");
                if ((error != null) && error.equals("username")) { %>
                    <p>Username already exists</p>
                <% } else if ((error != null) && error.equals("password")) { %>
                    <p>Passwords do not match</p>
                <% } %>
                <form action="./register" method="post">
                    <fieldset>
                        <label for="username">Username:</label>
                        <input id="username" type="text" name="username" required="required" maxlength="16" size="16" />
                        <br />
                        <label for="password">Password:</label>
                        <input id="password" type="password" name="password" required="required" maxlength="16" size="16" />
                        <br />
                        <label for="confirmPassword">Confirm password:</label>
                        <input id="confirmPassword" type="password" name="confirmPassword" required="required" maxlength="16" size="16" />
                        <br />
                        <br />
                        <label for="name">Name:</label>
                        <input id="name" type="text" name="name" required="required" maxlength="20" size="20" />
                        <br />
                        <label for="surname">Surname:</label>
                        <input id="surname" type="text" name="surname" required="required" maxlength="30" size="30" />
                        <br />
                        <label for="email">Email:</label>
                        <input id="email" type="email" name="email" required="required" maxlength="30" size="30" />
                        <br />
                        <input type="submit" value="Register" class="button" />
                        <input type="reset" value="Clear" class="button" />
                        <input type="button" value="Cancel" class="button" onclick="window.location = './index.jsp';" />
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
