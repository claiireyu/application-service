## Architecture Plan – Spring Boot Microservice App

### Overall Approach
- **Style**: Modular monolith that can evolve into microservices, implemented with Spring Boot.
- **Domain-first**: Focus on clean domain modeling of internship/job applications.
- **REST APIs**: JSON over HTTP, stateless operations where possible.

### Services (Phase 1 vs Phase 2)
- **Phase 1 (MVP)** – Single service:
  - `application-tracker-service`
    - Manages student-owned applications, statuses, notes, and filters.
    - Exposes REST endpoints to create/read/update/delete applications and query them by filters.
- **Phase 2+ (Microservice Evolution)**:
  - Split responsibilities:
    - `user-service`: user registration, authentication, and profiles.
    - `application-service`: core application tracking logic.

### Tech Stack
- **Backend**:
  - Java 17+ (or latest LTS).
  - Spring Boot (Web, Data JPA, Validation, Actuator).
  - Optional: Spring Security + JWT (for authenticated users).
- **Database**:
  - PostgreSQL (preferred) or MySQL.
  - Hibernate / JPA for ORM.
- **API Documentation**:
  - OpenAPI/Swagger (springdoc-openapi).
- **Build & Packaging**:
  - Maven or Gradle (decide upfront; default to Maven if unsure).
  - Docker for containerization.

### Logical Layers in `application-tracker-service`
- **API Layer (Controller)**:
  - Handles HTTP requests/responses.
  - Validation of input DTOs.
  - Mapping between DTOs and domain models.
- **Service Layer (Business Logic)**:
  - Orchestrates operations like creating an application, updating status, etc.
  - Enforces business rules (e.g., valid status transitions, deadline checks).
- **Persistence Layer (Repository)**:
  - JPA repositories for `Application`, `Company`, etc.
  - Encapsulates all DB access and query logic.

### Key Cross-Cutting Concerns
- **Validation**:
  - Use Bean Validation (JSR 380): `@NotNull`, `@Size`, `@FutureOrPresent` on DTOs.
- **Error Handling**:
  - Global exception handler with meaningful error codes and messages.
- **Logging**:
  - Standardized logging for key events (create/update/delete application, etc.).
- **Security (Later)**:
  - JWT-based authentication.
  - Role-based authorization for user-owned resources.

### High-Level Component Diagram (MVP)
- Client (Web UI)
  - Calls REST APIs.
- `application-tracker-service`
  - Controllers → Services → Repositories → PostgreSQL DB.


