<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nonna Pizza - Painel Admin</title>
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
            <a href="/find-all-pizzas" class="ativo">Cardápio</a>
            <a href="pedidos.html">Pedidos</a>
            <a href="producao.html">Produção</a>
        </nav>

        <div class="usuario">
            <div class="avatar">A</div>
            <div>
                <strong>Admin</strong>
                <a href="landing.html">Sair</a>
            </div>
        </div>
    </aside>

    <!-- ===== CONTEÚDO PRINCIPAL ===== -->
    <main class="conteudo">
        <header class="topo">
            <h2>Gerenciar Cardapio</h2>
            <a href="#sabor" class="btn btn-primario">+ Novo sabor</a>
        </header>

        <div class="grade">
            <!-- ===== TABELA DE PIZZAS (preenchida por js/admin-cardapio.js) ===== -->
            <section class="card tabela-card">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Pizza</th>
                        <th>Ingredientes</th>
                        <th>Preço</th>
                        <th>Disponível</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody id="pizzas">
                    <c:forEach var="pizzas" items="${pizzas}">
                        <tr>
                            <td>${pizzas.id}</td>
                            <td>${pizzas.sabor}</td>
                            <td>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </section>

            <!-- ===== FORMULÁRIO (envia para o PizzaServlet) ===== -->
            <section class="card painel">
                <div class="painel-topo">
                    <h3>Novo sabor</h3>
                    <span class="tag">Cadastro</span>
                </div>

                <form action="create-pizza" method="post">
                    <label for="sabor">Nome</label>
                    <input type="text" name="sabor" id="sabor" placeholder="Ex: Quatro Queijos" required>

                    <label for="tipo">Tipo</label>
                    <input type="text" name="tipo" id="tipo" placeholder="Ex: Salgada" required>

                    <label for="descricao">Ingredientes</label>
                    <textarea name="descricao" id="descricao" rows="3" placeholder="Ex: Molho, mussarela, tomate, manjericão" required></textarea>

                    <label for="valor">Preço (R$)</label>
                    <input type="number" name="valor" id="valor" step="0.01" min="0.01" placeholder="Ex: 49.90" required>

                    <button type="submit" class="btn btn-primario">Salvar sabor</button>
                </form>
            </section>
        </div>
    </main>
</div>

<script src="js/app.js"></script>
<script src="js/admin-cardapio.js"></script>
</body>
</html>
