#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

# Install curl
RUN apt-get update && apt-get install -y curl

#Information around who maintains the image
MAINTAINER justamitsaha@gmail.com

EXPOSE 8090

# Add the application's jar to the container
COPY target/springBootJPA-999.jar springBootJPA-999.jar

#execute the application
ENTRYPOINT ["java","-jar","/springBootJPA-999.jar"]

# mvn clean install
# docker build . -t justamitsaha/spring-boot-jpa:v1
# docker push justamitsaha/spring-boot-jpa:v1
#docker run -d -p 8080:8080 justamitsaha/spring-boot-jpa:v1