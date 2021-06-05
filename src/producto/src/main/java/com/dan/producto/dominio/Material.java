package com.dan.producto.dominio;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stockActual;
    private Integer stockMinimo;

    @Enumerated(EnumType.STRING)
    private Unidad unidad;


}
