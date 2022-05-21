FROM maven:3.8.4-jdk-8 as build
WORKDIR /build
COPY . .

RUN mvn clean package -D skipTests



FROM openjdk:8 as run
WORKDIR /app
COPY --from=build ./build/target/*.jar ./app.jar
EXPOSE 8080

ENTRYPOINT java -jar app.jar