<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nonna Pizza - Produção</title>
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
            <a href="/pedidos-adm">Pedidos</a>
            <a href="/producao" class="ativo">Produção</a>
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
            <h2>Produção — Cozinha</h2>
            <p class="atualizado">
                <svg class="icone" viewBox="0 0 24 24" aria-hidden="true"><circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/></svg>
                Atualizado às <span id="hora">${hora}</span>
            </p>
        </header>

        <div class="quadro">

            <section class="coluna coluna-recebido">
                <h3 class="coluna-topo">Recebido <span class="contador">${tamanhoRecebidos}</span></h3>
                <div class="lista">
                    <c:forEach var="pedidosRecebidos" items="${pedidosRecebidos}">
                        <article class="pedido-card">
                            <div class="pedido-cabecalho">
                                <span>#${pedidosRecebidos.id}</span>
                                <span class="pedido-hora">${pedidosRecebidos.hora}</span>
                            </div>
                            <p class="pedido-cliente"><c:out value="${pedidosRecebidos.cliente}"/></p>
                            <ul class="pedido-itens">
                                <c:forEach var="item" items="${pedidosRecebidos.itens}">
                                    <li><c:out value="${item}"/></li>
                                </c:forEach>
                            </ul>
                            <form action="/atualizar-status" method="post">
                                <input type="hidden" name="id" value="${pedidosRecebidos.id}">
                                <button type="submit" class="btn btn-avancar">→ Avançar</button>
                            </form>
                        </article>
                    </c:forEach>
                </div>
            </section>

            <section class="coluna coluna-preparando">
                <h3 class="coluna-topo">Preparando <span class="contador">${tamanhoPreparando}</span></h3>
                <div class="lista">
                    <c:forEach var="pedidosPreparando" items="${pedidosPreparando}">
                        <article class="pedido-card">
                            <div class="pedido-cabecalho">
                                <span>#${pedidosPreparando.id}</span>
                                <span class="pedido-hora">${pedidosPreparando.hora}</span>
                            </div>
                            <p class="pedido-cliente"><c:out value="${pedidosPreparando.cliente}"/></p>
                            <ul class="pedido-itens">
                                <c:forEach var="item" items="${pedidosPreparando.itens}">
                                    <li><c:out value="${item}"/></li>
                                </c:forEach>
                            </ul>
                            <form action="/atualizar-status" method="post">
                                <input type="hidden" name="id" value="${pedidosPreparando.id}">
                                <button type="submit" class="btn btn-avancar">→ Avançar</button>
                            </form>
                        </article>
                    </c:forEach>
                </div>
            </section>

            <section class="coluna coluna-assando">
                <h3 class="coluna-topo">Assando <span class="contador">${tamanhoAssando}</span></h3>
                <div class="lista">
                    <c:forEach var="pedidosAssando" items="${pedidosAssando}">
                        <article class="pedido-card">
                            <div class="pedido-cabecalho">
                                <span>#${pedidosAssando.id}</span>
                                <span class="pedido-hora">${pedidosAssando.hora}</span>
                            </div>
                            <p class="pedido-cliente"><c:out value="${pedidosAssando.cliente}"/></p>
                            <ul class="pedido-itens">
                                <c:forEach var="item" items="${pedidosAssando.itens}">
                                    <li><c:out value="${item}"/></li>
                                </c:forEach>
                            </ul>
                            <form action="/atualizar-status" method="post">
                                <input type="hidden" name="id" value="${pedidosAssando.id}">
                                <button type="submit" class="btn btn-avancar">→ Avançar</button>
                            </form>
                        </article>
                    </c:forEach>
                </div>
            </section>

            <section class="coluna coluna-pronto">
                <h3 class="coluna-topo">Pronto para entrega <span class="contador">${tamanhoPronto}</span></h3>
                <div class="lista">
                    <c:forEach var="pedidosPronto" items="${pedidosPronto}">
                        <article class="pedido-card">
                            <div class="pedido-cabecalho">
                                <span>#${pedidosPronto.id}</span>
                                <span class="pedido-hora">${pedidosPronto.hora}</span>
                            </div>
                            <p class="pedido-cliente"><c:out value="${pedidosPronto.cliente}"/></p>
                            <ul class="pedido-itens">
                                <c:forEach var="item" items="${pedidosPronto.itens}">
                                    <li><c:out value="${item}"/></li>
                                </c:forEach>
                            </ul>
                            <form action="/atualizar-status" method="post">
                                <input type="hidden" name="id" value="${pedidosPronto.id}">
                                <button type="submit" class="btn btn-avancar">Finalizar</button>
                            </form>
                        </article>
                    </c:forEach>
                </div>
            </section>

        </div>
    </main>
</div>


</body>
</html>