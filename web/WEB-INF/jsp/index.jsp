<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>User Login</title>
    </head>
    <body>
        <form action="login" method="post">
            <table>
                <tr>
                    <td>UserName:</td>
                    <td><input type="text" name="u_name"/>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="u_pass"/>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Login"/>
                        <form action = "signup"></form>
                </tr>
            </table>
        </form>
        <form action="signup" method="post">
            <table>     
                <tr>
                    <td colspan="1" align="center"><input type="submit" value="Sign Up"/> 
                </tr>
            </table>
        </form>
    </body>
</html>
