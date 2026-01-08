## Implementation Milestones – Internship Tracker App (Spring Boot)

### Milestone 1 – Project Bootstrap
- **Backend setup**
  - Initialize Spring Boot project (Maven or Gradle).
  - Add dependencies: Web, Data JPA, Validation, PostgreSQL driver, Lombok (optional), springdoc-openapi.
  - Configure application properties (DB connection, port, basic profiles).

### Milestone 2 – Domain & Persistence Layer
- Create `Application` entity with fields defined in the domain model.
- Define `ApplicationStatus` and `ApplicationType` enums.
- Create JPA repository for `Application`.
- Set up schema generation or Flyway/Liquibase migrations.

### Milestone 3 – Service & Business Logic
- Implement `ApplicationService` with methods:
  - `createApplication`, `getApplicationById`, `getApplications`, `updateApplication`, `partialUpdateApplication`, `deleteApplication`.
- Add domain-level validations and basic business rules (e.g., default statuses).

### Milestone 4 – REST Controllers & DTOs
- Create request/response DTOs for applications.
- Implement REST controllers matching the API design:
  - `POST /api/v1/applications`
  - `GET /api/v1/applications/{id}`
  - `GET /api/v1/applications` (with filtering & sorting).
  - `PUT /api/v1/applications/{id}`
  - `PATCH /api/v1/applications/{id}`
  - `DELETE /api/v1/applications/{id}`
- Implement global exception handling and validation error responses.

### Milestone 5 – Dashboard & UX (Client)
- Choose frontend approach (e.g., simple React SPA, Thymeleaf, or another UI).
- Implement dashboard page:
  - Fetch list of applications from `/api/v1/applications`.
  - Display in table with basic filters and sorts wired to API.
- Implement create/edit application flows hooked into backend.

### Milestone 6 – Testing & Quality
- Unit tests for service layer.
- Integration tests for repositories and REST endpoints.
- Basic performance sanity checks 

### Milestone 7 – Deployment & Ops (Optional Early or Later)
- Containerize the service using Docker.
- Prepare environment configurations for dev/test/prod.
- Set up simple CI pipeline (build, test, maybe docker build).


