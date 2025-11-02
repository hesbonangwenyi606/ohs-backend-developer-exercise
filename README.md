# OHS Backend — Developer Exercise
A Spring Boot–based backend API implementing **core patient** and **encounter management** features for a digital health system.  
This project demonstrates clean API design, validation, persistence, and documentation using modern Java and Spring Boot best practices.

## Getting Started

### Requirements

- **Java:** 17 or higher (tested with Java 21)
- **Maven:** 3.8+
- **Port:** Default `8080`

---

### How to Run

#### Clone or Extract the Repository
git clone <repo-link>
cd ohs-backend-developer-exercise/starter-project
## Build and Start the Application
mvn clean spring-boot:run

## Access the Services
Service	URL
API Root	http://localhost:8080
Swagger UI (API Docs)	http://localhost:8080/swagger-ui.html
H2 Console	http://localhost:8080/h2-console

## Default Database Settings
Setting	Value
JDBC URL	jdbc:h2:file:./data/ohsdb
User	sa
Password	(leave blank)

## The database is stored locally in the data/ folder.
It persists data between runs, unlike the in-memory (mem:) version.

## Patient Management
CRUD Operations
Method	Endpoint	Description
POST	/api/patients	Create a patient
GET	/api/patients	List all patients
GET	/api/patients/{id}	Get patient by ID
PUT	/api/patients/{id}	Update a patient
DELETE	/api/patients/{id}	Delete a patient

##Search Filters
You can filter patients by:

Family name

Given name

Identifier

Birth date range

## Example:
GET /api/patients?familyName=Angwenyi

## Encounter Management
Method	Endpoint	Description
POST	/api/patients/{id}/encounters	Create a new encounter for a patient
GET	/api/patients/{id}/encounters	List all encounters for a given patient

## Encounter Fields
Field	Type	Required	Description
type	String	✅	Encounter type (e.g., Consultation)
encounterClass	String    	X	Start time (auto-filled if not provided)
end	ISO DateTime	          X	End time
description	String	        X	Summary of the encounter

### Additional Features
-Input validation using Jakarta Bean Validation
-Global exception handling returning ProblemDetail JSON
-Auto-generated Swagger / OpenAPI documentation
-Configurable file-based H2 database for persistence
-Clean DTO mapping and service layer separation

## Design Notes & Trade-offs
Area	Choice	Reason
Database	H2 (file-based)	Lightweight and persistent for development/testing
Entities & DTOs	Java Records for DTOs	Clean, immutable, and simple mapping
Error Handling	@ControllerAdvice + ProblemDetail	Consistent error format
Validation	Bean Validation (Jakarta)	Declarative and standardized
Security	None (for simplicity)	JWT or API key can be added later
API Docs	Springdoc OpenAPI	Interactive Swagger UI for testing

### Database Configuration (PostgreSQL Example)
## To use PostgreSQL instead of H2, edit src/main/resources/application.properties:
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ohsdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

## And add this to pom.xml:
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
Validation and error handling

## Project Structure
src/main/java/com/example/ohs
├── controller     # REST endpoints
├── domain         # JPA entities (Patient, Encounter)
├── dto            # Request/response DTOs
├── exception      # Global exception handler & error classes
├── repository     # Spring Data JPA repositories
├── service        # Business logic
└── OhsApplication.java  # Application entry point

## Tech Stack
Java 17+

Spring Boot 3.3.4

Spring Data JPA

H2 Database (file-based)

Springdoc OpenAPI

Maven

### Example API Usage
## Create a Patient
curl -X POST http://localhost:8080/api/patients \
  -H "Content-Type: application/json" \
  -d '{
        "givenName": "Hesbon",
        "familyName": "Angwenyi",
        "birthDate": "2000-01-24",
        "gender": "M",
        "identifier": "P001"
      }'
## Get All Patients
curl http://localhost:8080/api/patients

## Create an Encounter
curl -X POST http://localhost:8080/api/patients/1/encounters \
  -H "Content-Type: application/json" \
  -d '{
        "type": "Consultation",
        "encounterClass": "Outpatient",
        "start": "2025-11-02T10:00:00Z",
        "description": "Routine check-up"
      }'
## Get All Encounters for a Patient
curl http://localhost:8080/api/patients/1/encounters

## Built With:
This implementation focuses on correctness, clean modeling, and maintainability — balancing simplicity with production realism for digital health systems.

<<<<<<< HEAD
Tests demonstrating correctness

Full documentation (this README)

How it was built:
This implementation focuses on correctness, clean modeling, and maintainability — balancing simplicity with production realism for digital health backends.

Author
=======
## Author
>>>>>>> 471b518 (completed)
Hesbon Angwenyi
Backend Software Engineer
hesbonamnyinsa96@gmail.com
Nairobi, Kenya
