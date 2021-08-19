FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-proxy.jar
ENTRYPOINT ["java","-jar","/ms-proxy.jar"]