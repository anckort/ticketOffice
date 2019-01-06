<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <%User user = (User) request.getAttribute("user");%>
</head>
<body>
<form action="menu" method="post">
<table>
    <tr>
        <td>
            <input type="submit" name="ref" value="Warehouse" size="20">
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" name="ref" value="Items" size="20">
        </td>
    </tr>
</table>
</form>
</body>
</html>
