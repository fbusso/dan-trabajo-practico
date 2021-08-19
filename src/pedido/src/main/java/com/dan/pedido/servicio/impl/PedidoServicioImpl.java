package com.dan.pedido.servicio.impl;

import com.dan.pedido.dominio.DetallePedido;
import com.dan.pedido.dominio.EstadoPedido;
import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.dto.ClienteDto;
import com.dan.pedido.dto.SituacionCrediticiaDto;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.excepcion.SituacionCrediticiaExcepcion;
import com.dan.pedido.repositorio.PedidoRepositorio;
import com.dan.pedido.servicio.*;
import com.dan.pedido.validador.PedidoValidador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServicioImpl implements PedidoServicio {

    private final PedidoRepositorio pedidoRepositorio;
    private final PedidoValidador pedidoValidador;
    private final MaterialServicio materialServicio;
    private final UsuarioServicio usuarioServicio;
    private final BcraServicio bcraServicio;
    private final ColaMensajesServicio colaMensajesServicio;

    public PedidoServicioImpl(PedidoRepositorio pedidoRepositorio,
                              PedidoValidador pedidoValidador,
                              MaterialServicio materialServicio,
                              UsuarioServicio usuarioServicio,
                              BcraServicio bcraServicio,
                              ColaMensajesServicio colaMensajesServicio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.pedidoValidador = pedidoValidador;
        this.materialServicio = materialServicio;
        this.usuarioServicio = usuarioServicio;
        this.bcraServicio = bcraServicio;
        this.colaMensajesServicio = colaMensajesServicio;
    }

    @Override
    public Pedido crear(Pedido pedido) throws ReglaDeNegociosExcepcion {
        pedidoValidador.validarCreacion(pedido);
        pedido.setFechaPedido(LocalDate.now());
        pedido.setEstadoPedido(EstadoPedido.NUEVO);
        return pedidoRepositorio.save(pedido);
    }

    @Override
    public Pedido confirmar(Integer id) throws ReglaDeNegociosExcepcion {
        Pedido pedido = pedidoValidador.validarConfirmacion(id);

        List<Integer> ids = pedido
                .getDetallePedido()
                .stream().map(detallePedido -> detallePedido.getProducto().getId())
                .collect(Collectors.toUnmodifiableList());

        if (!CollectionUtils.isEmpty(materialServicio.obtenerSinStockPorId(ids))) {
            pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        }

        // Se verifica si el pedido genera saldo deudor
        final ClienteDto cliente = usuarioServicio.obtenerPorObraId(pedido.getObra().getId());
        if (pedido.getCostoTotal().subtract(cliente.getMaximoCuentaCorriente()).compareTo(BigDecimal.ZERO) < 0) {

            final SituacionCrediticiaDto situacionCrediticia = bcraServicio.obtenerSituacionCrediticiaPorCuit(cliente.getCuit());
            if (!SituacionCrediticiaDto.BAJO.equals(situacionCrediticia)) {
                throw new SituacionCrediticiaExcepcion();
            }

        }

        pedido.setEstadoPedido(EstadoPedido.ACEPTADO);
        colaMensajesServicio.enviar(pedido);
        return pedidoRepositorio.save(pedido);
    }

    @Override
    public Optional<Pedido> obtenerPorId(Integer id) {
        return pedidoRepositorio.findById(id);
    }

    @Override
    public Page<Pedido> obtenerTodos(Integer pagina, Integer cantidadRegistros) {
        final PageRequest pageRequest = PageRequest.of(pagina, cantidadRegistros);
        return pedidoRepositorio.findAll(pageRequest);
    }

    @Override
    public Optional<Pedido> agregarDetalle(Integer id, DetallePedido detalle) {
        Optional<Pedido> pedidoOptional = pedidoRepositorio.findById(id);
        pedidoOptional.ifPresent(pedido -> {
            pedido.getDetallePedido().add(detalle);
            pedidoRepositorio.save(pedido);
        });

        return pedidoOptional;
    }

    @Override
    public List<Pedido> obtenerPorClienteId(Integer clienteId) {
        return pedidoRepositorio.findAllByObra_clienteId(clienteId);
    }

    @Override
    public void eliminarPorId(Integer id) {
        pedidoRepositorio.deleteById(id);
    }

}
