<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>
    Adding items to warehouse
</title>
<head>

</head>
<body>
<form action="addToWarehouse" method="post" >
<table>
    <tr>
        <td>Code</td><td><input type="text" name="codeOfItem" onchange=""></td>
        <td>Name</td><td><input type="text" name="nameOfItem"></td>
    </tr>
    <tr>
        <td>Count</td><td><input type="text" name="countOfItem"></td>
    </tr>
    <tr>
        <td><input type="submit" name="btn" value="Add"></td>
        <td><input type="submit" name="btn" value="Cancel"></td>
    </tr>
</table>
</form>
</body>
</html>