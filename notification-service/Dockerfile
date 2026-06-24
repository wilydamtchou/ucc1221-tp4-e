FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/notification-service-1.0.0.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app/app.jar"]
