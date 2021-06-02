CREATE TABLE usuario
(
    id             SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(20) UNIQUE NOT NULL,
    password       VARCHAR(50)        NOT NULL,
    tipo_usuario   VARCHAR(20)        NOT NULL
);

CREATE TABLE empleado
(
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(50) NOT NULL,
    usuario_id INTEGER     NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

CREATE TABLE cliente
(
    id                      SERIAL PRIMARY KEY,
    razon_social            VARCHAR(100)       NOT NULL,
    cuit                    VARCHAR(11) UNIQUE NOT NULL,
    mail                    VARCHAR(50) UNIQUE NOT NULL,
    maximo_cuenta_corriente NUMERIC(19, 2),
    habilitado_online       BOOLEAN            NOT NULL DEFAULT FALSE,
    usuario_id              INTEGER            NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

CREATE TABLE obra
(
    id          SERIAL PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    direccion   VARCHAR(100) NOT NULL UNIQUE,
    latitud     REAL         NOT NULL,
    longitud    REAL         NOT NULL,
    superficie  INTEGER      NOT NULL,
    tipo_obra   VARCHAR(20)  NOT NULL,
    cliente_id  INTEGER      NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);