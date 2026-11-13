package br.com.nonnapizza.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PedidosAdm {
    private String id;
    private String cliente;
    private String pizzas;
    private BigDecimal total;
    private LocalDateTime hora;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPizzas() {
        return pizzas;
    }

    public void setPizzas(String pizzas) {
        this.pizzas = pizzas;
    }

    public String getHoraFormatada() {
        return hora == null ? "" : hora.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
    }
}
