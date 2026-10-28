<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <a href="producao.html">Produção</a>
        </nav>

        <div class="usuario">
            <div class="avatar">A</div>
            <div>
                <strong>Admin</strong>
                <a href="logout">Sair</a>
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
                <c:forEach var="pedidos" items="${pedidos}">

                    <tr>
                        <td>${pedidos.id}</td>
                        <td>${pedidos.cliente}</td>
                        <td>${pedidos.produtos}</td>
                        <td>${pedidos.total}</td>
                        <td>${pedidos.hora}</td>
                        <td>${pedidos.status}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </section>

    </main>
</div>

<script src="js/app.js"></script>
<script src="js/admin-pedidos.js"></script>
<script src="js/filtro.js"></script>
</body>
</html>