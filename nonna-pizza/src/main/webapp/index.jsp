<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
            <h2>Gerenciar Cardápio</h2>
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
                    <c:forEach var="pizza" items="${pizzas}">
                        <%-- c:url codifica acentos e & dos parâmetros --%>
                        <c:url var="urlEditar" value="/find-all-pizzas">
                            <c:param name="id" value="${pizza.id}"/>
                            <c:param name="sabor" value="${pizza.sabor}"/>
                            <c:param name="descricao" value="${pizza.descricao}"/>
                            <c:param name="valor" value="${pizza.valor}"/>
                            <c:param name="tipo" value="${pizza.tipo}"/>
                        </c:url>
                        <tr>
                            <td><a href="${urlEditar}">${pizza.id}</a></td>
                            <td><c:out value="${pizza.sabor}"/></td>
                            <td><c:out value="${pizza.descricao}"/></td>
                            <td>${pizza.valor}</td>

                            <td>
                                <form action="atualizar-disponibilidade" method="post">
                                <label class="switch">
                                    <input type="hidden" name="id" value="${pizza.id}">

                                    <input type="checkbox" name="disponivel" value="true"
                                           ${pizza.disponibilidade ? 'checked' : ''}
                                           onchange="this.form.submit()">
                                           <span></span>
                                    </label>
                                </form>
                            </td>
                            <td>
                                <form action="/delete-pizza" method="post">
                                    <input type="hidden" name="id" value="${pizza.id}">
                                    <button type="submit">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty pizzas}">
                        <tr><td colspan="6" class="estado-vazio">Nenhuma pizza cadastrada.</td></tr>
                    </c:if>
                    </tbody>
                </table>
            </section>

            <!-- ===== FORMULÁRIO (envia para o PizzaServlet) ===== -->
            <section class="card painel">
                <div class="painel-topo">
                    <h3>Novo sabor</h3>
                    <span class="tag">Cadastro</span>
                </div>

                <form action="/create-pizza" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" id="idPizza" value="${param.id}">
                    <label for="sabor">Nome</label>
                    <input type="text" name="sabor" id="sabor" placeholder="Ex: Quatro Queijos" value="<c:out value="${param.sabor}"/>" required>

                    <label for="tipo">Tipo</label>
                    <input type="text" name="tipo" id="tipo" placeholder="Ex: Salgada" value="<c:out value="${param.tipo}"/>" required>

                    <label for="descricao">Ingredientes</label>
                    <textarea name="descricao" id="descricao" rows="3" placeholder="Ex: Molho, mussarela, tomate, manjericão" required><c:out value="${param.descricao}"/></textarea>

                    <label for="valor">Preço (R$)</label>
                    <input type="number" name="valor" id="valor" step="0.01" min="0.01" placeholder="Ex: 49.90" value="${param.valor}" required>

                    <label> Foto </label>
                    <input type="file" name="image" id="image">


                    <button type="submit" class="btn btn-primario">Salvar sabor</button>
                </form>
            </section>
        </div>
    </main>
</div>



</body>
</html>