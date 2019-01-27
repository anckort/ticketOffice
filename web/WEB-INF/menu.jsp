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
            <input type="submit" name="ref" value="Warehouse" size="500">
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" name="ref" value="Items" size="500">
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" name="ref" value="Registration" size="500">
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" name="ref" value="Cash desk" size="500">
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" name="ref" value="Cancellation" size="500">
        </td>
    </tr>
</table>
</form>
</body>
</html>
