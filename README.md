# Urban Ride: A Microservices-Based Ride-Hailing Platform

Urban Ride is a comprehensive, scalable, and resilient ride-hailing application built on a modern microservices architecture. This platform simulates the core functionalities of services like Uber and Lyft, demonstrating a robust backend system capable of handling user authentication, real-time location tracking, ride booking, and more.

This repository serves as a monorepo, containing the complete ecosystem of services that power the Urban Ride platform.

## ✨ Key Features

The Urban Ride platform is designed with a rich feature set, ensuring a complete and seamless user experience from registration to ride completion.

### Passenger & Driver Management

  * **Secure User Authentication**: End-to-end user security with JWT-based authentication for both passengers and drivers.
  * **Seamless Registration & Login**: Effortless onboarding process for new users and a secure login system for existing ones.
  * **Profile Management**: Functionality for users to manage their profiles and preferences.

### Real-Time & Location-Based Services

  * **Live Location Tracking**: Drivers' locations are tracked in real-time using a high-performance, Redis-backed location service.
  * **Find Nearby Drivers**: Passengers can instantly see available drivers in their vicinity, with data served efficiently from a Redis cache.
  * **Dynamic Ride Booking**: A sophisticated booking system that allows passengers to request rides, which are then matched with the nearest available drivers.

### Asynchronous & Event-Driven Communication

  * **Real-Time Ride Updates**: Leverages WebSockets and Apache Kafka for instant communication of ride status changes (e.g., driver assigned, arriving, ride started).
  * **Decoupled Services**: Asynchronous communication between services ensures high resilience and scalability. If one service is temporarily down, others can continue to function.

### Ride Lifecycle & Post-Ride Features

  * **End-to-End Booking Flow**: A complete ride lifecycle from request and driver acceptance to cancellation, completion, and payment processing.
  * **Rating and Review System**: A dedicated service for passengers and drivers to rate and review each other after a completed ride, ensuring quality and trust.

-----

## 🛠️ Technology Stack

This project is built using a modern, industry-standard technology stack chosen for scalability, performance, and maintainability.

| Category                  | Technology                                                                                                    |
| ------------------------- | ------------------------------------------------------------------------------------------------------------- |
| **Backend & Frameworks** | Java 17, Spring Boot 3, Spring Cloud, Spring Security                                                         |
| **Databases & Caching** | MySQL (for persistent relational data), Redis (for caching and high-speed geospatial queries)                 |
| **Messaging & Events** | Apache Kafka (for asynchronous, event-driven communication between microservices)                           |
| **Microservice Architecture** | Spring Cloud Gateway (API Gateway), Netflix Eureka (Service Discovery)                                      |
| **Authentication** | JSON Web Tokens (JWT) for stateless, secure authentication                                                    |
| **Real-time Communication** | Spring WebSocket                                                                                              |
| **Database Migration** | Flyway                                                                                                        |
| **Build & Dependency** | Gradle                                                                                                        |
| **Deployment & DevOps** | Terraform (Infrastructure as Code), ELK Stack & Prometheus (Monitoring)                                         |

-----

## 🏛️ Microservice Architecture

The platform is divided into a set of independent, single-purpose services, each handling a distinct business domain.

| Service                       | Description                                                                                             | Individual Repository Link                                            |
| ----------------------------- | ------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------- |
| **`discovery-service`** | A Netflix Eureka server that acts as the service registry, allowing all other microservices to find each other. | [discovery-service](https://github.com/Mystical-Coder/urban-ride-discovery-service.git) |
| **`urban-ride-api-gateway`** | The single entry point for all client requests. It handles routing, security (JWT validation), and load balancing. | [urban-ride-api-gateway](https://github.com/Mystical-Coder/urban-ride-api-gateway.git)       |
| **`auth-service`** | Manages user registration and login. Issues JWTs upon successful authentication.                             | [auth-service](https://github.com/Mystical-Coder/urban-ride-auth-service.git)      |
| **`location-service`** | Tracks and serves real-time driver locations using Redis for high-performance geospatial queries.             | [location-service](https://github.com/Mystical-Coder/urban-ride-location-service.git)  |
| **`booking-service`** | Orchestrates the entire ride booking process, from finding nearby drivers to managing the ride lifecycle. | [booking-service](https://github.com/Mystical-Coder/urban-ride-booking-service.git)   |
| **`review-service`** | Allows passengers and drivers to submit ratings and reviews after a ride is completed.                      | [review-service](https://github.com/Mystical-Coder/urban-ride-review-service.git)    |
| **`websocket-service`** | Facilitates real-time, bidirectional communication between the server and clients for live updates.        | [websocket-service](https://github.com/Mystical-Coder/urban-ride-websocket-service.git) |
| **`urban-ride-domain`** | A shared library containing the core JPA entities and data models used across multiple services.               | [urban-ride-domain](https://github.com/Mystical-Coder/urban-ride-domain.git)    |

-----

## 🚀 Getting Started

To run the entire Urban Ride platform locally, you will need the following prerequisites:

  * Java 17 or higher
  * A running instance of MySQL
  * A running instance of Redis
  * A running instance of Kafka

### Running the Services

The services must be started in a specific order to ensure proper dependency resolution and service registration.

1.  **Start Discovery Service:**

    ```bash
    cd discovery-service
    ./gradlew bootRun
    ```

    Wait for it to start, then navigate to `http://localhost:8761` to see the Eureka dashboard.

2.  **Start Core Services:** Start the following services in any order. It's recommended to open a new terminal for each service.

    ```bash
    cd auth-service
    ./gradlew bootRun
    ```

    ```bash
    cd location-service
    ./gradlew bootRun
    ```

    ```bash
    cd booking-service
    ./gradlew bootRun
    ```

    ```bash
    cd review-service
    ./gradlew bootRun
    ```

    ```bash
    cd websocket-service
    ./gradlew bootRun
    ```

3.  **Start API Gateway:** The gateway should be started last.

    ```bash
    cd urban-ride-api-gateway
    ./gradlew bootRun
    ```

Your Urban Ride platform is now running\! All API requests should be directed to the API Gateway at `http://localhost:8080`.

-----

## 🔮 Future Roadmap & Enhancements

This project is built with future scalability in mind. The following enhancements are planned to make the platform even more robust and production-ready.

  * **Infrastructure as Code (IaC)**: Integrate **Terraform** to automate the provisioning of cloud infrastructure on platforms like AWS or GCP, ensuring consistent and repeatable deployments.

  * **CI/CD Pipeline**: Implement a full CI/CD pipeline using **GitHub Actions** or **Jenkins** to automate the building, testing, and deployment of each microservice.

  * **Comprehensive Monitoring**:

      * **Logging**: Centralize logs from all microservices using the **ELK Stack (Elasticsearch, Logstash, Kibana)** or **EFK (with Fluentd)** for powerful searching and analysis.
      * **Metrics**: Use **Prometheus** to scrape key application metrics and **Grafana** to create insightful dashboards for monitoring system health.
      * **Distributed Tracing**: Integrate **Jaeger** or **Zipkin** to trace requests as they travel through multiple services, making it easy to identify performance bottlenecks.

  * **Containerization**: Fully containerize each microservice using **Docker** and create **Docker Compose** files for a streamlined, one-command local setup.
