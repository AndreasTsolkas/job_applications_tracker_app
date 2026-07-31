# Job Applications Tracker

A full-stack application for managing job applications, companies, job postings, CVs, cover letters, recruiters, and interview progress.

The goal of this project is to provide a complete system for tracking the job application process, from discovering job opportunities to managing applications, interviews, and application status history.

The project is currently under active development.

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
│   │   └── main
│   │       ├── java
│   │       │   └── com.example.jobtracker
│   │       │       │
│   │       │       ├── entity
│   │       │       ├── DTO
│   │       │       ├── mapper
│   │       │       ├── repository
│   │       │       ├── service
│   │       │       └── controller
│   │       │
│   │       └── resources
│   │           └── application.properties
│   │
│   ├── pom.xml
│   └── mvnw
│
├── frontend
│   ├── src
│   ├── public
│   ├── package.json
│   ├── tsconfig.json
│   └── vite.config.ts
│
├── database
│   ├── create_db.sql
│   └── seed_data.sql
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

Backend testing will be implemented gradually during development.

Planned unit tests include:

* Mapper tests
* Service layer tests
* Repository tests
* Controller tests

Future testing tools:

* JUnit
* Mockito
* MockMvc

The goal is to ensure reliability, maintainability, and confidence as new features are added.

---

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
create_db.sql
```

Then populate initial data:

```sql
seed_data.sql
```

---

# Development Roadmap

## Backend

Upcoming tasks:

* Improve service business logic
* Add validation using Jakarta Validation
* Add global exception handling
* Improve API error responses
* Implement authentication and authorization
* Add advanced application workflow handling
* Improve API documentation

## Testing

Upcoming testing tasks:

* Gradual creation of backend unit tests
* Mapper testing
* Service testing with Mockito
* Repository testing
* Controller testing with MockMvc

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
