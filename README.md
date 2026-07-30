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

The project is currently in the backend foundation stage.

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

Current implemented layers:

```text
Entity
  |
Repository
  |
DTO
  |
Mapper
```

Entity and API models are separated using DTOs and dedicated mapper classes.

The next backend development stages are:

```text
Mapper integration into Services
            |
      Service Layer
            |
    Controller Layer
            |
        REST API
```

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
* Controller tests after REST API implementation

The goal is to ensure reliability and maintainability as new backend layers are added.

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

* Complete mapper usage inside services
* Implement service layer
* Add business logic
* Create REST controllers
* Implement API endpoints
* Add request/response validation
* Add exception handling
* Implement authentication and authorization

## Testing

Upcoming testing tasks:

* Gradual creation of backend unit tests
* Mapper testing
* Service testing
* Repository testing
* Controller testing

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
