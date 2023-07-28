FROM openjdk:17-alpine

WORKDIR app-menu-command
COPY "./build/libs/menu-0.0.1-SNAPSHOT.jar" ./api-gateway.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","./api-gatewayjar"]