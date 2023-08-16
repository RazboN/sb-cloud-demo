FROM openjdk:17-alpine

WORKDIR api-gateway
COPY "./build/libs/gateway-0.0.1-SNAPSHOT.jar" ./api-gateway.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","./api-gateway.jar"]