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

    public Provision() {
    }

    public Provision(List<DetalleProvision> detalles) {
        this.fechaProvision = LocalDate.now();
        this.detalleProvision = detalles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaProvision() {
        return fechaProvision;
    }

    public void setFechaProvision(LocalDate fechaProvision) {
        this.fechaProvision = fechaProvision;
    }

    public List<DetalleProvision> getDetalleProvision() {
        return detalleProvision;
    }

    public void setDetalleProvision(List<DetalleProvision> detalleProvision) {
        this.detalleProvision = detalleProvision;
    }
}
