#!/usr/bin/env sh
if ./gradlew test jacocoReport --info --stacktrace; then
  if [ -z ${CODECOV_TOKEN+x} ]; then
    echo "skipping reporting coverage to codecov"
  else
    bash -c '/bin/bash <(curl -s https://codecov.io/bash)'
  fi
else
  exit 1
fi



