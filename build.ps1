# Levanta el servicio de base de datos para buildear las imágenes
docker-compose up -d database

# Build de imagen de Microservicio de BCRA
cd src/bcra
./mvnw spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Cuenta Corriente
cd src/cuenta
./mvnw spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Pedido
cd src/pedido
./mvnw spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Producto
cd src/producto
./mvnw spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Usuario
cd src/usuario
./mvnw spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Pago
cd src/pago
./mvnw spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Proxy
cd src/proxy
./mvnw spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Service Discovery
cd src/servicediscovery
./mvnw spring-boot:build-image -DskipTests=true
cd ../../

# Baja del servicio de base de datos
docker-compose up down database