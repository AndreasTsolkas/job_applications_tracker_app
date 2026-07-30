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

---

# Database

The application uses PostgreSQL.

The database model currently includes:

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

Database initialization is handled through SQL migration scripts.

### create_db.sql

Creates:

* Database tables
* Primary keys
* Foreign keys
* Constraints
* Indexes
* Relationships between entities

### seed_data.sql

Provides initial data for:

* Lookup tables
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

The project structure also contains placeholders for future layers:

```text
Service
  |
Controller
```

These layers will contain business logic and REST API endpoints in the next development stages.

---

# Current Backend Implementation

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

* Project setup completed
* Development environment configured

Planned frontend development:

* Authentication screens
* Dashboard
* Job application management interface
* Company and recruiter views
* CV and cover letter management
* Interview tracking interface
* Backend API integration

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

Configure PostgreSQL connection in:

```text
backend/src/main/resources/application.properties
```

Execute the migration scripts:

```sql
create_db.sql
```

Then populate initial data:

```sql
seed_data.sql
```

---

# Development Roadmap

Upcoming development stages:

## Backend

* Service layer implementation
* Business logic implementation
* REST API controllers
* Request/Response validation
* Exception handling
* Authentication and authorization

## Frontend

* UI implementation
* API integration
* User dashboard
* Application management screens
* Search and filtering functionality

## Deployment

* Docker support
* Production configuration
* Deployment setup

---

# License

This project is for educational and development purposes.
