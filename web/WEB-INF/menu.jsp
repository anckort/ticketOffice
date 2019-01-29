<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <%User user = (User) request.getSession().getAttribute("user");
    String role = user.getRole();
    String visibleType;
    if (role.equals("admin")){
        visibleType = "submit";
    }else{
        visibleType = "hidden";
    }%>
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
            <input type=<%=visibleType%> name="ref" value="Registration" size="500">
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
    <tr>
        <td>
            <input type="submit" name="ref" value="X Report" size="500">
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" name="ref" value="Log out" size="500">
        </td>
    </tr>
</table>
</form>
</body>
</html>
