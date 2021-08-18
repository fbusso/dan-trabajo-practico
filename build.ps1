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