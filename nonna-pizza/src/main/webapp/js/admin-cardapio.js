// Admin > Cardápio: lista, altera preço, liga/desliga e exclui pizzas.
const corpo = document.getElementById("pizzas");

async function carregar() {
    const pizzas = await api("api/pizzas"); // admin recebe todas, inclusive as indisponíveis
    corpo.innerHTML = pizzas.map((p) => `
        <tr data-id="${p.id}">
            <td><img class="pizza-img" src="${foto(p.foto)}" alt=""></td>
            <td class="nome">${esc(p.sabor)}</td>
            <td class="ingredientes">${esc(p.descricao)}</td>
            <td class="preco"><input type="number" class="campo-preco" step="0.01" min="0" value="${p.valor}" aria-label="Preço"></td>
            <td><label class="switch"><input type="checkbox" class="campo-disponivel" ${p.disponivel ? "checked" : ""}><span></span></label></td>
            <td><button type="button" class="btn-excluir">Excluir</button></td>
        </tr>`).join("");
}

function enviar(url, dados) {
    return api(url, { method: "POST", body: new URLSearchParams(dados) });
}

corpo.addEventListener("change", (evento) => {
    const id = evento.target.closest("tr").dataset.id;
    if (evento.target.matches(".campo-preco") && evento.target.value > 0)
        enviar("api/pizza-editar", { id, valor: evento.target.value });
    if (evento.target.matches(".campo-disponivel"))
        enviar("api/pizza-editar", { id, disponivel: evento.target.checked });
});

corpo.addEventListener("click", async (evento) => {
    if (!evento.target.matches(".btn-excluir")) return;
    const linha = evento.target.closest("tr");
    if (!confirm(`Excluir "${linha.querySelector(".nome").textContent}"?`)) return;
    await enviar("api/pizza-excluir", { id: linha.dataset.id });
    carregar();
});

carregar();
