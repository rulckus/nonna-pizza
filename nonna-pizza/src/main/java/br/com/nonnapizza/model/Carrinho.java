package br.com.nonnapizza.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Carrinho implements Serializable {
    private final Map<String, CarrinhoItem> itens = new LinkedHashMap<>();

    public void adicionar(CarrinhoItem novo) {
        CarrinhoItem existente = itens.get(novo.getChave());
        if (existente != null) existente.setQuantidade(existente.getQuantidade() + 1);
        else itens.put(novo.getChave(), novo);
    }

    public void alterar(String chave, int delta) {
        CarrinhoItem item = itens.get(chave);
        if (item == null) return;
        item.setQuantidade(item.getQuantidade() + delta);
        if (item.getQuantidade() <= 0) itens.remove(chave);
    }

    public void remover(String chave) { itens.remove(chave); }
    public void limpar() { itens.clear(); }

    public Collection<CarrinhoItem> getItens() { return itens.values(); }
    public boolean isVazio() { return itens.isEmpty(); }

    public int getQuantidadeTotal() {
        return itens.values().stream().mapToInt(CarrinhoItem::getQuantidade).sum();
    }

    public BigDecimal getTotal() {
        return itens.values().stream().map(CarrinhoItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
