############################################
#          DO NOT TOUCH THIS FILE          #
############################################

FROM openjdk:8-alpine

RUN apk add gradle

RUN mkdir -p "/lhgames"
WORKDIR "/lhgames"
COPY . .

RUN gradle fatJar

EXPOSE 3000

CMD ["java", "-jar", "build/libs/java-seed-all-1.0-SNAPSHOT.jar"]
