#!/bin/sh
if [ -z ${RUN_GRADLE_TEST+x} ]; then
  echo "running gradle without test task";
    ./gradlew build javadoc jar versionFile -x test --info --stacktrace
else
  echo "running gradle with test task";
  ./gradlew build javadoc jar versionFile --info --stacktrace
fi

