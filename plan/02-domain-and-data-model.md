## Domain & Data Model Plan

### Core Domain Concepts
- **User** (future phase for auth, may be simplified or omitted in MVP):
  - Represents a student using the app.
  - Owns a collection of applications.
- **Application**:
  - Represents a single internship or job application a student is tracking.
  - Holds details like company, role, deadlines, status, and notes.
- **Company** (optional, can be embedded in `Application` for MVP):
  - Represents the organization offering the role.
- **Status History** (phase 2):
  - Tracks transitions over time (applied → interview → offer, etc.).

### MVP Data Model (Single Service, Single DB)

#### Application Entity (Core)
- **Fields**:
  - `id` (UUID or Long): primary key.
  - `userId` (String/UUID): ID of the user who owns this application (if auth is present; otherwise omitted or placeholder).
  - `companyName` (String, required).
  - `roleTitle` (String, required).
  - `applicationType` (enum: INTERNSHIP, FULL_TIME, OTHER).
  - `status` (enum: PLANNING, APPLIED, INTERVIEW, OFFER, REJECTED, WITHDRAWN).
  - `applicationDeadline` (LocalDate, optional but recommended).
  - `appliedDate` (LocalDate, optional).
  - `location` (String, optional).
  - `jobPostingUrl` (String, optional).
  - `notes` (Text, optional).
  - `createdAt` (timestamp).
  - `updatedAt` (timestamp).

#### Status Enum
- `PLANNING` – considering applying or preparing materials.
- `APPLIED` – application submitted.
- `INTERVIEW` – any stage of interviews (phone screen, onsite, etc.).
- `OFFER` – offer extended.
- `REJECTED` – no longer in process, rejected or no response.
- `WITHDRAWN` – user chose to withdraw.

### Relationships (Future Expansion)
- **User ↔ Application**:
  - One user to many applications.
  - `Application.userId` foreign key to `User.id`.
- **Application ↔ StatusHistory** (phase 2):
  - One application to many status history records.
  - Each history row: `fromStatus`, `toStatus`, `changedAt`, optional `notes`.

### Database Tables (MVP)
- `applications`:
  - Columns corresponding to fields described above.
  - Indexes:
    - `idx_applications_user_id`
    - `idx_applications_status`
    - `idx_applications_deadline`
    - (Optional) `idx_applications_company_name`.

### Validation Rules (High-Level)
- `companyName` and `roleTitle`: required, reasonable length.
- `applicationDeadline`: must be in the future or present for new entries (optional rule).
- `status`:
  - Default to `PLANNING` or `APPLIED` depending on how the user creates the record.


