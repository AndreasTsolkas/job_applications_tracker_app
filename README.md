# Job Applications Tracker

A full-stack application for managing job applications, companies, job postings, CVs, cover letters, recruiters, and interview progress.

The goal of this project is to provide a complete system for tracking the job application process, from discovering job opportunities to managing applications, interviews, and application status history.

The project is currently under active development.

---

# Features

## Core Features

* Account
  * User registration / profile
  * Authentication & login
* Reference data
  * Manage sectors (industries)
  * Manage companies
  * Manage recruiters (linked to a company)
  * Manage job roles
  * Manage employment types (full-time, contract, etc.)
* Job posting tracking
  * Job postings are entered manually by the user — there is no automated import from external job boards
  * A job posting is added as its own standalone action, independent of applying to it
  * Add / view / delete job postings (title, company, role, employment type)
  * Browse postings by company or by role
* Documents
  * Manage CVs (upload / name, mark one active per user)
  * Manage cover letters (upload / name / content, linked to user)
* Application tracking
  * Applying to a job posting is a separate step from creating the posting itself — an application always references an existing job posting
  * Create an application for a job posting (attach a CV, a cover letter, optionally a recruiter)
  * View all applications, filter by user / status / job posting
  * Update application status (Applied → Interviewing → Offered → Rejected, etc.)
  * Automatic status history logging whenever status changes
  * Delete / withdraw an application
  * Add notes to an application
* Interview tracking
  * Schedule an interview for an application (type: phone / technical / onsite, date/time)
  * Record interview result (passed / failed / pending)
  * View interview history per application

## Planned Features

* Dashboard & insights
  * Overview of active applications by status
  * Upcoming interviews
  * Basic stats (applications sent, interview conversion rate, etc.)
* Search & filtering
  * Search / filter applications, postings, companies

## Out of Scope

No integrations with external systems or APIs (e.g. calendar sync, email parsing, cloud file storage, job board APIs) are planned at this time. The application is intended to remain self-contained.

---

# Workflows

## Account

1. User registers (name, email, etc.) → account created.
2. User logs in.
3. User views/updates their profile.

## Reference Data

*(sectors, companies, recruiters, job roles, employment types)*

1. User adds a sector (name) — typically created ad hoc, when it's needed to categorize a company rather than upfront.
2. User adds a company, optionally linking it to a sector.
3. User adds a recruiter, linking them to a company.
4. User adds a job role (title/category), used later when creating a job posting.
5. User adds an employment type, if not already seeded (Full-time, Contract, etc.).
6. User views/deletes any of these records as needed.

## Job Postings

1. User selects (or creates on the fly, via Reference Data) the company, job role, and employment type for the posting.
2. User creates the job posting (title, company, role, employment type).
3. User browses postings, filterable by company or by role.
4. User deletes a posting they no longer want to track (e.g. position filled, no longer interested).

## Documents

1. User uploads a CV (name, file) → CV created.
2. User marks a CV as the active one (others become inactive).
3. User uploads a cover letter (name, content/file).
4. User views/deletes CVs and cover letters.
5. When creating an application, the user picks from these existing CVs/cover letters (feeds into the Applications workflow).

## Applications

1. User browses their tracked job postings and picks one to apply to.
2. User selects a CV from their existing CVs (or uploads a new one first, via the Documents workflow).
3. User optionally selects a cover letter and/or a recruiter.
4. User submits the application (applied date, notes) → application is created with an initial status (e.g. "Applied").
5. A status history entry is automatically recorded for that initial status.
6. User views their applications, filterable by status / job posting.
7. As the process moves forward, user updates the application's status (e.g. "Interviewing" → "Offered"/"Rejected") → each change automatically appends a new status history entry.
8. User can add/edit notes on the application at any point.
9. User can withdraw/delete the application.

## Interviews

1. User selects an application to schedule an interview for.
2. User picks an interview type (phone/technical/onsite) — or creates a new one via Reference Data if it doesn't exist.
3. User sets the scheduled date/time and adds notes → interview is created, linked to the application.
4. After the interview happens, user records the result (passed/failed/pending), picking from existing interview results or creating a new one.
5. User views the interview history for an application.
6. User deletes an interview record if needed.

## Dashboard

*(planned — not yet detailed)*

## Search

*(planned — not yet detailed)*

---

# Technologies

## Frontend

* React
* TypeScript
* Vite

## Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* Lombok
* JUnit
* Mockito
* MockMvc

## Database

* PostgreSQL

---

# Project Structure (Currently)

The current repository structure is:

```text
job_applications_tracker_app
│
├── backend
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.example.jobtracker
│   │   │   │       │
│   │   │   │       ├── entity
│   │   │   │       ├── DTO
│   │   │   │       ├── mapper
│   │   │   │       ├── repository
│   │   │   │       ├── service
│   │   │   │       └── controller
│   │   │   │
│   │   │   └── resources
│   │   │       ├── application.properties
│   │   │       └── application-example.properties
│   │   │
│   │   └── test
│   │       └── java
│   │           └── com.example.jobtracker
│   │               │
│   │               ├── mapper
│   │               ├── service
│   │               └── controller
│   │
│   ├── compose.yaml
│   ├── pom.xml
│   └── mvnw
│
├── frontend
│   ├── src
│   ├── public
│   ├── index.html
│   ├── package.json
│   ├── tsconfig.json
│   └── vite.config.ts
│
├── database
│   ├── create_db.sql.txt
│   └── seed_data.sql.txt
│
└── README.md
```

The project is currently in the backend REST API foundation stage.

---

# Database

The application uses PostgreSQL.

The database model currently contains:

* Users
* Sectors
* Companies
* Recruiters
* Job roles
* Employment types
* Job postings
* CVs
* Cover letters
* Applications
* Application statuses
* Application status history
* Interviews
* Interview types
* Interview results

## Database Migration Files

Database initialization is handled through SQL scripts.

### create_db.sql

Responsible for creating:

* Database tables
* Primary keys
* Foreign keys
* Constraints
* Indexes
* Entity relationships

### seed_data.sql

Responsible for inserting initial data:

* Lookup values
* Application statuses
* Employment types
* Interview types
* Interview results
* Development/testing data

---

# Backend Architecture (Currently)

The backend follows a layered Spring Boot architecture.

Current architecture:

```text
Entity
  |
Repository
  |
DTO
  |
Mapper
  |
Service
  |
Controller
  |
REST API
```

The application uses DTOs to separate the persistence layer from API communication.

Dedicated mapper classes are responsible for converting:

```
Entity <-> DTO
```

Services handle business logic and repository communication.

Controllers expose REST endpoints using DTO request and response objects.

---

# Backend Implementation Status

## Completed

The following backend components have been implemented:

* Spring Boot project setup
* PostgreSQL configuration
* JPA/Hibernate entity mapping
* Database model implementation
* Repository layer
* DTO layer
* Mapper layer
* Service layer
* REST Controller layer
* Initial REST API endpoints

---

# Implemented Entities

The following JPA entities have been created:

* AppUser
* Sector
* Company
* Recruiter
* JobRole
* EmploymentType
* JobPosting
* CV
* CoverLetter
* ApplicationStatus
* Application
* ApplicationStatusHistory
* InterviewType
* InterviewResult
* Interview

---

# Implemented DTOs

DTO classes have been created for:

* AppUser
* Sector
* Company
* Recruiter
* JobRole
* EmploymentType
* JobPosting
* CV
* CoverLetter
* ApplicationStatus
* Application
* ApplicationStatusHistory
* InterviewType
* InterviewResult
* Interview

---

# Implemented Mappers

The mapper layer currently contains:

* SectorMapper
* CompanyMapper
* RecruiterMapper
* JobRoleMapper
* EmploymentTypeMapper
* JobPostingMapper
* CVMapper
* CoverLetterMapper
* ApplicationStatusMapper
* ApplicationMapper
* ApplicationStatusHistoryMapper
* InterviewTypeMapper
* InterviewResultMapper
* InterviewMapper

---

# Implemented Services

All main services have been updated to work with DTOs and mapper classes.

Implemented services:

* AppUserService
* SectorService
* CompanyService
* RecruiterService
* JobRoleService
* EmploymentTypeService
* JobPostingService
* CVService
* CoverLetterService
* ApplicationStatusService
* ApplicationService
* ApplicationStatusHistoryService
* InterviewTypeService
* InterviewResultService
* InterviewService

Current service responsibilities:

* Communicate with repositories
* Convert entities using mappers
* Return DTO responses
* Accept DTO requests
* Prepare business logic layer

---

# Implemented Controllers

REST controllers have been created for:

* AppUserController
* SectorController
* CompanyController
* RecruiterController
* JobRoleController
* EmploymentTypeController
* JobPostingController
* CVController
* CoverLetterController
* ApplicationStatusController
* ApplicationController
* ApplicationStatusHistoryController
* InterviewTypeController
* InterviewResultController
* InterviewController

Current API functionality includes:

* Fetching all resources
* Fetching resources by ID
* Fetching resources by related entities
* Creating resources
* Deleting resources

---

# Implemented Repositories

Repository interfaces currently include:

* AppUserRepository
* SectorRepository
* CompanyRepository
* RecruiterRepository
* JobRoleRepository
* EmploymentTypeRepository
* JobPostingRepository
* CVRepository
* CoverLetterRepository
* ApplicationStatusRepository
* ApplicationRepository
* ApplicationStatusHistoryRepository
* InterviewTypeRepository
* InterviewResultRepository
* InterviewRepository

---

# Frontend (Currently)

The frontend application is initialized using:

* React
* TypeScript
* Vite

Current frontend status:

* React project setup completed
* TypeScript configuration completed
* Vite development environment configured

Future frontend development includes:

* UI implementation
* Backend API integration
* Authentication screens
* Dashboard
* Job application management interface
* Company and recruiter views
* CV and cover letter management
* Interview tracking interface

---

# Testing

Backend testing has started and is being implemented gradually during development.

Implemented testing:

* Mapper unit testing
* Service unit testing
* Controller unit testing
* JUnit 5 configuration
* Mockito integration
* MockMvc controller testing
* Spring Boot MVC slice testing using @WebMvcTest
* Service mocking using @MockitoBean

Completed controller tests:

* AppUserControllerTest
* SectorControllerTest
* CompanyControllerTest
* EmploymentTypeControllerTest
* JobRoleControllerTest
* JobPostingControllerTest
* ApplicationStatusControllerTest
* InterviewTypeControllerTest
* InterviewResultControllerTest
* RecruiterControllerTest
* CVControllerTest
* CoverLetterControllerTest
* ApplicationControllerTest
* ApplicationStatusHistoryControllerTest
* InterviewControllerTest

All controllers now have unit test coverage.

Current controller tests verify:

* HTTP status codes
* JSON response structure
* Request body serialization
* Service interaction
* Endpoint behavior

The testing approach follows the principle:

Controllers are tested independently by mocking their service dependencies instead of accessing the database.

Future testing includes:

* Integration tests


# Running the Backend

Navigate to the backend directory:

```bash
cd backend
```

Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

---

# Running the Frontend

Navigate to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

---

# Database Setup

Configure PostgreSQL connection inside:

```text
backend/src/main/resources/application.properties
```

Execute:

```sql
create_db.sql.txt
```

Then populate initial data:

```sql
seed_data.sql.txt
```

---

# Development Roadmap

## Backend

Upcoming tasks:

* Create service business logic
* Add validation using Jakarta Validation
* Add global exception handling
* Improve API error responses
* Implement authentication and authorization
* Add advanced application workflow handling
* Implement dashboard and statistics features
* Add search and filtering capabilities
* Improve API documentation

## Testing

Upcoming testing tasks:

* Start integration testing

## Frontend

Upcoming tasks:

* Build application UI
* Connect frontend with backend APIs
* Implement authentication flow
* Create dashboard
* Add job application management screens
* Add search and filtering features

## Deployment

Future tasks:

* Docker support
* Production configuration
* Deployment setup

---

# License

This project is for educational and development purposes.
