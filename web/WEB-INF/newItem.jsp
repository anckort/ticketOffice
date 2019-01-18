<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding a new item</title>
    <%User user = (User) request.getAttribute("user");%>
</head>
<body>
<form action="newItem" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Code:</td>
            <td><input type="text" name="code"></td>
        </tr>
        <tr>
            <td><input type="submit" name="btn" value="Add"></td>
            <td><input type="submit" name="btn" value="Cancel"></td>
        </tr>
    </table>
</form>
</body>
</html>