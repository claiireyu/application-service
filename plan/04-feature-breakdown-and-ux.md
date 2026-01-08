## Feature Breakdown & UX Plan

### Core Features Mapped to UX

- **Add and manage applications**
  - UI form to create a new application:
    - Fields: company, role, deadline, type, status, location, URL, notes.
    - Client-side validation (required fields, date formats).
  - Edit form (re-using the same component for create/update).

- **Track application status**
  - Status rendered as a pill or badge (color-coded).
  - Quick status change control (e.g., dropdown or inline editable field).
  - Optional: simple status transition helper (e.g., common transitions surfaced as quick actions).

- **View all applications in a single dashboard**
  - Main dashboard page:
    - Table or card-based list of all applications.
    - Key columns: company, role, status, deadline, last updated, quick actions.
  - Empty state guidance: show call-to-action to add the first application.

- **Filter and sort applications**
  - Top filter bar:
    - Status filter: multi-select (e.g., All, Applied, Interview, Offer, Rejected).
    - Search by company name / role.
    - Date range filter for deadlines.
  - Sort controls:
    - By deadline, created date, company name, status.
    - Asc/desc toggle.

- **Quickly update application details**
  - Inline actions:
    - Change status directly from the dashboard row.
    - Open detail/edit modal or dedicated page.
  - Notes:
    - Editable notes area on application detail view.

### Initial Screens / Pages
- **1. Dashboard Page**
  - Table of applications.
  - Filters and sorting controls.
  - “Add Application” button.

- **2. Create/Edit Application Page (or Modal)**
  - Form for entering application fields.
  - Primary action: Save.
  - Secondary action: Cancel.

- **3. Application Detail View (optional for MVP)**
  - Expanded view with full notes and status history (in later versions).

### MVP UX Priorities
- Fast add/edit flows with minimal required fields.
- Clear indication of status and deadlines to support quick scanning.
- Simple but responsive layout (desktop-first, mobile-friendly later).


