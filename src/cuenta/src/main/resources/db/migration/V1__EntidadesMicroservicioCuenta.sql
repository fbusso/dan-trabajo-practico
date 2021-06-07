CREATE TABLE medio_pago
(
    id             SERIAL PRIMARY KEY,
    observacion    VARCHAR(255) NOT NULL
);

CREATE TABLE medio_pago_cheque
(
    id            SERIAL PRIMARY KEY,
    numero_cheque INTEGER     NOT NULL,
    fecha_cobro   DATE        NOT NULL,
    banco         VARCHAR(20) NOT NULL,
    medio_pago_id INTEGER,
    CONSTRAINT fk_medio_pago FOREIGN KEY (medio_pago_id) REFERENCES medio_pago (id)
);

CREATE TABLE medio_pago_efectivo
(
    id            SERIAL PRIMARY KEY,
    numero_recibo INTEGER NOT NULL,
    medio_pago_id INTEGER,
    CONSTRAINT fk_medio_pago FOREIGN KEY (medio_pago_id) REFERENCES medio_pago (id)
);

CREATE TABLE medio_pago_transferencia
(
    id            SERIAL PRIMARY KEY,
    cbu_origen    VARCHAR(20) NOT NULL,
    cbu_destino   VARCHAR(20) NOT NULL,
    medio_pago_id INTEGER,
    CONSTRAINT fk_medio_pago FOREIGN KEY (medio_pago_id) REFERENCES medio_pago (id)
);

CREATE TABLE pago
(
    id            SERIAL PRIMARY KEY,
    pedido_id     INTEGER NOT NULL,
    fecha_pago    DATE    NOT NULL,
    medio_pago_id INTEGER,
    CONSTRAINT fk_medio_pago FOREIGN KEY (medio_pago_id) REFERENCES medio_pago (id)
);