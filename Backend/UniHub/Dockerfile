FROM maven as builder
COPY src/ /usr/src/app/src
COPY pom.xml /usr/src/app
WORKDIR /usr/src/app
RUN mvn package -DskipTests=true

FROM openjdk:17
COPY --from=builder /usr/src/app/target/UniHub-0.0.1-SNAPSHOT.jar /usr/src/
WORKDIR /usr/src
EXPOSE 8443
CMD ["java","-jar","UniHub-0.0.1-SNAPSHOT.jar"]

#FROM maven:3.8.3-jdk-11
 #
 ## Copy source code
 #COPY src /usr/src/app/src
 #COPY pom.xml /usr/src/app
 #
 ## Define working directory
 #WORKDIR /usr/src/app
 #
 ## Copy JAR
 #COPY target/UniHub-0.0.1-SNAPSHOT.jar /usr/src/app
 #
 ## Expose port
 #EXPOSE 8443
 #
 ## Execute JAR
 #CMD ["java", "-jar", "/usr/src/app/UniHub-0.0.1-SNAPSHOT.jar"]