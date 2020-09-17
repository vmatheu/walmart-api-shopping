FROM gradle:4.3.0-jdk8-alpine

ENV LANG C.UTF-8

EXPOSE $PORT

COPY build/libs/*.jar /api/app.jar

CMD java -Xms64m -Xmx256m -Dserver.port=$PORT -jar /api/app.jar