FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/zazie-0.0.1-SNAPSHOT.jar zazie.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","zazie.jar"]


