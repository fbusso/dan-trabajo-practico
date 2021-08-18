# Build de imagen de Microservicio de BCRA
cd src/bcra
mvn spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Cuenta Corriente
cd src/cuenta
mvn spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Pedido
cd src/pedido
mvn spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Producto
cd src/producto
mvn spring-boot:build-image -DskipTests=true
cd ../../

# Build de imagen de Microservicio de Usuario
cd src/usuario
mvn spring-boot:build-image -DskipTests=true
cd ../../