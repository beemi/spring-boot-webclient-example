FROM --platform=linux/x86_64 amazoncorretto:11-alpine-jdk

RUN apk update \
    && apk upgrade \
    && apk --no-cache add --update bash tcl apache2 ca-certificates \
    apk-tools curl build-base supervisor cups-client dcron bind-tools rsync libxml2-utils libxslt unzip dos2unix && \
    rm -rf /var/cache/apk/*

ENV USER_GROUP=jaitechltd
ENV USER_NAME=jaitechltd

ARG JAR_NAME
ARG SPRING_PROFILES_ACTIVE
ENV SERVICE_JAR_NAME=${JAR_NAME}

# create usergroup & user
RUN addgroup -S $USER_GROUP && \
    adduser -S $USER_NAME -G $USER_GROUP && \
    mkdir /home/app && \
    chown $USER_NAME /home/app

USER $USER_NAME
WORKDIR /home/app

COPY --chown=$USER_NAME:$USER_GROUP /target/${JAR_NAME}*.jar ${JAR_NAME}.jar

EXPOSE ${SERVER_PORT} 8080 8090

ENTRYPOINT exec java \
           -jar ${JAR_NAME}.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE}
