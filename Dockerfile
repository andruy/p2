FROM openjdk:8
EXPOSE 8082
ADD target/project02.jar app.jar
ENTRYPOINT [ "java" , "-jar" , "/app.jar"]