FROM openjdk:11
EXPOSE 8080
ADD target/adview-docker.jar adview-docker.jar
ENTRYPOINT [ "java", "-jar", "/adview-docker.jar" ]
