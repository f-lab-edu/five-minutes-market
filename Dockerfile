FROM adoptopenjdk/openjdk11:alpine AS builder

VOLUME /vol

ARG jar_file=build/libs/*.jar
ARG apm_agent=apm-agent/*.jar

COPY ${jar_file} app.jar

ENTRYPOINT ["java", \
"-Dspring.profiles.active=${PROFILE_OPTION}", \
"-Dspring.datasource.url=${SPRING_DATASOURCE_URL}", \
"-Dkakao.admin.token=${KAKAO_ADMIN_TOKEN}", \
"-Dmail.username=${MAIL_USERNAME}", \
"-Dmail.password=${MAIL_PASSWORD}", \
"-Dredis.host=${REDIS_HOST}", \
"-jar", \
"/app.jar"]