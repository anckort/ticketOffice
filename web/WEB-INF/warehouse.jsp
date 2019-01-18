<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User" %>
<%@ page import="entity.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warehouse</title>
    <%User user = (User) request.getAttribute("user");%>
</head>
<body>
<form action="warehouse" method="get">
    <input type="submit" name="btn", value="To menu">
    <table>
        <tr>
            <td><input type="submit" name="btn" value="Add item to warehouse"></td>
        </tr>
    </table>
    <table border="1">
        <thead>
        <tr>
            <%--<th></th>--%>
            <%--<th>#</th>--%>
            <th>Name</th>
            <th>Count</th>
            <th  style="visibility:hidden;">Item</th>
        </tr>
        </thead>
        <tbody>
        <% ArrayList<Item> listOfItems = (ArrayList <Item>) request.getAttribute("listOfItems");%>
        <c:forEach var="item" items="${listOfItems}">
            <tr>
                <%--<td><input type="checkbox" name="id" value="${item.getId()}"></td>--%>
                <td> ${item.getItem().getName()}</td>
                <td> ${item.getCount()}</td>
                <td style="visibility:hidden;"> ${item.getItem()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
