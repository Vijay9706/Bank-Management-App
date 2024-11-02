From openjdk:24-slim-bullseye
ADD  target/bank-management-app.jar bank-management-app.jar
ENTRYPOINT ["java", "-jar", "/bank-management-app.jar"]