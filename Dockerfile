FROM openjdk:8-alpine
WORKDIR /demo_app 
COPY ./target/bankinformation-0.0.1-SNAPSHOT.jar /demo_app/demo_app.jar
ENTRYPOINT ["java", "-jar", "/demo_app/demo_app.jar"]
