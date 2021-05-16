FROM openjdk:15-slim

ENV LOG_LEVEL INFO

ADD target/demo2-0.0.1-SNAPSHOT.jar /usr/src/docker-super-1.0.jar

WORKDIR /usr/src

EXPOSE 8082

ENTRYPOINT java -Dlogging.level.ru.docker=$LOG_LEVEL -Dspring.datasource.url=$JDBC_URL -Dspring.datasource.username=$JDBC_USER -Dspring.datasource.password=$JDBC_PASS -jar docker-super-1.0.jar