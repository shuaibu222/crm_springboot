FROM openjdk:17-jdk-alpine
## Rather than running it as a root user(i.e which is not recommended for security)
RUN addgroup -S webapps && adduser -S crm -G webapps
USER crm
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]