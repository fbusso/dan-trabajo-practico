FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-bcra.jar
ENTRYPOINT ["java","-jar","/ms-bcra.jar"]