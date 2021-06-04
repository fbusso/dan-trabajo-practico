package com.dan.usuario.servicio;

import com.dan.usuario.dominio.Empleado;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

import java.util.Optional;

public interface EmpleadoServicio {

    Empleado crear(Empleado empleado) throws ReglaDeNegociosExcepcion;

    Empleado actualizar(Empleado empleado);

    Optional<Empleado> obtenerPorId(Integer id);

    void eliminarPorId(Integer id);
}
