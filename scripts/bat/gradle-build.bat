IF "%RUN_GRADLE_TEST%"=="" (
    ECHO "Running gradle without test task"
    gradlew.bat build javadoc jar versionFile -x test --info --stacktrace
) else (
    ECHO "Running gradle with test task"
    gradlew.bat build javadoc jar versionFile --info --stacktrace
)


