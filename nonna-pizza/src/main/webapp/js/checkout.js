function renderizarItens() {
    const itens = lerCarrinho();

    if (itens.length === 0) {
        window.location.href = 'carrinho.jsp';
        return;
    }

    document.getElementById('itens').innerHTML = itens.map(i => `
    <div class="linha-resumo">
      <span>${i.quantidade}x ${escapar(i.nome)}</span>
      <span>${moeda(i.preco * i.quantidade)}</span>
    </div>
  `).join('');

    const subtotal = itens.reduce((s, i) => s + i.preco * i.quantidade, 0);
    document.getElementById('total').textContent = moeda(subtotal);
}

async function carregarEnderecos() {
    const select = document.getElementById('endereco');
    const aviso = document.getElementById('aviso');

    try {
        const resp = await fetch('enderecos');
        if (resp.status === 401) {
            window.location.href = 'login.html';
            return;
        }
        const enderecos = await resp.json();

        if (enderecos.length === 0) {
            select.innerHTML = '<option value="">Nenhum endereço cadastrado</option>';
            aviso.textContent = 'Cadastre um endereço para continuar.';
            return;
        }

        select.innerHTML = enderecos.map(e =>
            `<option value="${e.id}">${escapar(`${e.rua}, ${e.numero} - ${e.bairro}, ${e.cidade}`)}</option>`
        ).join('');
        aviso.textContent = '';
    } catch {
        aviso.textContent = 'Não foi possível carregar seus endereços.';
    }
}

async function fazerPedido(e) {
    e.preventDefault();

    const itens = lerCarrinho();
    const aviso = document.getElementById('aviso');
    const botao = e.target.querySelector('button[type="submit"]');
    const idEndereco = document.getElementById('endereco').value;

    if (itens.length === 0) return;
    if (!idEndereco) {
        aviso.textContent = 'Selecione um endereço de entrega.';
        return;
    }

    const payload = {
        idEndereco: Number(idEndereco),
        formaPagamento: document.querySelector('input[name="pagamento"]:checked').value,
        itens: itens.map(i => ({ tipo: i.tipo, id: Number(i.id), quantidade: i.quantidade }))
    };

    botao.disabled = true; // evita clique duplo gerando dois pedidos

    try {
        const resp = await fetch('pedido', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (resp.status === 201) {
            localStorage.removeItem(CHAVE_CARRINHO);
            window.location.href = 'historico.jsp';
        } else if (resp.status === 401) {
            window.location.href = 'login.html';
        } else {
            aviso.textContent = 'Erro ao finalizar o pedido. Tente novamente.';
            botao.disabled = false;
        }
    } catch {
        aviso.textContent = 'Falha de conexão. Tente novamente.';
        botao.disabled = false;
    }
}

document.addEventListener('DOMContentLoaded', () => {
    renderizarItens();
    carregarEnderecos();
    document.getElementById('checkout').addEventListener('submit', fazerPedido);
});