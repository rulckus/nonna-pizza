// Quadro da cozinha: cada pedido anda para a próxima coluna e o status é salvo no banco.
const quadro = document.querySelector(".quadro");
const COLUNAS = { RECEBIDO: "recebido", PREPARANDO: "preparando", ASSANDO: "assando", PRONTO: "pronto" };
const PROXIMO = { RECEBIDO: "PREPARANDO", PREPARANDO: "ASSANDO", ASSANDO: "PRONTO", PRONTO: "ENTREGUE" };
const RELOGIO = `<svg class="icone" viewBox="0 0 24 24" aria-hidden="true"><circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/></svg>`;

async function carregar() {
    const pedidos = await api("api/admin/pedidos");

    for (const nome of Object.values(COLUNAS)) {
        const coluna = quadro.querySelector(".coluna-" + nome);
        const doStatus = pedidos.filter((p) => COLUNAS[p.status] === nome).reverse(); // mais antigo primeiro

        coluna.querySelector(".contador").textContent = doStatus.length;
        coluna.querySelector(".lista").innerHTML = doStatus.map((p) => `
            <article class="pedido-card" data-id="${p.id}" data-status="${p.status}">
                <div class="pedido-cabecalho">
                    <span>#${p.id}</span>
                    <span class="pedido-hora">${RELOGIO} ${esc(p.data)}</span>
                </div>
                <p class="pedido-cliente">${esc(p.cliente)}</p>
                <ul class="pedido-itens">${p.itens.split(", ").map((i) => `<li>${esc(i)}</li>`).join("")}</ul>
                <button type="button" class="btn btn-avancar">${p.status === "PRONTO" ? "✓ Confirmar entrega" : "→ Avançar"}</button>
            </article>`).join("");
    }
    document.getElementById("hora").textContent = new Date().toTimeString().slice(0, 5);
}

quadro.addEventListener("click", async (evento) => {
    if (!evento.target.closest(".btn-avancar")) return;
    const card = evento.target.closest(".pedido-card");
    await api("api/admin/status", {
        method: "POST",
        body: new URLSearchParams({ id: card.dataset.id, status: PROXIMO[card.dataset.status] }),
    });
    carregar();
});

carregar();
setInterval(carregar, 15000); // novos pedidos aparecem sozinhos
