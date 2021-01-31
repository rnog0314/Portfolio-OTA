FROM openjdk:11
EXPOSE 8080
# ENV POSTGRES_PASSWORD=password
VOLUME /tmp
# RUN mkdir /app.jar
# WORKDIR /the/workdir/path
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
ADD target/portfolio-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
