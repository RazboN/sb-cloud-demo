FROM openjdk:17-alpine

WORKDIR config-server
COPY "./build/libs/config-server-0.0.1-SNAPSHOT.jar" ./config-sever.jar
EXPOSE 1080
ENTRYPOINT ["java","-jar","./config-server.jar"]