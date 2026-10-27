package br.com.nonnapizza.model;

public class Endereco {
    private String id;
    private String rua;
    private String cep;
    private String cidade;
    private String numero;
    private String complemento;
    private String bairro;
    private String idCliente;

    public Endereco() {
    }

    public Endereco(String id, String rua, String cep, String cidade, String numero, String complemento, String bairro) {
        this.id = id;
        this.rua = rua;
        this.cep = cep;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}
