FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-service-discovery.jar
ENTRYPOINT ["java","-jar","/ms-service-discovery.jar"]