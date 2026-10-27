package br.com.nonnapizza.model;

import java.math.BigDecimal;

public class CarrinhoItem {
    private String tipo;          // "pizza" ou "produto"
    private Long id;
    private String nome;
    private String foto;
    private BigDecimal preco;
    private int quantidade;

    public String getChave() { return tipo + "-" + id; }
    public BigDecimal getSubtotal() { return preco.multiply(BigDecimal.valueOf(quantidade)); }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
