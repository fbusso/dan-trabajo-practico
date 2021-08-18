# Levanta el servicio de base de datos para buildear las imágenes
docker-compose up -d database

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

# Baja del servicio de base de datos
docker-compose up down database