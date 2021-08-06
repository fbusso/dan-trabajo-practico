CREATE DATABASE "usuario-db";
CREATE USER usuario_application WITH PASSWORD 'AQG5VAEZCKBzEwFg';
GRANT ALL PRIVILEGES ON DATABASE "usuario-db" to usuario_application;

CREATE DATABASE "cliente-db";
CREATE USER cliente_application WITH PASSWORD 'nvhYPZk74dAjkSJQ';
GRANT ALL PRIVILEGES ON DATABASE "cliente-db" to usuario_application;

CREATE DATABASE "producto-db";
CREATE USER producto_application WITH PASSWORD 'b8DNZsX7WDhFaep4';
GRANT ALL PRIVILEGES ON DATABASE "producto-db" to producto_application;

CREATE DATABASE "pedido-db";
CREATE USER pedido_application WITH PASSWORD 'U5zRu6Vgmx6PmrmU';
GRANT ALL PRIVILEGES ON DATABASE "pedido-db" to pedido_application;

CREATE DATABASE "cuenta-db";
CREATE USER cuenta_application WITH PASSWORD 'AQG5VAEZCKBzEwFg';
GRANT ALL PRIVILEGES ON DATABASE "cuenta-db" to cuenta_application;

CREATE DATABASE "keycloak-db";
CREATE USER keycloak_application WITH PASSWORD 'FNvJ1ThgdcvU390G';
GRANT ALL PRIVILEGES ON DATABASE "keycloak-db" to keycloak_application;