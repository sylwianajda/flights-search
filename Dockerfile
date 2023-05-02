FROM openjdk:12-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} flights-search.jar
ENTRYPOINT ["java","-jar","/flights-search.jar"]
EXPOSE 8083

