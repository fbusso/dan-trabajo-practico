package com.dan.cuenta.dominio;

import javax.persistence.*;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne
    @JoinColumn(name = "medio_pago_id")
    private MedioPago medioPago;
}
