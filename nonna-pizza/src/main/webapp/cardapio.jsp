<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cardápio - Nonna Pizza</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,wght@0,400;0,500;0,600;0,700;1,400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/cliente.css">
</head>
<body class="pagina-cliente">

<header class="cabecalho">
    <div class="container">
        <a href="landing.jsp" class="marca">
            <span class="marca-selo" aria-hidden="true">N</span>
            <span>Nonna<small>PIZZA</small></span>
        </a>
        <nav class="menu-topo" aria-label="Principal">
            <c:choose>
              <c:when test="${not empty sessionScope.loggeduser}">
                 <a href="/cardapio" aria-current="page">Cardápio</a>
                 <a href="/historico">Meus Pedidos</a>
                 <a href="/enderecos">Minha Conta</a>
                 <a href="/carrinho" class="carrinho-link" aria-label="Carrinho, ${empty carrinho ? 0 : carrinho.quantidadeTotal} itens">
                 <svg class="icone" viewBox="0 0 24 24" aria-hidden="true"><circle cx="9" cy="21" r="1"/><circle cx="20" cy="21" r="1"/><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"/></svg>
                  <span class="carrinho-qtd">${empty carrinho ? 0 : carrinho.quantidadeTotal}</span>
                 </a>
              </c:when>
                <c:otherwise>
                  <a href="/landing">Home</a>
                  <a href="login.html" class="btn btn-primario btn-pilula">Entrar</a>
                </c:otherwise>
            </c:choose>
        </nav>
    </div>
</header>


<main>
    <div class="container">
        <h1 class="titulo-pagina titulo-italico">Pizzas e Sabores</h1>

        <div class="grade-pizzas" id="grade">
            <c:forEach var="pizza" items="${pizzasCardapio}">
                <article class="pizza-card">
                    <img src="/img/${pizza.image}" loading="lazy">
                    <h3><c:out value="${pizza.sabor}"/></h3>
                    <p><c:out value="${pizza.descricao}"/></p>
                    <span class="valor"><fmt:formatNumber value="${pizza.valor}" type="currency"/></span>

                  <c:choose>
                  <c:when test="${not empty sessionScope.loggeduser}">
                     <form action="/carrinho" method="post">
                        <input type="hidden" name="acao" value="adicionar">
                        <input type="hidden" name="tipo" value="pizza">
                        <input type="hidden" name="id" value="${pizza.id}">
                        <button type="submit" class="btn btn-primario">+ Pedir</button>
                     </form>
                  </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
                </article>
            </c:forEach>
            <c:if test="${empty pizzasCardapio}">
                <p class="estado-vazio">Nenhuma pizza disponível no momento.</p>
            </c:if>
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



</body>
</html>