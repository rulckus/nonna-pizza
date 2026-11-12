<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produtos - Nonna Pizza</title>
    <link rel="stylesheet" href="css/base.css">
</head>
<body>
<main>
    <h1>Produtos</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Excluir</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${produtos}">
                <tr>
                    <td>${item.id}</td>
                    <td><c:out value="${item.nome}"/></td>
                    <td>
                        <form action="/delete-product" method="post">
                            <input type="hidden" name="id" value="${item.id}">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>