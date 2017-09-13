FROM java:8
VOLUME /tmp
ADD auth-microservice-0.0.1-SNAPSHOT.jar auth-microservice.jar
RUN bash -c ‘touch /auth-microservice.jar’
ENTRYPOINT [“java”,”-Djava.security.egd=file:/dev/./urandom”,”-jar”,”/auth-microservice.jar”]