package com.dan.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClienteDto {

    private Integer id;
    private String razonSocial;
    private String cuit;
    private String mail;
    private LocalDate fechaBaja;
    private BigDecimal maximoCuentaCorriente;
    private Boolean habilitadoOnline;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public BigDecimal getMaximoCuentaCorriente() {
        return maximoCuentaCorriente;
    }

    public void setMaximoCuentaCorriente(BigDecimal maximoCuentaCorriente) {
        this.maximoCuentaCorriente = maximoCuentaCorriente;
    }

    public Boolean getHabilitadoOnline() {
        return habilitadoOnline;
    }

    public void setHabilitadoOnline(Boolean habilitadoOnline) {
        this.habilitadoOnline = habilitadoOnline;
    }
}
