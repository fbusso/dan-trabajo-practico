FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-pago.jar
ENTRYPOINT ["java","-jar","/ms-pago.jar"]