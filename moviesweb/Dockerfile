FROM openjdk:11
COPY build/libs/moviesweb-0.0.1-SNAPSHOT.jar MovieswebApplication.jar
ENTRYPOINT ["java","-jar","/MovieswebApplication.jar","--spring.profiles.active=localpsql"]