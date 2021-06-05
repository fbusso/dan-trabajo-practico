package com.dan.cuenta.dominio;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "medio_pago_id")
public class MedioPagoTransferencia extends MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cbuOrigen;
    private String cbuDestino;

}
