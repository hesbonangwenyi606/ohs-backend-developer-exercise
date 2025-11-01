# OHS Backend — Developer Exercise
A Spring Boot–based backend API implementing core patient and encounter management features for a digital health system.  
This project demonstrates clean API design, validation, persistence, and documentation using modern Java and Spring Boot practices.

---

## Getting Started

### Requirements
- **Java:** 17 or higher (tested with Java 21)
- **Maven:** 3.8+
- **Port:** Default is `8080` (configurable in `application.properties`)

---

### How to Run

1. Clone or extract the repository:
   ```bash
   git clone <repo-link>
   cd ohs-backend-developer-exercise/starter-project
Build and start the application:


mvn clean spring-boot:run
Access the services:

API Root: http://localhost:8080

Swagger UI: http://localhost:8080/swagger-ui.html

H2 Console: http://localhost:8080/h2-console

Setting	Value
JDBC URL	jdbc:h2:mem:ohsdb
User	sa
Password	(leave blank)

## Implemented Features
Patient Management
CRUD Operations

POST /api/patients — Create a patient

GET /api/patients — List patients

GET /api/patients/{id} — Get by ID

PUT /api/patients/{id} — Update patient

DELETE /api/patients/{id} — Delete patient

Search Filters

Search by family name, given name, identifier, or birth date range.

## Encounter Management
POST /api/patients/{id}/encounters — Create a new encounter for a patient

GET /api/patients/{id}/encounters — List all encounters for a given patient

## Additional Features
Input validation using Jakarta Bean Validation

Global exception handling returning ProblemDetail responses

Auto-generated OpenAPI/Swagger documentation

In-memory H2 database (configurable)

## Design Notes & Trade-offs
Area	Choice	Reason
Database	H2 in-memory	Lightweight and fast for development/testing
Entities & DTOs	Java records for DTOs	Simple, immutable, clear mapping
Error Handling	@ControllerAdvice with ProblemDetail	Provides consistent JSON error format
Validation	Bean Validation (Jakarta)	Declarative and standardized
Security	None (for simplicity)	API key or JWT can be added for production
API Docs	Springdoc OpenAPI	Provides live, interactive documentation

Database Configuration (Optional: PostgreSQL Example)
To use a persistent database instead of H2, edit src/main/resources/application.properties:

## properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ohsdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
And add the dependency in pom.xml:

xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
</dependency>
## Running Tests
Run all tests with:


## mvn test
Covers:

Core domain logic

Repository behavior

REST API integration (via MockMVC)

Validation and error responses

## Project Structure
bash
Copy code
src/main/java/com/example/ohs
├── controller     # REST endpoints
├── domain         # JPA entities (Patient, Encounter)
├── dto            # Request/response DTOs
├── exception      # Global exception handler & error classes
├── repository     # Spring Data JPA repositories
├── service        # Business logic
└── OhsApplication.java  # Entry point
## Tech Stack
Java 17+

Spring Boot 3.3.4

Spring Data JPA

H2 Database

Springdoc OpenAPI

Maven

## Example API Usage
Create a Patient

curl -X POST http://localhost:8080/api/patients \
  -H "Content-Type: application/json" \
  -d '{
        "givenName": "John",
        "familyName": "Doe",
        "birthDate": "1990-01-01",
        "gender": "M",
        "identifier": "P001"
      }'
Get All Patients

curl http://localhost:8080/api/patients
Create an Encounter

curl -X POST http://localhost:8080/api/patients/1/encounters \
  -H "Content-Type: application/json" \
  -d '{
        "type": "Consultation",
        "description": "Routine check-up"
      }'
 ## Submission Notes
What this project includes:

Working API meeting core requirements

Tests demonstrating correctness

Full documentation (this README)

Optional Dockerfile and migration scaffolding (if added)

How it was built:
This implementation focuses on correctness, clean modeling, and maintainability — balancing simplicity with production realism for digital health backends.

Author
Hesbon Angwenyi
Backend Software Engineer
hesbonamnyinsa96@gmail.com
Nairobi, Kenya