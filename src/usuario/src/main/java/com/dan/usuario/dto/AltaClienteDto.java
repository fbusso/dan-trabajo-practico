package com.dan.usuario.dto;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.dominio.Obra;

import java.util.List;

public class AltaClienteDto {

    private Cliente cliente;
    private List<Obra> obras;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
}
