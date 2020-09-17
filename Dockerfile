FROM gradle:4.3.0-jdk8-alpine

ENV LANG C.UTF-8

EXPOSE 8080

COPY build/libs/*.jar /api/app.jar

CMD java -Xms64m -Xmx256m -Dserver.port=8080 -jar /api/app.jar