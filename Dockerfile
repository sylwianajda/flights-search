FROM openjdk:17-jdk-slim
# ARG JAR_FILE
COPY ./target/FlightSearch-0.0.1-SNAPSHOT.jar /flights-search.jar
ENTRYPOINT ["java","-jar","./flights-search.jar"]
EXPOSE 8083
