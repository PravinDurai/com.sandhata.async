FROM maven:3.8.2-jdk-11

#USER pravinsm94

MAINTAINER s_m_pravin@yahoo.com

WORKDIR /springboot-async

#ADD target/LicenseManagement.jar LicenseManagement.jar

COPY . .

#RUN mvn clean install

EXPOSE 9095

CMD mvn spring-boot:run