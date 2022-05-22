FROM openjdk:14-jdk-alpine
EXPOSE 8080
COPY target/hotelBK-0.1.jar hotelBK-0.1.jar
ENTRYPOINT ["java","-jar","/hotelBK-0.1.jar"]