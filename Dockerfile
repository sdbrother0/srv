FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN file="$(ls -1 ./)" && echo $file
# 👇 the JAR is built in build/libs/
#ARG JAR_FILE=./build/libs/srv-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar

#ENTRYPOINT ["java", "-jar", "app.jar"]