// Funções compartilhadas por todas as páginas.

const TAXA_ENTREGA = 6.99; // precisa ser igual a PedidoDao.TAXA_ENTREGA no Java

const moeda = (v) => Number(v).toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
const foto = (f) => encodeURI(f || "imagem das pizzas/Marguerita.png"); // pizza sem foto usa uma padrão

// Escapa texto antes de colocar em innerHTML (evita injetar HTML/JS pelo nome de uma pizza).
const esc = (t) => String(t ?? "").replace(/[&<>"']/g, (c) => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" }[c]));

// fetch que já devolve JSON; se a sessão expirou (403) volta para o login.
async function api(url, opcoes) {
    const resposta = await fetch(url, opcoes);
    if (resposta.status === 403) {
        location.href = "login.html";
        throw new Error("Sem acesso");
    }
    if (!resposta.ok) throw new Error("Erro " + resposta.status);
    return resposta.json();
}

// Carrinho fica no navegador (localStorage): [{id, sabor, valor, foto, qtd}]
const carrinho = {
    ler() {
        try { return JSON.parse(localStorage.getItem("carrinho")) || []; } catch (e) { return []; }
    },
    salvar(lista) {
        localStorage.setItem("carrinho", JSON.stringify(lista));
        mostrarQuantidade();
    },
    adicionar(pizza) {
        const lista = this.ler();
        const item = lista.find((i) => i.id === pizza.id);
        if (item) item.qtd = Math.min(99, item.qtd + 1);
        else lista.push({ id: pizza.id, sabor: pizza.sabor, valor: pizza.valor, foto: pizza.foto, qtd: 1 });
        this.salvar(lista);
    },
};

function mostrarQuantidade() {
    const total = carrinho.ler().reduce((soma, i) => soma + i.qtd, 0);
    document.querySelectorAll(".carrinho-qtd").forEach((e) => (e.textContent = total));
    document.querySelectorAll(".carrinho-link").forEach((e) => e.setAttribute("aria-label", `Carrinho, ${total} itens`));
}
mostrarQuantidade();

// Menu do cliente: mostra "Sair" se estiver logado, senão "Entrar".
const menu = document.querySelector(".menu-topo");
if (menu) {
    fetch("api/me").then((r) => r.json()).then((u) => {
        const link = document.createElement("a");
        link.href = u.logado ? "logout" : "login.html";
        link.textContent = u.logado ? "Sair" : "Entrar";
        menu.insertBefore(link, menu.querySelector(".carrinho-link"));
    }).catch(() => {});
}
