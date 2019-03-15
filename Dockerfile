FROM openjdk:8
MAINTAINER vmflex
LABEL app="GCW" version="0.0.1" by="vmflex"
COPY ./GetCurrentWeather-0.0.1-SNAPSHOT.jar gcw.jar
CMD java -jar gcw.jar