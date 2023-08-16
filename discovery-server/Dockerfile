FROM openjdk:17-alpine

WORKDIR discovery-server
COPY "./build/libs/discovery-server-0.0.1-SNAPSHOT.jar" ./discovery-server.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","./discovery-server.jar"]