## API Design – Application Tracker Service

### General Conventions
- **Base URL** (MVP, no gateway): `/api/v1`
- **Resource**: `applications`
- **Format**: JSON request/response.
- **Error Handling**: Consistent error body with `code`, `message`, `details`.

### DTOs

#### ApplicationRequest (Create/Update)
- `companyName` (String, required)
- `roleTitle` (String, required)
- `applicationType` (String enum: `INTERNSHIP`, `FULL_TIME`, `OTHER`)
- `status` (String enum: `PLANNING`, `APPLIED`, `INTERVIEW`, `OFFER`, `REJECTED`, `WITHDRAWN`)
- `applicationDeadline` (ISO date string, optional)
- `appliedDate` (ISO date string, optional)
- `location` (String, optional)
- `jobPostingUrl` (String, optional)
- `notes` (String, optional)

#### ApplicationResponse
- All fields from `ApplicationRequest`
- Plus:
  - `id`
  - `createdAt`
  - `updatedAt`

### Endpoints

#### 1. Create Application
- **POST** `/api/v1/applications`
- **Request Body**: `ApplicationRequest`
- **Response**: `201 Created` with `ApplicationResponse`

#### 2. Get Single Application
- **GET** `/api/v1/applications/{id}`
- **Response**: `200 OK` with `ApplicationResponse`
- **Errors**:
  - `404 Not Found` if not owned by user or missing.

#### 3. List Applications (Dashboard View)
- **GET** `/api/v1/applications`
- **Query Params** (all optional):
  - `status` – one or more statuses (e.g., `status=APPLIED&status=INTERVIEW`)
  - `companyName` – partial match filter.
  - `fromDeadline` / `toDeadline` – filter by deadline range.
  - `sortBy` – `deadline`, `createdAt`, `companyName`, `status`.
  - `sortDirection` – `asc` / `desc`.
  - Pagination: `page`, `size`.
- **Response**:
  - Paged list of `ApplicationResponse` (+ page metadata).

#### 4. Update Application (Full Update)
- **PUT** `/api/v1/applications/{id}`
- **Request Body**: `ApplicationRequest`
- **Response**: `200 OK` with updated `ApplicationResponse`

#### 5. Partial Update (Status / Notes)
- **PATCH** `/api/v1/applications/{id}`
- **Request Body**: e.g. `{ "status": "INTERVIEW", "notes": "Phone screen scheduled" }`
- **Response**: `200 OK` with updated `ApplicationResponse`

#### 6. Delete Application
- **DELETE** `/api/v1/applications/{id}`
- **Response**: `204 No Content`

### Future API Extensions
- **Status History**:
  - `GET /api/v1/applications/{id}/history`
- **Reminders / Notifications**:
  - `POST /api/v1/applications/{id}/reminders`


