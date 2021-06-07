package com.dan.producto.dominio;

import javax.persistence.*;

@Entity
@Table(name = "detalle_provision")
public class DetalleProvision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cantidad;


    @OneToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provision_id")
    Provision provision;

    public DetalleProvision() {
    }

    // TODO: Cambiar la cantidad a reponer
    public DetalleProvision(Material material) {
        this.material = material;
        this.cantidad = 100;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Provision getProvision() {
        return provision;
    }

    public void setProvision(Provision provision) {
        this.provision = provision;
    }
}
