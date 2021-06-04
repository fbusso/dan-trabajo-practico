CREATE TABLE material
(
    id           SERIAL PRIMARY KEY,
    nombre       VARCHAR(50)    NOT NULL,
    descripcion  VARCHAR(255)   NOT NULL,
    precio       NUMERIC(19, 2) NOT NULL,
    stock_actual INTEGER        NOT NULL,
    stock_minimo INTEGER        NOT NULL,
    unidad       VARCHAR(25)    NOT NULL
);

CREATE TABLE provision
(
    id              SERIAL PRIMARY KEY,
    fecha_provision DATE
);

CREATE TABLE detalle_provision
(
    id           SERIAL PRIMARY KEY,
    cantidad     INTEGER NOT NULL,
    material_id  INTEGER,
    provision_id INTEGER,
    CONSTRAINT fk_material FOREIGN KEY (material_id) REFERENCES material (id),
    CONSTRAINT fk_provision FOREIGN KEY (provision_id) REFERENCES provision (id)
);

CREATE TABLE movimiento_stock
(
    id                   SERIAL PRIMARY KEY,
    cantidad_entrada     INTEGER NOT NULL,
    cantidad_salida      INTEGER NOT NULL,
    fecha_movimiento     DATE    NOT NULL,
    detalle_provision_id INTEGER,
    material_id          INTEGER,
    CONSTRAINT fk_detalle_provision FOREIGN KEY (detalle_provision_id) REFERENCES detalle_provision (id),
    CONSTRAINT fk_material FOREIGN KEY (material_id) REFERENCES material (id)
);




