<%@ page import="entity.CashDeskItem" %>
<%@ page import="entity.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>
    Cancellation
</title>
<head>

</head>
<body>
<form action="cancellation" method="post">
<table>
    <tr>
        <td>Input number of ticket</td>
        <td><input type="text" name="ticketNumber"></td>
        <td><input type="submit" name="btn" value="Find"></td>
    </tr>
    <tr>
        <td><input type="submit" name="btn" value="Cancel"></td>
        <td><input type="submit" name="btn" value="Cancel all"></td>
    </tr>
</table>

<table border="1">
    <thead>
    <tr>
        <th></th>
        <th>Name</th>
        <th>Count</th>

    </tr>
    </thead>
    <tbody>

    <c:set var="listOfItems" value="${listOfItems}" scope="session"/>
    <c:forEach var="item" items="${listOfItems}">
        <tr>
            <td><input type="checkbox" name="id" value="${item.getID()}"></td>
            <td> ${item.getItem().getName()}</td>
            <td> ${item.getCount()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</form>
</body>
</html>