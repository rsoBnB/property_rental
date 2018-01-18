FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./api/target/property_lease-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8084

CMD ["java", "-jar", "property_lease-api-1.0.0-SNAPSHOT.jar"]