// Recalcula o valor de cada item e o total do pedido ao mudar a quantidade.
const moeda = (valor) => valor.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });

const itens = document.querySelectorAll(".item-carrinho");
const taxaEntrega = Number(document.querySelector(".resumo").dataset.entrega);

function atualizarTotais() {
    let subtotal = 0;

    itens.forEach((item) => {
        const quantidade = Number(item.querySelector("output").value);
        const totalItem = quantidade * Number(item.dataset.preco);

        item.querySelector(".item-total").textContent = moeda(totalItem);
        subtotal += totalItem;
    });

    document.getElementById("subtotal").textContent = moeda(subtotal);
    document.getElementById("total").textContent = moeda(subtotal + taxaEntrega);
}

document.querySelector(".lista-carrinho").addEventListener("click", (evento) => {
    const botao = evento.target.closest("button[data-acao]");
    if (!botao) return;

    const saida = botao.closest(".quantidade").querySelector("output");
    const passo = botao.dataset.acao === "mais" ? 1 : -1;

    saida.value = Math.max(1, Number(saida.value) + passo);
    atualizarTotais();
});