# CLINIC API Documentation

## Overview
This **CLINIC API** was built using the following technologies and tools:

<div >
    <img height="60px" width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg" alt="Spring"/>
    <img height="60px" width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" alt="Java"/>
    <img height="60px" width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mysql/mysql-original.svg" alt="MySQL"/>
   <img  height="60px" width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original.svg" alt="postman"/>
    <img height="60px" width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg" alt="IntelliJ IDEA"/>
    <img height="60px" width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/dbeaver/dbeaver-original.svg"  alt="dbeaver"/>
</div>

## Configuration
### Application Properties
Update the `application.properties` file with the following configuration:

```properties
# Application Name and Port
spring.application.name=ClinicAPI
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Security Configuration
api.security.token.secret=${JWT_SECRET:my-secret-key}
springdoc.swagger-ui.path=/swagger-ui.html

```
# SMTP Setup
[To set up your Gmail as an SMTP server, follow this](https://youtu.be/ugIUObNHZdo?si=MwG79gZohHWhtMxD)

# Documentation Access
 ## The API documentation is accessible at:

### . localhost:8080/swagger-ui/index.html

# Requirements
### . JDK 17 or higher

### . MySQL installed on your machine

# Authentication
**The API uses JWT for authentication. You can test the endpoints using Postman or a similar tool.**