FROM openjdk:11
ADD target/BackEndVivoApplication.jar BackEndVivoApplication.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "BackEndVivoApplication.jar"]
