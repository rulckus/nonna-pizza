// Cardápio do cliente: pizzas vêm do banco (GET /api/pizzas).
const grade = document.getElementById("grade");

api("api/pizzas").then((pizzas) => {
    const porId = Object.fromEntries(pizzas.map((p) => [p.id, p])); // atalho: porId[3] -> pizza 3

    grade.innerHTML = pizzas.length ? pizzas.map((p) => `
        <article class="pizza-card">
            <img src="${foto(p.foto)}" alt="Pizza ${esc(p.sabor)}" loading="lazy">
            <h3>${esc(p.sabor)}</h3>
            <p>${esc(p.descricao)}</p>
            <span class="valor">${moeda(p.valor)}</span>
            <button type="button" class="btn btn-primario" data-id="${p.id}">+ Adicionar</button>
        </article>`).join("") : "<p>Nenhuma pizza disponível no momento.</p>";

    grade.addEventListener("click", (evento) => {
        const botao = evento.target.closest("button[data-id]");
        if (!botao) return;
        carrinho.adicionar(porId[botao.dataset.id]);
        botao.textContent = "✓ Adicionado";
        setTimeout(() => (botao.textContent = "+ Adicionar"), 900);
    });
}).catch(() => (grade.innerHTML = "<p>Não foi possível carregar o cardápio.</p>"));
