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
                <td>${pizzas.id}</td>
                <td>${pizzas.sabor}</td>
                <td>
                     <form action="/delete-pizza" method="post">
                        <input type="hidden" id="id" name="id" value="${pizzas.id}">
                        <button type="submit">Delete</button>
                     </form>
                </td>
            </tr>
        </c:forEach>
    </table>


    <h1>Enderecos</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Numero</th>
            </tr>
            <c:forEach var="enderecos" items="${enderecos}">
                <tr>
                    <td>${enderecos.id}</td>
                    <td>${enderecos.rua}</td>
                    <td>${enderecos.numero}</td>

                    <td>
                      <form action="/delete-endereco" method="post">
                         <input type="hidden" id="id" name="id" value="${enderecos.id}">
                         <button type="submit">Delete</button>
                       </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
  </div>
</body>
</html>