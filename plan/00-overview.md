## Internship Tracker App – High-Level Overview

**Goal**: Build a Spring Boot–based microservice application that helps college students organize and manage internship and job applications in one place, replacing scattered spreadsheets, notes, and emails.

### Target Users
- **College students**: Actively applying to internships and entry-level jobs.
- **Primary needs**:
  - Quickly add new applications (company, role, deadline, notes).
  - Track status over time (applied, interview, offer, rejected, etc.).
  - See everything in a single dashboard.
  - Filter and sort by status, company, or deadline.
  - Update details easily as the process progresses.

### Core Use Cases
- **Create application**: User adds an application with basic fields and optional notes.
- **Update application**: User modifies status, deadline, or notes as they progress.
- **View dashboard**: User sees all applications in a consolidated, sortable, filterable view.
- **Filter & sort**: User views applications by status, company, time horizon, or custom filters.

### Non-Goals (for initial version)
- No complex analytics (conversion rates, funnel metrics) beyond simple counts by status.
- No third-party ATS or email inbox integration in v1.
- No multi-tenant enterprise features (this is student-focused).

### High-Level Architecture Direction
- **Backend**: Spring Boot microservice(s), REST APIs, JWT-based auth (future), JPA/Hibernate for persistence.
- **Database**: Relational DB (e.g., PostgreSQL/MySQL) for strong consistency and flexible querying.
- **Client(s)**:
  - Initially: a simple web UI (SPA or server-rendered) that consumes the REST APIs.
  - Optionally later: a mobile client or React front-end.

### Planned Microservices (Initial Thought)
- **Gateway / API Edge (optional for v1)**:
  - Simple gateway or API layer that fronts all services.
- **User Service (future)**:
  - Handle registration, login, profile, and auth.
- **Application Tracker Service (core)**:
  - Manage internship/job applications, statuses, notes, and querying.

For the first iteration, we can start with a **single Spring Boot service** for the application tracking domain, but design it so it can be split into multiple services later.


