package com.dan.usuario.excepcion.handler;

import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExcepcionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReglaDeNegociosExcepcion.class)
    protected ResponseEntity<Object> manejarReglaDeNegociosExcepcion(ReglaDeNegociosExcepcion excepcion, WebRequest request) {
        return handleExceptionInternal(excepcion, excepcion.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
