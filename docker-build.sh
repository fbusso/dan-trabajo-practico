# Levanta el servicio de base de datos para buildear las imágenes
docker-compose up -d database

docker-compose up -d grafana     # Grafana
docker-compose up -d prometheus  # Prometheus
docker-compose up -d rabbit-mq   # RabbitMQ
docker-compose up -d keycloak    # Keycloak

# Build de imagen de Microservicio de BCRA
cd src/bcra
mvn spring-boot:build-image -DskipTests=true -Pdeploy
cd ../../

# Build de imagen de Microservicio de Cuenta Corriente
cd src/cuenta
mvn spring-boot:build-image -DskipTests=true -Pdeploy
cd ../../

# Build de imagen de Microservicio de Pedido
cd src/pedido
mvn spring-boot:build-image -DskipTests=true -Pdeploy
cd ../../

# Build de imagen de Microservicio de Producto
cd src/producto
mvn spring-boot:build-image -DskipTests=true -Pdeploy
cd ../../

# Build de imagen de Microservicio de Usuario
cd src/usuario
mvn spring-boot:build-image -DskipTests=true -Pdeploy
cd ../../

## Servicios de Docker ##

docker-compose up -d ms-bcra         # Inicialización del Servicio de BCRA

docker-compose up flyway-ms-cuenta   # Migración de la base de datos del Microservicio de Cuenta Corriente
docker-compose up -d ms-cuenta       # Inicialización del Servicio de Cuenta Corriente

docker-compose up flyway-ms-pedido   # Migración de la base de datos del Microservicio de Pedidos
docker-compose up -d ms-pedido       # Inicialización del Servicio de Pedidos

docker-compose up flyway-ms-producto # Migración de la base de datos del Microservicio de Productos
docker-compose up -d ms-producto     # Inicialización del Servicio de Productos

docker-compose up flyway-ms-usuario  # Migración de la base de datos del Microservicio de Usuario
docker-compose up -d ms-usuario      # Inicialización del Servicio de Usuario