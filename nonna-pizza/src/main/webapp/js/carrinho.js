const CHAVE_CARRINHO = 'carrinho';
const moeda = v => Number(v).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });

function escapar(texto) {
    const div = document.createElement('div');
    div.textContent = texto;
    return div.innerHTML;
}

function lerCarrinho() {
    try {
        return JSON.parse(localStorage.getItem(CHAVE_CARRINHO)) || [];
    } catch {
        return [];
    }
}

function salvarCarrinho(itens) {
    localStorage.setItem(CHAVE_CARRINHO, JSON.stringify(itens));
    atualizarContador();
}

const mesmoItem = (i, tipo, id) => i.tipo === tipo && String(i.id) === String(id);

// tipo: 'pizza' ou 'produto' (chamada pelo cardapio.jsp)
function adicionarAoCarrinho(tipo, id, nome, preco, foto) {
    const itens = lerCarrinho();
    const existente = itens.find(i => mesmoItem(i, tipo, id));
    if (existente) {
        existente.quantidade++;
    } else {
        itens.push({ tipo, id, nome, preco: Number(preco), foto, quantidade: 1 });
    }
    salvarCarrinho(itens);
}

function alterarQuantidade(tipo, id, delta) {
    const itens = lerCarrinho();
    const item = itens.find(i => mesmoItem(i, tipo, id));
    if (!item) return;
    item.quantidade += delta;
    salvarCarrinho(item.quantidade > 0 ? itens : itens.filter(i => i !== item));
}

function removerDoCarrinho(tipo, id) {
    salvarCarrinho(lerCarrinho().filter(i => !mesmoItem(i, tipo, id)));
}

function atualizarContador() {
    const total = lerCarrinho().reduce((s, i) => s + i.quantidade, 0);
    const badge = document.querySelector('.carrinho-qtd');
    if (badge) badge.textContent = total;

    const link = document.querySelector('.carrinho-link');
    if (link) link.setAttribute('aria-label', `Carrinho, ${total} ${total === 1 ? 'item' : 'itens'}`);
}

function renderizarCarrinho() {
    const lista = document.querySelector('.lista-carrinho');
    if (!lista) return; // não está na página do carrinho

    const itens = lerCarrinho();
    const botao = document.getElementById('continuar');

    if (itens.length === 0) {
        lista.innerHTML = '<p>Seu carrinho está vazio.</p>';
    } else {
        lista.innerHTML = itens.map(i => `
      <article class="item-carrinho">
        ${i.foto ? `<img src="${escapar(i.foto)}" alt="" class="item-foto">` : ''}
        <div class="item-info">
          <h3>${escapar(i.nome)}</h3>
          <span>${moeda(i.preco)}</span>
        </div>
        <div class="item-quantidade">
          <button type="button" data-acao="menos" data-tipo="${i.tipo}" data-id="${i.id}" aria-label="Diminuir">−</button>
          <span>${i.quantidade}</span>
          <button type="button" data-acao="mais" data-tipo="${i.tipo}" data-id="${i.id}" aria-label="Aumentar">+</button>
        </div>
        <strong class="item-subtotal">${moeda(i.preco * i.quantidade)}</strong>
        <button type="button" data-acao="remover" data-tipo="${i.tipo}" data-id="${i.id}" aria-label="Remover ${escapar(i.nome)}">✕</button>
      </article>
    `).join('');
    }




    document.getElementById('total').textContent = moeda(subtotal);

    if (botao) {
        botao.setAttribute('aria-disabled', itens.length === 0);
        botao.classList.toggle('desabilitado', itens.length === 0);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    atualizarContador();
    renderizarCarrinho();

    // um único listener para todos os botões da lista
    const lista = document.querySelector('.lista-carrinho');
    if (lista) {
        lista.addEventListener('click', e => {
            const btn = e.target.closest('button[data-acao]');
            if (!btn) return;
            const { acao, tipo, id } = btn.dataset;
            if (acao === 'mais') alterarQuantidade(tipo, id, 1);
            if (acao === 'menos') alterarQuantidade(tipo, id, -1);
            if (acao === 'remover') removerDoCarrinho(tipo, id);
            renderizarCarrinho();
        });
    }

    // não deixa ir para o checkout com carrinho vazio
    const continuar = document.getElementById('continuar');
    if (continuar) {
        continuar.addEventListener('click', e => {
            if (lerCarrinho().length === 0) e.preventDefault();
        });
    }
});