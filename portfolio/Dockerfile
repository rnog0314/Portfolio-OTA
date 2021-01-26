FROM openjdk:11-jre-slim
VOLUME /tmp
ADD target/portfolio-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
