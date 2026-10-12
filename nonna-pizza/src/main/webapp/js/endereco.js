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