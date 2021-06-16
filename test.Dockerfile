FROM alpine:3.14.0 AS LOCAL_FILES
WORKDIR /app_files/
COPY ./ ./

FROM openjdk:16-jdk-alpine AS TEST
WORKDIR /app_test/
COPY --from=LOCAL_FILES /app_files/ /app_test/
RUN chmod +x /app_test/scripts/sh/run-qa.sh
RUN chmod +x /app_test/gradlew

RUN apk add --no-cache git curl bash findutils

ENV spring_profiles_active=ci