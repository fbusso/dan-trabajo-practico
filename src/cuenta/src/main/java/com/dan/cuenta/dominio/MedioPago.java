package com.dan.cuenta.dominio;

import javax.persistence.*;

@Entity
@Table(name = "medio_pago")
@DiscriminatorColumn(name = "discriminador")
@Inheritance(strategy = InheritanceType.JOINED)
public class MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String observacion;
    private String discriminador;

    public MedioPago() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDiscriminador() {
        return discriminador;
    }

    public void setDiscriminador(String discriminador) {
        this.discriminador = discriminador;
    }
}
