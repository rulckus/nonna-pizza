// Admin > Pedidos: lista todos os pedidos (a mudança de status é feita na tela Produção).
const ROTULO = { RECEBIDO: "Recebido", PREPARANDO: "Preparando", ASSANDO: "Assando", PRONTO: "Pronto", ENTREGUE: "Entregue" };

api("api/admin/pedidos").then((pedidos) => {
    document.getElementById("pedidos").innerHTML = pedidos.map((p) => `
        <tr>
            <td>#${p.id}</td>
            <td class="cliente">${esc(p.cliente)}</td>
            <td class="ingredientes">${esc(p.itens)}</td>
            <td class="total">${moeda(p.total)}</td>
            <td>${esc(p.data)}</td>
            <td><span class="status status-${p.status.toLowerCase()}">${ROTULO[p.status]}</span></td>
        </tr>`).join("");
});
