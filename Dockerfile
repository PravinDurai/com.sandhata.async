FROM maven:3.8.2-jdk-11

USER sandhata

MAINTAINER pravin.durai@sandhata.com

WORKDIR /springboot-async

COPY . .

RUN mvn clean install

EXPOSE 8080

CMD mvn spring-boot:run