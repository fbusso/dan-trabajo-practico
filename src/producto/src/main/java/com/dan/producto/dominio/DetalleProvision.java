package com.dan.producto.dominio;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class DetalleProvision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provision_id")
    Provision provision;

}
