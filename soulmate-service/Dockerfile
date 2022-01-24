FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 8088
ADD ./target/soulmate-service-0.0.1-SNAPSHOT.jar /usr/src/soulmate-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","soulmate-service-0.0.1-SNAPSHOT.jar"]