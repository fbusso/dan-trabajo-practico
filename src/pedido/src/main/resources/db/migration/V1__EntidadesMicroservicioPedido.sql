CREATE TABLE obra
(
    id          SERIAL PRIMARY KEY,
    cliente_id  INTEGER      NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE producto
(
    id          SERIAL PRIMARY KEY,
    descripcion VARCHAR(100)   NOT NULL,
    precio      NUMERIC(19, 2) NOT NULL
);

CREATE TABLE pedido
(
    id            SERIAL PRIMARY KEY,
    estado_pedido VARCHAR(50) NOT NULL,
    fecha_pedido  DATE        NOT NULL,
    obra_id       INTEGER     NOT NULL,
    CONSTRAINT fk_obra FOREIGN KEY (obra_id) REFERENCES obra (id)
);

CREATE TABLE detalle_pedido
(
    id          SERIAL PRIMARY KEY,
    producto_id INTEGER,
    pedido_id   INTEGER,
    cantidad    INTEGER        NOT NULL,
    precio      NUMERIC(19, 2) NOT NULL,
    CONSTRAINT fk_producto FOREIGN KEY (producto_id) REFERENCES producto (id),
    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id)
);




