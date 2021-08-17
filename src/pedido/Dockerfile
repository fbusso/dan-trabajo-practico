FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-pedido.jar
ENTRYPOINT ["java","-jar","/ms-pedido.jar"]