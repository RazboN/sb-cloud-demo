FROM openjdk:17-alpine

ENV APP_PORT=1010

WORKDIR user-service
COPY "./build/libs/user-consumer-0.0.1-SNAPSHOT.jar" ./user-consumer.jar
EXPOSE 1007
ENTRYPOINT ["java","-jar","./user-consumer.jar"]