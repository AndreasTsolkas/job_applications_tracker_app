# Job Application Tracker

A full-stack application for managing job applications, companies, job postings, CVs, cover letters, recruiters, and interview progress.

The goal of this project is to provide a complete system for tracking the job application process, from discovering job opportunities to managing applications, interviews, and application status history.

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

# Project Structure

```
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

The database model contains the following entities:

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

The database setup is handled through SQL migration scripts.

### create_db.sql

Responsible for creating:

* Database tables
* Primary keys
* Foreign keys
* Constraints
* Indexes
* Entity relationships

### seed_data.sql

Responsible for inserting:

* Initial lookup values
* Application statuses
* Employment types
* Interview types
* Interview results
* Development/testing data

---

# Backend Architecture

The backend follows a layered Spring Boot architecture:

```
Controller
     |
Service
     |
Repository
     |
Entity
```

DTO conversion is handled through a dedicated mapper layer:

```
Entity <----> Mapper <----> DTO
```

This approach separates database entities from API communication models and keeps the application easier to maintain.

---

# Backend Implementation Status

## Completed

* Spring Boot application setup
* PostgreSQL configuration
* JPA/Hibernate entity mapping
* Database model implementation
* Repository layer
* DTO layer
* Mapper layer

---

# Implemented Entities

The following JPA entities have been implemented:

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

DTOs have been created for:

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

The mapper layer contains:

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

Repositories have been created for:

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

# Frontend

The frontend application is built with React, TypeScript and Vite.

Current frontend setup includes:

* React project structure
* TypeScript configuration
* Vite development environment

Planned frontend features:

* User authentication interface
* Dashboard
* Job application management screens
* Company and recruiter views
* CV and cover letter management
* Interview tracking interface
* Communication with backend REST APIs

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

The backend will start using the configured PostgreSQL database.

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

```
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

## Next Steps

* Service layer implementation
* Business logic implementation
* REST API controllers
* Request/Response validation
* Exception handling
* Authentication and authorization
* Frontend-backend integration
* Job application dashboard
* Search and filtering functionality
* Docker deployment

---

# License

This project is for educational and development purposes.

```
```
