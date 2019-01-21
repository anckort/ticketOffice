<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<title>
    Adding items to warehouse
</title>
<head>

</head>
<body>
<form action="addToWarehouse" method="post">
    <table>
        <tr>
            <td>Code</td>
            <td><input type="text" name="codeOfItem" value=<%= request.getParameter("codeOfItem")%>></td>
            <td><input type="submit" name="btn" value="Find by code"></td>
            <td>Name:</td>
            <td>
                <%
                    Item item = (Item) request.getAttribute("item");
                    if (item != null) {
                        out.println(item.getName());
                        request.setAttribute("item",item);
                    }
                %>
            </td>
            <td><input type="hidden" name="itemID" value=<%= request.getAttribute("itemID")%>></td>
        </tr>
        <tr>
            <td>Count</td>
            <td><input type="text" name="countOfItem"></td>
        </tr>
        <tr>
            <td><input type="submit" name="btn" value="Add" <%request.setAttribute("item",item);%>></td>
            <td><input type="submit" name="btn" value="Cancel"></td>
        </tr>
    </table>
</form>
</body>
</html>