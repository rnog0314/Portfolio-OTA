FROM openjdk:11
EXPOSE 8080
RUN mvn clean install
ADD target/adview-docker.jar adview-docker.jar
ENTRYPOINT [ "java", "-jar", "/adview-docker.jar" ]
