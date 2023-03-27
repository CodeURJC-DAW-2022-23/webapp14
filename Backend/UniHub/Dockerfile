FROM maven as builder
COPY src/ /usr/src/app/src
COPY pom.xml /usr/src/app
WORKDIR /usr/src/app
RUN mvn package -DskipTests=true

FROM openjdk:17
COPY src/main/resources/static/img/Profile-Pics /usr/src/src/main/resources/static/img/Profile-Pics
COPY --from=builder /usr/src/app/target/UniHub-0.0.1-SNAPSHOT.jar /usr/src/
WORKDIR /usr/src
EXPOSE 8443
CMD ["java","-jar","UniHub-0.0.1-SNAPSHOT.jar"]