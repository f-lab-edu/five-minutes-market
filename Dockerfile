FROM adoptopenjdk/openjdk11:alpine AS builder

VOLUME /vol

ARG jar_file=build/libs/*.jar
COPY ${jar_file} app.jar

ENTRYPOINT ["java", \
"-jar", \
"-Dspring.profiles.active=${PROFILE_OPTION}", \
"-Dspring.datasource.url=${SPRING_DATASOURCE_URL}", \
"-Dmail.username=${MAIL_USERNAME}", \
"-Dmail.password=${MAIL_PASSWORD}", \
"-Dredis.host=${REDIS_HOST}", \
"/app.jar"]