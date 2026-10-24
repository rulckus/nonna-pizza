// Histórico do cliente (GET /api/meus-pedidos).
const ROTULO = { RECEBIDO: "Recebido", PREPARANDO: "Preparando", ASSANDO: "Assando", PRONTO: "Pronto", ENTREGUE: "Entregue" };

api("api/meus-pedidos").then((pedidos) => {
    document.getElementById("pedidos").innerHTML = pedidos.length ? pedidos.map((p) => `
        <li class="pedido">
            <div class="pedido-numero">#${p.id} <time>${esc(p.data)}</time></div>
            <ul>${p.itens.split(", ").map((i) => `<li>${esc(i)}</li>`).join("")}</ul>
            <span class="pedido-total">${moeda(p.total)}</span>
            <span class="selo selo-${p.status.toLowerCase()}">${ROTULO[p.status]}</span>
        </li>`).join("") : "<li>Você ainda não fez nenhum pedido.</li>";
});
