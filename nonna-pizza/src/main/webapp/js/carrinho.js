// Carrinho: lê do localStorage, desenha os itens e recalcula os totais.
const lista = document.querySelector(".lista-carrinho");
const botaoContinuar = document.getElementById("continuar");

function desenhar() {
    const itens = carrinho.ler();
    let subtotal = 0;

    lista.innerHTML = itens.length ? itens.map((i) => {
        subtotal += i.valor * i.qtd;
        return `
        <article class="item-carrinho" data-id="${i.id}">
            <img src="${foto(i.foto)}" alt="">
            <div class="item-info">
                <div class="item-linha">
                    <h2>${esc(i.sabor)}</h2>
                    <strong class="item-total">${moeda(i.valor * i.qtd)}</strong>
                </div>
                <div class="item-acoes">
                    <div class="quantidade">
                        <button type="button" data-acao="menos" aria-label="Diminuir quantidade">&minus;</button>
                        <output>${i.qtd}</output>
                        <button type="button" data-acao="mais" aria-label="Aumentar quantidade">+</button>
                    </div>
                </div>
            </div>
        </article>`;
    }).join("") : "<p>Seu carrinho está vazio.</p>";

    const entrega = itens.length ? TAXA_ENTREGA : 0;
    document.getElementById("subtotal").textContent = moeda(subtotal);
    document.getElementById("taxa").textContent = moeda(entrega);
    document.getElementById("total").textContent = moeda(subtotal + entrega);
    botaoContinuar.hidden = !itens.length;
}

lista.addEventListener("click", (evento) => {
    const botao = evento.target.closest("button[data-acao]");
    if (!botao) return;

    const id = Number(botao.closest(".item-carrinho").dataset.id);
    let itens = carrinho.ler();
    const item = itens.find((i) => i.id === id);

    item.qtd += botao.dataset.acao === "mais" ? 1 : -1;
    item.qtd = Math.min(99, item.qtd);
    if (item.qtd < 1) itens = itens.filter((i) => i.id !== id); // chegou a 0: remove o item

    carrinho.salvar(itens);
    desenhar();
});

desenhar();
