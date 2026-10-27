<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Endereço de Entrega - Nonna Pizza</title>
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
            <a href="/endereco" aria-current="page">Minha Conta</a>
            <a href="/carrinho" class="carrinho-link" aria-label="Carrinho, 3 itens">
                <svg class="icone" viewBox="0 0 24 24" aria-hidden="true"><circle cx="9" cy="21" r="1"/><circle cx="20" cy="21" r="1"/><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"/></svg>
                <span class="carrinho-qtd">3</span>
            </a>
        </nav>
    </div>
</header>

<main>
    <div class="container duas-colunas duas-colunas--endereco">
        <section>
            <h1 class="titulo-simples">Endereço de Entrega</h1>

            <form action="create-endereco" method="post" class="bloco formulario">
                <p class="formulario-titulo">Novo endereço</p>

                <div class="linha-campos linha-cep">
                    <div>
                        <label for="cep">CEP</label>
                        <input type="text" name="cep" id="cep" placeholder="00000-000" inputmode="numeric" maxlength="9" required>
                    </div>
                    <button type="button" id="buscar-cep" class="btn btn-secundario">
                        <svg class="icone" viewBox="0 0 24 24" aria-hidden="true"><circle cx="11" cy="11" r="8"/><path d="M21 21l-4.35-4.35"/></svg>
                        Buscar CEP
                    </button>
                </div>

                <div class="linha-campos linha-rua">
                    <div>
                        <label for="rua">Rua</label>
                        <input type="text" name="rua" id="rua" placeholder="Nome da rua" required>
                    </div>
                    <div>
                        <label for="numero">Número</label>
                        <input type="number" name="numero" id="numero" placeholder="Nº" min="0" required>
                    </div>
                </div>

                <div class="linha-campos">
                    <div>
                        <label for="complemento">Complemento</label>
                        <input type="text" name="complemento" id="complemento" placeholder="Apto, sala, bloco... (opcional)">
                    </div>
                </div>

                <div class="linha-campos linha-cidade">
                    <div>
                        <label for="bairro">Bairro</label>
                        <input type="text" name="bairro" id="bairro" placeholder="Nome do bairro" required>
                    </div>
                    <div>
                        <label for="cidade">Cidade</label>
                        <input type="text" name="cidade" id="cidade" placeholder="Nome da cidade" required>
                    </div>
                </div>

                <button type="submit" class="btn btn-primario btn-grande">Salvar e continuar</button>
            </form>
        </section>

        <aside class="enderecos-salvos">
            <h2>Endereços salvos</h2>

            <div id="lista-enderecos">
                <c:forEach var="item" items="${enderecos}">
                    <tr>

                        <td>${item.rua}</td>
                        <td>${item.numero}</td>
                    </tr>
                </c:forEach>

            </div>

            <a href="checkout.html" class="btn btn-primario">Ir para o pagamento →</a>
        </aside>
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
<script src="js/endereco.js"></script>
</body>
</html>