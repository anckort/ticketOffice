<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warehouse</title>
    <%User user = (User) request.getAttribute("user");%>
</head>
<body>
Warehouse
<form action="warehouse" method="get">
<table>
    <c:forEach items="${items}" var="item">
        <tr><td>${item.id}</td><td>${item.name}</td></tr>
    </c:forEach>
</table>
</form>
</body>
</html>
