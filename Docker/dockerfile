FROM node:16 AS angular
WORKDIR /code
COPY Angular-SPA/package*.json Angular-SPA/angular.json Angular-SPA/tsconfig*.json /code/
RUN npm install --legacy-peer-deps
COPY Angular-SPA /code
WORKDIR /code
RUN npm run build -- --configuration production --base-href=/new/ --output-path=/code/dist/angular-spa/

FROM maven as builder
WORKDIR /usr/src/app
COPY Backend/UniHub/src/ /usr/src/app/src
COPY Backend/UniHub/pom.xml /usr/src/app
COPY --from=angular /code/dist/angular-spa/ /usr/src/app/src/main/resources/static/new/
RUN mvn package -DskipTests=true

FROM openjdk:17
WORKDIR /usr/src/app
COPY Backend/UniHub/src/main/resources/static/img/Profile-Pics /usr/src/app/src/main/resources/static/img/Profile-Pics
COPY --from=builder /usr/src/app/target/UniHub-0.0.1-SNAPSHOT.jar /usr/src/app/
EXPOSE 8443
CMD ["java","-jar","UniHub-0.0.1-SNAPSHOT.jar"]
