package com.dan.cuenta.dominio;

import javax.persistence.*;

@Entity
@Table(name = "medio_pago_efectivo")
@PrimaryKeyJoinColumn(name = "medio_pago_id")
public class Efectivo extends MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numeroRecibo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }
}
