<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Histórico de Pedidos - Nonna Pizza</title>
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
            <a href="/historico" aria-current="page">Meus Pedidos</a>
            <a href="/enderecos">Minha Conta</a>
            <a href="/carrinho" class="carrinho-link" aria-label="Carrinho, ${empty carrinho ? 0 : carrinho.quantidadeTotal} itens">
                <svg class="icone" viewBox="0 0 24 24" aria-hidden="true"><circle cx="9" cy="21" r="1"/><circle cx="20" cy="21" r="1"/><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"/></svg>
                <span class="carrinho-qtd">${empty carrinho ? 0 : carrinho.quantidadeTotal}</span>
            </a>
        </nav>
    </div>
</header>

<main>
    <div class="container">
        <h1 class="titulo-pagina titulo-italico">Histórico de Pedidos</h1>

        <ul class="lista-pedidos" id="pedidos">
            <c:forEach var="pedido" items="${pedidos}">
                <%-- data vem como 2026-10-29T18:30:12: separa dia, mês, ano e hora --%>
                <c:set var="d" value="${pedido.data}"/>
                <li class="pedido">
                    <div class="pedido-numero">
                        #${pedido.id}
                        <time>${fn:substring(d,8,10)}/${fn:substring(d,5,7)}/${fn:substring(d,0,4)} ${fn:substring(d,11,16)}</time>
                    </div>
                    <span></span>
                    <span class="pedido-total"><fmt:formatNumber value="${pedido.valor}" type="currency"/></span>
                    <span class="selo selo-${fn:toLowerCase(pedido.status)}">
                        <c:choose>
                            <c:when test="${pedido.status == 'RECEBIDO'}">Recebido</c:when>
                            <c:when test="${pedido.status == 'PREPARANDO'}">Preparando</c:when>
                            <c:when test="${pedido.status == 'ASSANDO'}">Assando</c:when>
                            <c:when test="${pedido.status == 'PRONTO'}">Pronto</c:when>
                            <c:when test="${pedido.status == 'ENTREGUE' or pedido.status == 'FINALIZADO'}">Entregue</c:when>
                            <c:otherwise><c:out value="${pedido.status}"/></c:otherwise>
                        </c:choose>
                    </span>
                </li>
            </c:forEach>
            <c:if test="${empty pedidos}">
                <li class="estado-vazio">Você ainda não fez nenhum pedido.</li>
            </c:if>
        </ul>
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
<script src="js/historico.js"></script>
</body>
</html>