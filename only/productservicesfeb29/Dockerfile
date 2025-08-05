FROM openjdk:17

COPY target/productservicesfeb29-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENV SERVER_PORT=8085

ENTRYPOINT ["java", "-jar", "app.jar"]

