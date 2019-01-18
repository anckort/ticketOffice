<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <form action="registration" method="post">
        <tr>
            <td>User name:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>Role:</td>
            <td><input type="text" name="role"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="btn" value="Save">
            </td>
        </tr>
    </form>
</table>
</body>
</html>