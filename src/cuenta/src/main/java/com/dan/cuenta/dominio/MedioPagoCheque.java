package com.dan.cuenta.dominio;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "medio_pago_id")
public class MedioPagoCheque extends MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numeroCheque;
    private LocalDate fechaCobro;
    private String banco;
}
