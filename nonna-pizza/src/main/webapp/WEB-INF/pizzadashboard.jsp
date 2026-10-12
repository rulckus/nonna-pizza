<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>
  <div>
    <h1>Pizzas</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
        </tr>
        <c:forEach var="pizzas" items="${pizzas}">
            <tr>
                <td></td>
                <td>${pizzas.sabor}</td>
            </tr>
        </c:forEach>
    </table>
  </div>
</body>
</html>