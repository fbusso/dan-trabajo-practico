package com.dan.producto.dto;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

public class DetallePedidoDto {

    private Integer id;
    private Integer cantidad;
    private BigDecimal precio;
    private MaterialDto producto;

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public MaterialDto getProducto() {
        return producto;
    }

    public void setProducto(MaterialDto producto) {
        this.producto = producto;
    }
}
