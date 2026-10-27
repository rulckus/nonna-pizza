package br.com.nonnapizza.model;

import java.math.BigDecimal;

public class Pizza {
    private String sabor;
    private String tipo;
    private BigDecimal valor;
    private String descricao;
    private String id;

    public Pizza() {}

    public Pizza(String sabor, String id) {
        this.sabor = sabor;
        this.id = id;
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
}
