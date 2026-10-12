// Esconde os itens que não contêm o texto digitado na busca.
// Uso: <input data-filtro=".seletor-dos-itens">
document.querySelectorAll("[data-filtro]").forEach(function (campo) {
    campo.addEventListener("input", function () {
        const termo = campo.value.trim().toLowerCase();

        document.querySelectorAll(campo.dataset.filtro).forEach(function (item) {
            item.hidden = !item.textContent.toLowerCase().includes(termo);
        });
    });
});