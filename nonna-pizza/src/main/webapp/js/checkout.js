// Checkout: mostra o resumo, deixa escolher o endereço e envia o pedido (POST /api/pedido).
const itens = carrinho.ler();
if (!itens.length) location.href = "carrinho.html";

const form = document.getElementById("checkout");
const selectEndereco = document.getElementById("endereco");
const aviso = document.getElementById("aviso");

let subtotal = 0;
document.getElementById("itens").innerHTML = itens.map((i) => {
    subtotal += i.valor * i.qtd;
    return `
    <div class="item-resumo">
        <img src="${foto(i.foto)}" alt="">
        <div><strong>${esc(i.sabor)}</strong><small>× ${i.qtd}</small></div>
        <strong>${moeda(i.valor * i.qtd)}</strong>
    </div>`;
}).join("");
document.getElementById("subtotal").textContent = moeda(subtotal);
document.getElementById("taxa").textContent = moeda(TAXA_ENTREGA);
document.getElementById("total").textContent = moeda(subtotal + TAXA_ENTREGA);

api("api/enderecos").then((enderecos) => {
    if (!enderecos.length) {
        aviso.textContent = "Você ainda não tem endereço cadastrado.";
        selectEndereco.hidden = true;
        return;
    }
    selectEndereco.innerHTML = enderecos.map((e) =>
        `<option value="${e.id}">${esc(e.rua)}, ${esc(e.numero)} — ${esc(e.bairro)}, ${esc(e.cidade)}</option>`).join("");
});

form.addEventListener("submit", async (evento) => {
    evento.preventDefault();
    if (selectEndereco.hidden) {
        aviso.textContent = "Cadastre um endereço para continuar.";
        return;
    }

    // "3:2,5:1" = pizza 3 (2 unidades) e pizza 5 (1 unidade). O preço é calculado no servidor.
    const dados = new URLSearchParams({
        itens: itens.map((i) => `${i.id}:${i.qtd}`).join(","),
        endereco: selectEndereco.value,
        pagamento: form.pagamento.value,
    });

    try {
        await api("api/pedido", { method: "POST", body: dados });
        carrinho.salvar([]);
        location.href = "historico.html";
    } catch (erro) {
        aviso.textContent = "Não foi possível finalizar o pedido. Confira se as pizzas ainda estão disponíveis.";
    }
});
