FROM eclipse-temurin:17

RUN mkdir /opt/app
COPY target/rinha-backend-java-0.0.1-SNAPSHOT.jar /opt/app/rinha-backend.jar

EXPOSE 8080
CMD ["java", "-XshowSettings:vm", "-Xss256k", "-XX:MaxRAMPercentage=75", "-jar", "/opt/app/rinha-backend.jar"]
