# Sports Complex Web Application

Welcome to the Sports Complex project! This repository contains a solution for managing a sports complex, providing customers with a flexible subscription mechanism to access various activity areas.

# Technologies and Tools Used
The project was implemented using the following technologies and tools:

- Spring Boot
- Java 17
- Maven
- PostgreSQL
- H2 in-memory database
- MockMVC for integration tests
- JUnit for unit tests
- Mockito for dependency mocking in unit tests
- Hibernate 
- Twilio (as a provider for SMS)

# Features

- Flexible subscription mechanism: Customers can choose from a variety of subscription plans to access different activity areas within the sports complex.
- Database Integration: PostgreSQL is used to store customer information and subscription details, while H2 in-memory database is utilized for testing purposes.
- Automated Testing: MockMVC, JUnit, and Mockito are employed to perform integration and unit tests, ensuring the reliability of the application.
- SMS Notifications: Twilio integration allows the system to send SMS notifications to customers regarding their subscriptions and other relevant updates.

# Getting Started

To get started with the Sports Complex project, follow these steps:

1. Clone the repository to your local machine.
2. Make sure you have Java 17 and Maven installed.
3. Set up the PostgreSQL database and configure the application.properties file with the appropriate database credentials.
4. Run the application using Spring Boot's embedded Tomcat server.
   mvn spring-boot:run
5.  Access the application in your web browser at 'http://localhost:8080'.

# Contributing 

Contributions are welcome! I'm always open to feedback and collaboration. If you have any suggestions, improvements, or ideas, please feel free to open an issue or submit a pull request. If you want to contribute to the project, please follow the standard GitHub workflow and submit a pull request.
