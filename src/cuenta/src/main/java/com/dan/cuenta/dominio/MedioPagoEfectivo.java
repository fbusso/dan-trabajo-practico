package com.dan.cuenta.dominio;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "medio_pago_id")
public class MedioPagoEfectivo extends MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numeroRecibo;
}
