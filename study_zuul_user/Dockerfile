#from java:1.8
FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 9200
ENTRYPOINT ["java","-jar","/app.jar"]