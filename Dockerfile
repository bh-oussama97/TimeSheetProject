FROM openjdk:8-jre-alpine
EXPOSE 8083
ADD target/timesheet_devops-1.0.jar timesheet_devops.jar
ENTRYPOINT ["java", "-jar", "/timesheet_devops.jar"] 