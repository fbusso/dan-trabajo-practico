package com.dan.producto.dominio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Provision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fechaProvision;


    @OneToMany(mappedBy = "provision", cascade = CascadeType.ALL, orphanRemoval = true)
    List<DetalleProvision> detalleProvision;
}
