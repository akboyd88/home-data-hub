FROM alpine:3.14.0 AS LOCAL_FILES
WORKDIR /app_files/
COPY ./ ./

FROM openjdk:16-jdk-alpine AS JAVA_BUILD
WORKDIR /app_build/
COPY --from=LOCAL_FILES /app_files/ /app_build/
RUN chmod +x /app_build/scripts/sh/gradle-build.sh
RUN chmod +x /app_build/gradlew
RUN /app_build/scripts/sh/gradle-build.sh

FROM openjdk:16-jdk-alpine AS RUNTIME
WORKDIR /app
COPY --from=JAVA_BUILD /app_build/build/libs/ /app/
COPY --from=JAVA_BUILD /app_build/build/VERSION /app/
CMD /opt/openjdk-16/bin/java -jar -Dspring.profiles.active=prod home-data-hub-$(cat /app/VERSION).jar
