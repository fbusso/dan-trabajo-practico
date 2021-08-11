package com.dan.bcra.controlador;

import com.dan.bcra.dominio.SituacionCrediticia;
import com.dan.bcra.servicio.SituacionCrediticiaServicioMock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sitaucion-crediticia")
public class SituacionCrediticiaControlador {

    private final SituacionCrediticiaServicioMock situacionCrediticiaServicioMock;

    public SituacionCrediticiaControlador(SituacionCrediticiaServicioMock situacionCrediticiaServicioMock) {
        this.situacionCrediticiaServicioMock = situacionCrediticiaServicioMock;
    }

    @GetMapping("/{cuit}")
    public ResponseEntity<SituacionCrediticia> obtenerSituacionCrediticia(@PathVariable String cuit) {
        return ResponseEntity.ok(situacionCrediticiaServicioMock.obtenerPorCuit(cuit));
    }
}
