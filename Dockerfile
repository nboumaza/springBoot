FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/zazie-0.0.1-SNAPSHOT.jar zazie.jar
#ENV PORT=9090
#EXPOSE 9090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","zazie.jar"]


