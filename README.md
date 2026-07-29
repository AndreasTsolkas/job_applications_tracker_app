# Job Application Tracker

A full-stack application for managing job applications, companies, job postings, CVs, cover letters, recruiters, and interview progress.

The goal of this project is to provide a complete system for tracking the job application process from discovering a job opportunity to managing interviews and application status history.

## Technologies

### Frontend

* React
* TypeScript
* Vite

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* Lombok

### Database

* PostgreSQL

## Project Structure

```
job_applications_tracker_app
│
├── backend
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com.example.demo
│   │       │       ├── entity
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
├── database
│   ├── create.sql
│   └── seed.sql
│
└── README.md
```

## Database

The application uses PostgreSQL.

The database schema includes:

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

Database scripts:

* `create.sql` - creates all database tables, constraints and indexes.
* `seed.sql` - inserts initial sample data.

## Backend Features

Current backend implementation:

* Spring Boot application setup
* PostgreSQL connection
* JPA/Hibernate entity mapping
* Database model implementation

Implemented entities:

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

## Running the Backend

Navigate to the backend folder:

```bash
cd backend
```

Run the application:

```bash
./mvnw spring-boot:run
```

The application will start using the configured PostgreSQL database.

## Database Setup

Create a PostgreSQL database and execute:

```sql
create.sql
```

Then populate initial data:

```sql
seed.sql
```

## Future Development

Planned features:

* REST API endpoints
* Authentication and authorization
* Application management interface
* Company and job search functionality
* CV and cover letter management
* Interview scheduling
* Frontend implementation
* Docker deployment

## License

This project is for educational and development purposes.

```
```
