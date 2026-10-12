// Move o pedido para a próxima etapa da produção.
// Na última etapa o botão confirma a entrega e o pedido sai do quadro.
const quadro = document.querySelector(".quadro");

function atualizarContadores() {
    quadro.querySelectorAll(".coluna").forEach((coluna) => {
        coluna.querySelector(".contador").textContent = coluna.querySelectorAll(".pedido-card").length;
    });
}

quadro.addEventListener("click", (evento) => {
    const botao = evento.target.closest(".btn-avancar");
    if (!botao) return;

    const card = botao.closest(".pedido-card");
    const proxima = card.closest(".coluna").nextElementSibling;

    if (proxima) {
        proxima.querySelector(".lista").append(card);
        const ultima = !proxima.nextElementSibling;
        botao.textContent = ultima ? "✓ Confirmar entrega" : "→ Avançar";
    } else {
        card.remove();
    }

    atualizarContadores();
});