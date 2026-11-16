package br.com.nonnapizza.model;

import java.math.BigDecimal;

public class Pizza {
    private String sabor;
    private String tipo;
    private BigDecimal valor;
    private String descricao;
    private String id;
    private boolean disponibilidade;
    private String image;

    public Pizza() {}

    public Pizza(String sabor, String id) {
        this.sabor = sabor;
        this.id = id;
    }

    public Pizza(String sabor, String tipo, BigDecimal valor, String descricao, String id, boolean disponibilidade, String image) {
        this.sabor = sabor;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.id = id;
        this.disponibilidade = disponibilidade;
        this.image = image;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
