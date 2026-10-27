<!DOCTYPE html>
<html lang="pt-BR" xmlns:fmt="">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="pt_BR"/>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seu Pedido - Nonna Pizza</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,wght@0,400;0,500;0,600;0,700;1,400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/cliente.css">
</head>
<body class="pagina-cliente">

<header class="cabecalho">
    <div class="container">
        <a href="landing.html" class="marca">
            <span class="marca-selo" aria-hidden="true">N</span>
            <span>Nonna<small>PIZZA</small></span>
        </a>
        <nav class="menu-topo" aria-label="Principal">
            <a href="/cardapio">Cardápio</a>
            <a href="/historico">Meus Pedidos</a>
            <a href="/endereco">Minha Conta</a>
            <a href="/carrinho" class="carrinho-link" aria-label="Carrinho, 3 itens" aria-current="page">
                <svg class="icone" viewBox="0 0 24 24" aria-hidden="true"><circle cx="9" cy="21" r="1"/><circle cx="20" cy="21" r="1"/><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"/></svg>
                <span class="carrinho-qtd">3</span>
            </a>
        </nav>
    </div>
</header>

<main>
    <div class="container">
        <h1 class="titulo-pagina">Seu Pedido</h1>

        <div class="duas-colunas">
            <section aria-label="Itens do pedido">
                <div class="lista-carrinho">
                  <c:choose>
                    <c:when test="${empty carrinho or carrinho.vazio}">
                        <p>Seu carrinho está vazio.</p>
                    </c:when>
                    <c:otherwise>
                      <c:forEach var="item" items="${carrinho.itens}">
                        <article class="item-carrinho">
                            <div class="item-info">
                                <h3><c:out value="${item.nome}"/></h3>
                                <span><fmt:formatNumber value="${item.preco}" type="currency"/></span>
                            </div>

                            <form action="carrinho" method="post" class="item-quantidade">
                                <input type="hidden" name="tipo" value="${item.tipo}">
                                <input type="hidden" name="id" value="${item.id}">
                                <button name="acao" value="menos" aria-label="Diminuir">−</button>
                                <span>${item.quantidade}</span>
                                <button name="acao" value="mais" aria-label="Aumentar">+</button>
                                <button name="acao" value="remover" aria-label="Remover">✕</button>
                            </form>

                            <strong><fmt:formatNumber value="${item.subtotal}" type="currency"/></strong>
                        </article>
                      </c:forEach>
                    </c:otherwise>
                  </c:choose>
                </div>

                <a href="cardapio.jsp" class="adicionar-itens">+ Adicionar mais itens</a>
            </section>

            <aside class="resumo">
                <h2>Resumo do Pedido</h2>
                <dl>
                    <div class="linha-resumo linha-total">
                        <dt>Total</dt>
                        <dd id="total"></dd>
                    </div>
                </dl>
                <a href="/checkout" id="continuar" class="btn btn-primario btn-grande">Continuar para entrega</a>
                <a href="cardapio.jsp" class="voltar">← Voltar ao cardápio</a>
            </aside>
        </div>
    </div>
</main>

<footer class="rodape">
    <div class="container">
        <a href="landing.html" class="marca">
            <span class="marca-selo" aria-hidden="true">N</span>
            Nonna Pizza
        </a>
        <p>&copy; 2026 Nonna Pizza. Todos os direitos reservados.</p>
        <nav aria-label="Rodapé">
            <a href="#">Privacidade</a>
            <a href="#">Termos</a>
        </nav>
    </div>
</footer>

<script src="js/app.js"></script>
<script src="js/carrinho.js"></script>
</body>
</html>