package com.dan.usuario.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Cliente {

    @Id
    private Integer id;
    private String razonSocial;
    private String cuit;
    private String mail;
    private BigDecimal maximoCuentaCorriente;
    private Boolean habilitadoOnline;

    @OneToOne
    private Usuario usuario;

    public Cliente() {
    }

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

    public void setCuit(String cuil) {
        this.cuit = cuil;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
