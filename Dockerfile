FROM eclipse-temurin:17

RUN mkdir /opt/app
COPY target/rinha-backend-java-0.0.1-SNAPSHOT.jar /opt/app/rinha-backend.jar

CMD ["java", "-XshowSettings:system", "-XX:MaxRAMPercentage=75", "-jar", "/opt/app/rinha-backend.jar"]
