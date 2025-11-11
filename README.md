### OHS Backend — Developer Exercise
A Spring Boot backend API implementing core patient and encounter management features for a digital health system.
This project demonstrates clean API design, validation, persistence, and documentation using modern Java and Spring Boot best practices.

## Table of Contents
**Requirements**

Getting Started

Running the Application

API Endpoints

Database Configuration

Running Tests

Project Structure

Tech Stack

Example Usage

Author

Requirements

Java: 17 or higher (tested with Java 21)

Maven: 3.8+

Port: Default 8080

Getting Started
Clone the Repository
git clone <repo-link>
cd ohs-backend-developer-exercise/starter-project

## Running the Application
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081

### Access the Services
**Service	URL**
-API Root	http://localhost:8080
-Swagger UI	http://localhost:8080/swagger-ui.html
-H2 Console	http://localhost:8080/h2-console

### API Endpoints
-Patient Management (CRUD)
-Method	Endpoint	Description
-POST	/api/patients	Create a patient
-GET	/api/patients	List all patients
-GET	/api/patients/{id}	Get patient by ID
-PUT	/api/patients/{id}	Update a patient
-DELETE	/api/patients/{id}	Delete a patient

## Search Filters
**Filter patients by familyName, givenName, identifier, birthDate range:**
GET /api/patients?familyName=Angwenyi

## Encounter Management
-Method	Endpoint	Description
-POST	/api/patients/{id}/encounters	Create a new encounter for a patient
-GET	/api/patients/{id}/encounters	List all encounters for a patient

## Encounter Fields
**Field	Type	Required	Description**
-type	String	✅	Encounter type (e.g., Consultation)
-encounterClass	String	❌	Encounter class (optional)
-start	ISO DateTime	❌	Start time (auto-filled if missing)
-end	ISO DateTime	❌	End time (optional)
-description	String	❌	Summary of the encounter


## Database Configuration
**In-Memory H2 (Default)**
spring.datasource.url=jdbc:h2:mem:ohsdb
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

**Do NOT use file-based H2 (jdbc:h2:file:...) if you want ephemeral, non-persistent data.**

## Running Tests
mvn test


## Project Structure
src/main/java/com/example/ohs
├── controller     # REST endpoints
├── domain         # JPA entities (Patient, Encounter)
├── dto            # Request/response DTOs
├── exception      # Global exception handler & error classes
├── repository     # Spring Data JPA repositories
├── service        # Business logic
└── OhsApplication.java  # Application entry point

### Tech Stack
-Java 17+
-Spring Boot 3.3.4
-Spring Data JPA
-H2 Database (in-memory)
-Springdoc OpenAPI / Swagger
-Maven

### Example API Usage
**Create a Patient**
curl -X POST http://localhost:8080/api/patients \
  -H "Content-Type: application/json" \
  -d '{
        "givenName": "Hesbon",
        "familyName": "Angwenyi",
        "birthDate": "2000-01-24",
        "gender": "M",
        "identifier": "P001"
      }'

**Get All Patients**
curl http://localhost:8080/api/patients

**Create an Encounter**
curl -X POST http://localhost:8080/api/patients/1/encounters \
  -H "Content-Type: application/json" \
  -d '{
        "type": "Consultation",
        "encounterClass": "Outpatient",
        "start": "2025-11-02T10:00:00Z",
        "description": "Routine check-up"
      }'

**Get All Encounters for a Patient**
curl http://localhost:8080/api/patients/1/encounters

### Author
Hesbon Angwenyi
Backend Software Engineer
hesbonamnyinsa96@gmail.com
Nairobi, Kenya