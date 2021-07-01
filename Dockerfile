FROM adoptopenjdk/openjdk11:alpine AS builder

VOLUME "/vol"

ARG profile_option
ENV PROFILE_OPTION=${profile_option}

ARG mail_username
ENV MAIL_USERNAME=${mail_username}

ARG mail_password
ENV MAIL_PASSWORD=${mail_password}

ARG jar_file=build/libs/*.jar
COPY ${jar_file} app.jar

ENTRYPOINT ["java", \
"-jar", \
"-Dspring.profiles.active=${PROFILE_OPTION}", \
"-Dmail.username=${MAIL_USERNAME}", \
"-Dmail.password=${MAIL_PASSWORD}", \
"/app.jar"]