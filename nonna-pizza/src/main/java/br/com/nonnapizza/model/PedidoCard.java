package br.com.nonnapizza.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PedidoCard {
    private String id;
    private LocalDateTime hora;
    private String status;
    private String cliente;
    private List<String> itens = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHora() {
        return hora == null ? "" : hora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<String> getItens() {
        return itens;
    }

    public void setItens(List<String> itens) {
        this.itens = itens;
    }
}
