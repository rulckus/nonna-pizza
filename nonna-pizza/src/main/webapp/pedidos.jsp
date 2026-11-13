<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nonna Pizza - Gerenciar Pedidos</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/admin.css">
</head>
<body>

<div class="layout">

    <!-- ===== MENU LATERAL ===== -->
    <aside class="sidebar">
        <div class="logo">
            <h1>Nonna Pizza</h1>
            <span>Painel Admin</span>
        </div>

        <nav class="menu">
            <a href="/find-all-pizzas">Cardápio</a>
            <a href="/pedidos-adm" class="ativo">Pedidos</a>
            <a href="/producao">Produção</a>
        </nav>

        <div class="usuario">
            <div class="avatar">A</div>
            <div>
                <strong>Admin</strong>
                <a href="/logout">Sair</a>
            </div>
        </div>
    </aside>

    <!-- ===== CONTEÚDO PRINCIPAL ===== -->
    <main class="conteudo">

        <header class="topo">
            <h2>Gerenciar Pedidos</h2>
        </header>

        <label class="busca">
            <svg class="icone" viewBox="0 0 24 24" aria-hidden="true"><circle cx="11" cy="11" r="8"/><path d="M21 21l-4.35-4.35"/></svg>
            <input type="search" placeholder="Pesquisar pedido..." aria-label="Pesquisar pedido" data-filtro=".tabela-pedidos tbody tr">
        </label>

        <section class="card tabela-card">
            <table class="tabela-pedidos">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Cliente</th>
                    <th>Produtos</th>
                    <th>Total</th>
                    <th>Hora</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody id="pedidos">
                <c:forEach var="pedido" items="${pedidos}">
                    <tr>
                        <td>#${pedido.id}</td>
                        <td class="cliente"><c:out value="${pedido.cliente}"/></td>
                        <td class="ingredientes"><c:out value="${pedido.pizzas}"/></td>
                        <td class="total"><fmt:formatNumber value="${pedido.total}" type="currency"/></td>
                        <td>${pedido.horaFormatada}</td>
                        <td>
                            <span class="status status-${fn:toLowerCase(pedido.status)}"><c:out value="${pedido.status}"/></span>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty pedidos}">
                    <tr><td colspan="6" class="estado-vazio">Nenhum pedido recebido ainda.</td></tr>
                </c:if>
                </tbody>
            </table>
        </section>

    </main>
</div>

</body>
</html>