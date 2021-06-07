package com.dan.cuenta.dominio;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Transferencia.class, name = "transferencia"),
        @JsonSubTypes.Type(value = Efectivo.class, name = "efectivo"),
        @JsonSubTypes.Type(value = Cheque.class, name = "cheque")
})
@Entity
@Table(name = "medio_pago")
@Inheritance(strategy = InheritanceType.JOINED)
public class MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String observacion;

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

}
