# Digi Bank Microservices

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2024.x-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16%2B-336791)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED)
![Docker Compose](https://img.shields.io/badge/Docker%20Compose-Orchestrated-1D63ED)
![CI](https://img.shields.io/badge/CI-GitHub%20Actions-black)
![Architecture](https://img.shields.io/badge/Architecture-Microservices-purple)
![License](https://img.shields.io/badge/License-Academic-lightgrey)

A final project for the **UCC122-1 Cloud Architecture** course focused on the design and implementation of a **microservices architecture** for Digi Bank using Spring Boot, PostgreSQL, Docker, Docker Compose, and GitHub Actions [file:13].

This project is the evolution of a previously implemented modular monolith into a distributed system organized around independent business services. It demonstrates how to split a banking platform into autonomous services while introducing key architectural patterns such as API Gateway, Service Discovery, Config Server, Circuit Breaker, Saga, and CQRS [file:13].

---

## Table of Contents

- [Overview](#overview)
- [Objectives](#objectives)
- [Architecture](#architecture)
- [Mermaid Diagram](#mermaid-diagram)
- [Services](#services)
- [Technology Stack](#technology-stack)
- [Repository Structure](#repository-structure)
- [Prerequisites](#prerequisites)
- [Database Strategy](#database-strategy)
- [Build and Run](#build-and-run)
- [Ports](#ports)
- [API Examples](#api-examples)
- [Postman Testing](#postman-testing)
- [Testing Strategy](#testing-strategy)
- [Dockerization and Orchestration](#dockerization-and-orchestration)
- [CI/CD](#cicd)
- [Technical Decisions](#technical-decisions)
- [Implementation Roadmap](#implementation-roadmap)
- [Deliverables](#deliverables)
- [Expected Outcome](#expected-outcome)
- [Academic Context](#academic-context)

---

## Overview

Digi Bank is implemented as a set of Spring Boot microservices, each responsible for a clearly defined business capability such as customer management, account management, transaction processing, compliance validation, and notifications. The platform is exposed through an API Gateway and supported by a Discovery Server, a Config Server, dedicated PostgreSQL databases, Docker-based orchestration, and CI/CD automation with GitHub Actions [file:13].

The project remains intentionally educational: its goal is to demonstrate the foundations of distributed system design in a readable, stable, and reproducible way without aiming for full industrial complexity [file:13].

---

## Objectives

The lab is designed to help the student move from a monolithic architecture to a distributed architecture based on autonomous services. Instead of deploying the whole banking system as a single application, the project separates business responsibilities into multiple Spring Boot services that can evolve independently [file:13].

By the end of the lab, the project should demonstrate the ability to:
- analyze business requirements and derive coherent service boundaries [file:13]
- design a microservices architecture for Digi Bank [file:13]
- create several independent Spring Boot services [file:13]
- configure PostgreSQL using a database-per-service approach [file:13]
- implement REST endpoints in each service [file:13]
- enable inter-service communication [file:13]
- introduce an API Gateway as a unified entry point [file:13]
- containerize services with Docker [file:13]
- automate build and validation with GitHub Actions [file:13]
- apply relevant microservices patterns such as Service Discovery, Config Server, Circuit Breaker, Saga, and CQRS [file:13]

---

## Architecture

The target architecture for Digi Bank includes the following components [file:13]:

- **api-gateway**: unified entry point for clients [file:13]
- **customer-service**: customer management [file:13]
- **account-service**: account management [file:13]
- **transaction-service**: financial operations [file:13]
- **compliance-service**: compliance and validation rules [file:13]
- **notification-service**: business and technical notifications [file:13]
- **discovery-server**: service registration and discovery with Eureka [file:13]
- **config-server**: centralized externalized configuration [file:13]
- **one PostgreSQL database per service**: persistence isolation [file:13]
- **Docker Compose**: local orchestration of the full ecosystem [file:13]
- **GitHub Actions**: automated build and validation pipeline [file:13]

The runtime flow is simple and readable: the client calls the API Gateway, the gateway routes the request to the appropriate service, each service uses its own database, and critical services communicate explicitly with each other when business coordination is required [file:13].

---

## Mermaid Diagram

```mermaid
flowchart TD
    Client[Client / Postman / Frontend] --> Gateway[API Gateway :8080]

    Gateway --> CustomerService[Customer Service :8081]
    Gateway --> AccountService[Account Service :8082]
    Gateway --> TransactionService[Transaction Service :8083]
    Gateway --> ComplianceService[Compliance Service :8084]
    Gateway --> NotificationService[Notification Service :8085]

    CustomerService --> CustomerDB[(Customer DB)]
    AccountService --> AccountDB[(Account DB)]
    TransactionService --> TransactionDB[(Transaction DB)]
    ComplianceService --> ComplianceDB[(Compliance DB)]
    NotificationService --> NotificationDB[(Notification DB)]

    CustomerService --> Eureka[Discovery Server :8761]
    AccountService --> Eureka
    TransactionService --> Eureka
    ComplianceService --> Eureka
    NotificationService --> Eureka
    Gateway --> Eureka

    CustomerService --> ConfigServer[Config Server :8888]
    AccountService --> ConfigServer
    TransactionService --> ConfigServer
    ComplianceService --> ConfigServer
    NotificationService --> ConfigServer
    Gateway --> ConfigServer

    TransactionService --> ComplianceService
    TransactionService --> NotificationService
```

This diagram reflects the distributed organization expected in the lab: a client never talks directly to internal services, the gateway centralizes access, each service owns its own persistence, and platform services such as discovery and configuration support the overall ecosystem [file:13].

---

## Services

### Customer Service

The `customer-service` manages customer identity and contact information. It supports customer creation, retrieval, update, and listing operations [file:13].

### Account Service

The `account-service` manages bank accounts, including account number generation, account balance, and the association between an account and a customer [file:13].

### Transaction Service

The `transaction-service` is the operational core of Digi Bank. It records financial operations and interacts with the `compliance-service` before validating transactions [file:13].

### Compliance Service

The `compliance-service` enforces validation rules to determine whether a transaction amount is acceptable according to business or regulatory constraints [file:13].

### Notification Service

The `notification-service` demonstrates a secondary processing flow triggered after a business event, such as notifying a user after an accepted or rejected transaction [file:13].

### API Gateway

The `api-gateway` provides a single entry point for all external requests. It simplifies testing, centralizes cross-cutting concerns, and hides the internal service topology from consumers [file:13].

### Discovery Server

The `discovery-server` uses Eureka to allow services to register dynamically and discover one another by logical name instead of hard-coded addresses [file:13].

### Config Server

The `config-server` externalizes shared configuration and reduces duplication across services, improving maintainability in a distributed environment [file:13].

---

## Technology Stack

The project uses the following technologies and tools [file:13]:

- Java 17 [file:13]
- Spring Boot [file:13]
- Spring Cloud [file:13]
- Spring Data JPA [file:13]
- PostgreSQL [file:13]
- Maven [file:13]
- Docker [file:13]
- Docker Compose [file:13]
- GitHub Actions [file:13]
- JUnit 5 [file:13]
- Cucumber for integration testing [file:13]
- Postman / Newman for API testing [file:13]

---

## Repository Structure

The project follows a **mono-repository** structure, which is recommended in the lab because it makes the architecture easier to read, easier to correct, and easier to orchestrate progressively during development [file:13].

```text
digibank-microservices/
├── api-gateway/
├── discovery-server/
├── config-server/
├── customer-service/
├── account-service/
├── transaction-service/
├── compliance-service/
├── notification-service/
├── docker-compose.yml
├── .github/
│   └── workflows/
│       └── ci.yml
└── pom.xml
```

---

## Prerequisites

Before running the project, make sure the following tools are installed and available in your environment [file:13]:

- JDK 17 or a compatible version [file:13]
- Apache Maven [file:13]
- PostgreSQL [file:13]
- Docker [file:13]
- Docker Compose [file:13]
- Git [file:13]
- A working GitHub account [file:13]
- IntelliJ IDEA or Visual Studio Code [file:13]
- Postman for testing the APIs [file:13]

Recommended verification commands [file:13]:

```bash
java -version
mvn -version
git --version
psql --version
docker --version
docker compose version
```

The local environment should be able to compile Spring Boot applications, run multiple services in parallel, start PostgreSQL databases, build Docker images, and test REST endpoints locally [file:13].

---

## Database Strategy

The project adopts a **database-per-service** model, which is a central microservices principle in this lab. Each service owns its persistence layer to avoid direct schema sharing and strong coupling through the database [file:13].

Example databases used in the project [file:13]:
- `digibankcustomerdb` [file:13]
- `digibankaccountdb` [file:13]
- `digibanktransactiondb` [file:13]
- `digibankcompliancedb` [file:13]
- `digibanknotificationdb` [file:13]

Example SQL initialization:

```sql
CREATE DATABASE digibankcustomerdb;
CREATE DATABASE digibankaccountdb;
CREATE DATABASE digibanktransactiondb;
CREATE DATABASE digibankcompliancedb;
CREATE DATABASE digibanknotificationdb;
```

---

## Build and Run

### 1. Clone the repository

```bash
git clone <repository-url>
cd digibank-microservices
```

### 2. Build the full project

```bash
mvn clean install
```

This command compiles all Maven modules and runs the configured test suite [file:13].

### 3. Start the full platform with Docker Compose

```bash
docker compose up --build
```

This command starts the infrastructure services, databases, and business services in a reproducible local environment [file:13].

---

## Ports

The main service ports described in the lab are the following [file:13]:

| Component | Port |
|---|---:|
| API Gateway | 8080 [file:13] |
| Customer Service | 8081 [file:13] |
| Account Service | 8082 [file:13] |
| Transaction Service | 8083 [file:13] |
| Compliance Service | 8084 [file:13] |
| Notification Service | 8085 [file:13] |
| Discovery Server | 8761 [file:13] |
| Config Server | 8888 [file:13] |

The Docker Compose example in the lab also maps PostgreSQL instances separately so each service can use its own dedicated database container [file:13].

---

## API Examples

Representative endpoints described in the lab include [file:13]:

### Customer Service
- `POST /api/customers`
- `GET /api/customers`
- `GET /api/customers/{id}`
- `PUT /api/customers/{id}`

### Account Service
- `POST /api/accounts`
- `GET /api/accounts`
- `GET /api/accounts/{id}`

### Transaction Service
- `POST /api/transactions`
- `GET /api/transactions`

### Compliance Service
- `GET /api/compliance/validate/{amount}`

### Notification Service
- `POST /api/notifications`

When the gateway is correctly configured, these endpoints can be accessed through `http://localhost:8080` without exposing internal service ports to the client directly [file:13].

---

## Postman Testing

Postman is explicitly recommended in the lab as part of the development and verification workflow for the REST APIs [file:13].

### Suggested setup

1. Start the full platform with Docker Compose.
2. Make sure the API Gateway is reachable on `http://localhost:8080`.
3. Open Postman and create a collection named **Digi Bank Microservices**.
4. Add requests for each exposed endpoint routed by the gateway.
5. Group requests by domain: Customers, Accounts, Transactions, Compliance, Notifications.

### Recommended base URL

```text
http://localhost:8080
```

### Example requests

#### Create a customer

**POST** `http://localhost:8080/api/customers`

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

#### List customers

**GET** `http://localhost:8080/api/customers`

#### Create an account

**POST** `http://localhost:8080/api/accounts`

```json
{
  "balance": 1000.0,
  "customerId": 1
}
```

#### Validate a compliant transaction amount

**GET** `http://localhost:8080/api/compliance/validate/5000`

#### Create a transaction

**POST** `http://localhost:8080/api/transactions`

```json
{
  "type": "TRANSFER",
  "amount": 500.0,
  "accountId": 1
}
```

#### Send a notification

**POST** `http://localhost:8080/api/notifications`

```json
{
  "recipient": "john.doe@example.com",
  "message": "Your transaction has been processed."
}
```

### Newman

The lab also recommends Newman for API test automation, especially for validating endpoints and routes from the command line or in CI workflows [file:13].

Example command:

```bash
newman run DigiBank.postman_collection.json
```

---

## Testing Strategy

Testing is critical in Digi Bank because a microservices architecture introduces multiple failure points across services, databases, and service-to-service communication paths [file:13].

The testing strategy includes:
- **unit tests** for isolated business logic such as compliance rules [file:13]
- **integration tests** for persistence and service interactions [file:13]
- **API tests** for validating endpoints and gateway routes [file:13]

Example command:

```bash
mvn test
```

The lab specifically highlights Cucumber for integration testing and Newman for REST endpoint validation [file:13].

---

## Dockerization and Orchestration

Docker is used to isolate each service in its own containerized runtime environment. This makes execution more reproducible and avoids environment-specific issues when running several services and databases together [file:13].

The project includes:
- one `Dockerfile` per Spring Boot service [file:13]
- one `docker-compose.yml` file for full orchestration [file:13]

Docker Compose is especially important in this project because the value of the system appears only when all services can start together and communicate correctly in the same environment [file:13].

---

## CI/CD

GitHub Actions is used to automate build and validation steps whenever code changes are pushed or submitted through pull requests [file:13].

The lab expects the pipeline to:
- compile the project [file:13]
- run tests [file:13]
- produce build artifacts [file:13]
- detect regressions early [file:13]
- prepare future Docker image automation if needed [file:13]

Example workflow:

```yaml
name: DigiBank CI

on:
  push:
    branches: [ "main" ]
  pull_request:

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 17

      - name: Cache Maven packages
        uses: actions/cache@v4
        with:
          path: ~/.m2/repository
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: ${{ runner.os }}-m2

      - name: Build and test
        run: mvn clean test
```

---

## Technical Decisions

This section explains the main technical choices made in the project and why they fit the Digi Bank lab context [file:13].

### 1. Mono-repository organization

A mono-repo was chosen because the lab recommends centralizing services, orchestration files, scripts, and shared configuration in a single repository. This makes the project easier to understand, easier to grade, and easier to run as a complete platform [file:13].

### 2. Database per service

Each service owns its own PostgreSQL database. This follows a core microservices principle and avoids the strong coupling that would result from sharing a single schema across multiple business services [file:13].

### 3. API Gateway as a single entry point

The gateway simplifies client access and hides internal service ports and URLs. This also prepares the platform for centralized authentication, monitoring, logging, and traffic management [file:13].

### 4. Eureka for Service Discovery

Instead of relying entirely on hard-coded URLs, services can register dynamically and discover one another by name. This is better suited to a distributed, Dockerized environment where endpoints may evolve over time [file:13].

### 5. Config Server for centralized configuration

Centralizing configuration reduces duplication and supports cleaner management of ports, profiles, shared conventions, and service parameters in a distributed system [file:13].

### 6. Circuit Breaker for resilience

A failure in one service must not bring down the entire platform. The Circuit Breaker pattern protects inter-service calls and allows the system to degrade more gracefully when a dependency becomes unavailable [file:13].

### 7. Saga for distributed business consistency

Banking flows often involve several services. Saga is used because a distributed operation may partially succeed, and compensating actions are needed to keep the system in a coherent state [file:13].

### 8. CQRS for better separation of concerns

Separating command and query responsibilities keeps write models simple while enabling more flexible and optimized read models, especially for transaction history and reporting views [file:13].

### 9. Docker Compose for local orchestration

Starting each service and each database manually would be tedious and error-prone. Docker Compose provides a practical way to run the whole Digi Bank ecosystem with one command in a reproducible environment [file:13].

### 10. GitHub Actions for continuous validation

In a microservices system, automation becomes essential because a regression in one service or one shared configuration can affect the whole system. CI helps catch those issues early [file:13].

---

## Implementation Roadmap

The lab recommends building Digi Bank progressively from the most basic components to the more advanced distributed patterns [file:13]:

1. create the core business services [file:13]
2. configure PostgreSQL databases per service [file:13]
3. introduce Eureka for service discovery [file:13]
4. add the Config Server [file:13]
5. implement the API Gateway [file:13]
6. secure inter-service communication with Circuit Breaker [file:13]
7. model a distributed transfer process with Saga [file:13]
8. separate reads and writes with CQRS [file:13]
9. containerize services with Docker [file:13]
10. orchestrate the platform with Docker Compose [file:13]
11. automate build and validation with GitHub Actions [file:13]
12. test endpoints and business behavior until the whole platform becomes stable [file:13]

---

## Deliverables

The expected submission includes a complete and reproducible microservices project with the following elements [file:13]:

- the full `digibank-microservices` repository [file:13]
- all Maven projects [file:13]
- every `pom.xml` file [file:13]
- `application.yml` or `application.properties` files [file:13]
- Dockerfiles for each component [file:13]
- the `docker-compose.yml` file [file:13]
- GitHub Actions workflows [file:13]
- Java classes for business services [file:13]
- REST controllers [file:13]
- repositories, services, clients, and configuration classes [file:13]
- unit and integration tests [file:13]
- SQL scripts for database creation [file:13]
- a short note explaining the implementation order and technical choices [file:13]

---

## Expected Outcome

At the end of the lab, Digi Bank should provide a first stable, executable version of a distributed banking platform based on a coherent microservices architecture. It should expose a unified entry point, autonomous business services, dynamic service discovery, centralized configuration, resilience mechanisms, Docker-based orchestration, and a documented, testable, and automatable delivery process [file:13].

---

## Academic Context

This project was developed as part of the **UCC122-1 Cloud Architecture** course in the **Professional Bachelor's Degree in Cloud Computing** at **Université des Montagnes** [file:13].

---

## Note

This project is educational in nature. Its purpose is to demonstrate the architectural foundations of microservices applied to a distributed banking use case, not to replicate the full complexity of a production-grade banking platform [file:13].

Sources
[1] Monolith-to-Microservices_-Evolutionary-Patterns-to-Transform-Your-Monolith.pdf https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/23401853/8b17445e-9fa6-4d4f-873d-3096ea15f434/Monolith-to-Microservices_-Evolutionary-Patterns-to-Transform-Your-Monolith.pdf?AWSAccessKeyId=ASIA2F3EMEYE7LL2TDNB&Signature=B3TePZ4mJtRIq4qUxFZ91XTMswM%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEGIaCXVzLWVhc3QtMSJIMEYCIQD6e6GtoCdpkjA%2BQe4phx%2BIv8dqVHiMsQYPLfZFWNmksgIhAJv%2B11WNVShA9YBwdmtwyFfMCdEGF%2BKdcYkLcsSoyG2VKvMECCsQARoMNjk5NzUzMzA5NzA1IgyTDDkw8FgIYwbV8egq0ATiAtxJ5W%2BcNPrdrml1nuE6HKs03IQuf0r124SlWWYgNMtIHyOyzGfUPFWBabnsBTNVpuwxWZ6Ppi%2BC1Pvj8F8R8911oLUpbZlUHsms1x9xiJ8AX61AKurOznLwQXOEYDr0dEMZE7sxDgRC9%2B3nek0WHor%2FG7w5X14nPZsFemgMkt7SaE8GjYIxQ6%2Bo4afqcAT3lCJL2CySu8Y%2BIviEH4AI8ehxvYVC%2F95nTI9EHXRRQwl2rBPol2MHqOylP27yPYlQ1xqLkhyd%2FXdAlaUA%2BZyjjkUUx66UTFjXbAyYxI3%2FHw3fBj2B2YRqx9sX90KfYYUMhP4xhq9w63ptTNHaO8prUhqZ3%2BfG4o%2FquyUc3JWH3x%2FuwAoH%2Beszo6A0ZlDQ1ATNpk%2BlK%2FUEdNiVFHxI0RRwDTyYyl%2FShyiJbwH5NUslapGBYqhrPOIHIN9d3ffVGA%2BcF3gRsGPAhRZ%2Bi5y8AEgC4BS9f7GLO1IMbodnrfnuXh1LpxC8o8%2BGYJtemKWrfWcVBE2PvYXe143z86jP1JXSRBBS3Kw82SfVJk1Rg6VJKtujVa6fHF3Xne4CEQsFA5qWI0EN0gLKs0npupdedjSGAL3NfvbY7ZgTbufBA9dEa7acLsMYV%2FMCQvvJwjZet%2Bi7YrsFLsEjLiiZ%2FhOf5pyzsi%2FSraAc2U8rFjZ7Bhqhcbs6AGt7WNtKW5DJnsmsiwxSacixAFadefgu4Ggh1686EGLn3W3BGTgOrgvxCxVc%2F3Lom3q7ZVg1s5ljj%2BjOeWnHDDPVfCqDZU%2FTh%2FwwQDb0MOjm7NEGOpcBgNesLDIHSavt11YU7P%2BzreE8F9U2H390ycnaYDdGHCnyYLtCRF4I5mNd5y9iW7NXEOooOlgmKD6dB%2FBqvt4%2FggqOW%2FwdbsSsZ9CFx0agn7CxEr0R5gZqItqWuLeXjVx%2FYLkRp2W02HvkuOS08%2FEXyxFs%2FExEyc%2FYm3ZTZKGH6cWc%2FJ894pvm8znYUF6hCQwWsMYnq0sa8A%3D%3D&Expires=1782268219