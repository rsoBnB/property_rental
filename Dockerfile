FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./api/target/property_rental-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8082

CMD ["java", "-jar", "property_rental-api-1.0.0-SNAPSHOT.jar"]