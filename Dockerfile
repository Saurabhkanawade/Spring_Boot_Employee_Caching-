FROM openjdk:11
ADD target/employeproject-0.0.1-SNAPSHOT.jar employeproject.jar
ENTRYPOINT ["java","-jar","/employeproject.jar"]







