package com.dan.producto.dominio;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cantidadEntrada;
    private Integer cantidadSalida;
    private LocalDate fechaMovimiento;

    @OneToOne
    @JoinColumn(name = "detalle_provision_id")
    private DetalleProvision detalleProvision;
}
