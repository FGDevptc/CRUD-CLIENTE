FROM openjdk

WORKDIR /app

EXPOSE 8080

COPY target/spring-app-0.0.1-SNAPSHOT.jar /app/crud-backend.jar

ENTRYPOINT ["java","-jar","crud-backend.jar"]