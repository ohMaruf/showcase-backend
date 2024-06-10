# Showcase Backend

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
[![License](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

## Table of Contents
<!-- TOC -->
* [Showcase Backend](#showcase-backend)
  * [Table of Contents](#table-of-contents)
  * [Overview](#overview)
  * [Features](#features)
  * [Future Developments](#future-developments)
  * [Getting Started](#getting-started)
    * [Requirements](#requirements)
    * [Installation Steps](#installation-steps)
  * [Contributing](#contributing)
  * [Liked my work?](#liked-my-work)
<!-- TOC -->

## Overview
This project shows the current best practices for backend development
This is a Spring Boot backend application designed to interact with a PostgreSQL database.

**WARNING**: The project is still in development and misses a lot of functionalities.

## Features
- **Spring Boot Backend**: Utilizes Spring Boot for creating a RESTful API.
- **PostgreSQL Integration**: Interacts with a PostgreSQL database for persistent storage.
- **Basic CRUD Operations**
  
## Future Developments
This project is a work in progress with several planned enhancements:

- **Additional Tests**: Increase test coverage to ensure the reliability and maintainability of the codebase.
- **New Endpoints**: Introduce more endpoints to extend the functionality and meet diverse requirements.
- **Swagger Integration**: Add Swagger for API documentation to make the endpoints easy to understand and test.
- **Authentication Client**: Implement an authentication client to manage secure access to the API.

## Getting Started

### Requirements
- Java 21 LTS (will work also with Java 11+)
- Maven
- PostgreSQL database

### Installation Steps

1. Clone the repository
    ```sh
    git clone https://github.com/ohMaruf/showcase-backend.git
    cd showcase-backend
    ```

2. Setup PostgreSQL <br>
Update application.properties with your PostgreSQL configuration:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Build the project
    ```sh
    mvn clean install
    ```

4. Run the application
    ```sh
    mvn spring-boot:run
    ```

## Contributing
This project is currently closed to contributions. However, you are welcome to fork the repository and build upon it for your own experiments or projects.

## Liked my work?
Reach out to me on [LinkedIn](https://www.linkedin.com/in/ahmed-maruf-15684a212/)

Thank you for reading! Consider leaving a star if you liked the project ☆\*: .｡. o(≧▽≦)o .｡.:\*☆
