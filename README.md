# SpringMicroservices

## Overview
This project demonstrates a microservices architecture using Spring technologies. It consists of multiple independent services that communicate via REST APIs and follow best practices for scalability and maintainability.

## Features
- Microservices architecture with Spring Boot
- Service discovery with Eureka
- API Gateway using Spring Cloud Gateway
- Inter-service communication with OpenFeign
- Centralized configuration with Spring Cloud Config
- Database integration with JPA and MySQL

## Project Structure
```
SpringMicroservices/
|-- eureka-registry/     # Eureka Server for service discovery
|-- api-gateway/          # API Gateway with Spring Cloud Gateway
|-- question-service/         # Manages user-related operations
|-- quiz-service/        # Handles order processing
```

## Prerequisites
- Java 17+
- Maven 3.8+
- MySQL (for database storage)

## Installation & Running

### Clone Repository
```sh
git clone https://github.com/stakerAchyut/SpringMicroservices.git
cd SpringMicroservices
```

### Start Services
Run the following services in order:

#### Start Service Registry (Eureka)
```sh
cd eureka-registry
mvn spring-boot:run
```

#### Start Config Server
```sh
cd ../question-server
mvn spring-boot:run
```

#### Start API Gateway
```sh
cd ../api-gateway
mvn spring-boot:run
```

#### Start Other Microservices
```sh
cd ../quiz-service
mvn spring-boot:run
```

## API Endpoints
| Service         | Endpoint Example |
|----------------|-----------------|
| Quiz Service   | `/create`    |
| Quiz Service   | `/get/{type}`    |
| Quiz Service   | `/submit`    |
| Question Service  | `/type/{type}`   |
| Question Service  | `/submit`   |

All services are accessed via the API Gateway:
```
http://localhost:8080/{service-name}/{endpoint}
```

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.

