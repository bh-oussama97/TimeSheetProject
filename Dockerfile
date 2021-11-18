FROM openjdk:8-jre-alpine
EXPOSE 8084
ADD target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1.0.jar timesheet_devops.jar
ENTRYPOINT ["java", "-jar", "/timesheet_devops.jar"] 