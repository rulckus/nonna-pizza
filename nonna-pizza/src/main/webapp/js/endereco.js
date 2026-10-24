// Preenche rua, bairro e cidade a partir do CEP (API pública ViaCEP).
const campoCep = document.getElementById("cep");

function avisar(mensagem) {
    campoCep.setCustomValidity(mensagem);
    campoCep.reportValidity();
}

campoCep.addEventListener("input", () => campoCep.setCustomValidity(""));

document.getElementById("buscar-cep").addEventListener("click", async () => {
    const cep = campoCep.value.replace(/\D/g, "");

    if (cep.length !== 8) {
        avisar("Digite um CEP com 8 números.");
        return;
    }

    try {
        const resposta = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
        const dados = await resposta.json();

        if (dados.erro) {
            avisar("CEP não encontrado.");
            return;
        }

        document.getElementById("rua").value = dados.logradouro;
        document.getElementById("bairro").value = dados.bairro;
        document.getElementById("cidade").value = dados.localidade;
        document.getElementById("numero").focus();
    } catch (erro) {
        avisar("Não foi possível buscar o CEP agora.");
    }
});

// Lista os endereços já salvos do cliente.
api("api/enderecos").then((enderecos) => {
    document.getElementById("lista-enderecos").innerHTML = enderecos.length ? enderecos.map((e) => `
        <article class="endereco-salvo">
            <header>
                <svg class="icone" viewBox="0 0 24 24" aria-hidden="true"><path d="M21 10c0 7-9 13-9 13S3 17 3 10a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/></svg>
                ${esc(e.rua)}, ${esc(e.numero)}
            </header>
            <p>${esc(e.complemento)}${e.complemento ? " — " : ""}${esc(e.bairro)}, ${esc(e.cidade)}<br>CEP ${esc(e.cep)}</p>
        </article>`).join("") : "<p>Nenhum endereço salvo ainda.</p>";
});
