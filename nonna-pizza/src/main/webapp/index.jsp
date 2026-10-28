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
            <a href="/pedidos-adm">Pedidos</a>
            <a href="/producao.html">Produção</a>
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
            <a href="/find-all-pizzas" class="btn btn-primario">+ Novo sabor</a>
        </header>

        <div class="grade">
            <section class="card tabela-card">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Pizza</th>
                        <th>Ingredientes</th>
                        <th>Preço</th>
                        <th>Disponível</th>
                        <th>Excluir</th>
                    </tr>
                    </thead>
                    <tbody id="pizzas">
                    <c:forEach var="pizzas" items="${pizzas}">

                            <tr>
                                <td><a href="find-all-pizzas?id=${pizzas.id}&sabor=${pizzas.sabor}&descricao=${pizzas.descricao}&valor=${pizzas.valor}&tipo=${pizzas.tipo}">${pizzas.id}</a></td>
                                <td>${pizzas.sabor}</td>
                                <td>${pizzas.descricao}</td>
                                <td>${pizzas.valor}</td>
                                <td><button/>Diponivel</td>
                                <form action="/delete-pizza" method="post">
                                <input type="hidden" id="id" name="id" value="${pizzas.id}">
                                <td><button type ="submit">Excluir</button></td>
                                </form>
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
                    <input type="hidden" name="id" id="idPizza" value="${param.id}" required>
                    <label for="sabor">Nome</label>
                    <input type="text" name="sabor" id="sabor" placeholder="Ex: Quatro Queijos" value="${param.sabor}" required>

                    <label for="tipo">Tipo</label>
                    <input type="text" name="tipo" id="tipo" placeholder="Ex: Salgada" value="${param.tipo}" required>

                    <label for="descricao">Ingredientes</label>
                    <textarea name="descricao" id="descricao" value="${param.descricao}" rows="3" placeholder="Ex: Molho, mussarela, tomate, manjericão" required></textarea>

                    <label for="valor">Preço (R$)</label>
                    <input type="number" name="valor" id="valor" step="0.01" min="0.01" placeholder="Ex: 49.90" value="${param.valor}" required>


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
