# OHS Backend — Starter implementation

How to run:
- Java 17+, Maven 3.8+
- From starter-project: `mvn clean spring-boot:run`
- API: http://localhost:8080
- H2 console: http://localhost:8080/h2-console (jdbc url: jdbc:h2:mem:ohsdb)

Implemented:
- Patient CRUD (POST/GET/PUT/DELETE)
- Patient search by family/given/identifier/birthDate range
- Encounters create and list per patient
- Basic validation with Bean Validation
- Global exception handler returning consistent problem details
- OpenAPI/Swagger available via springdoc at /swagger-ui.html

Notes & tradeoffs:
- Uses H2 in-memory DB for simplicity; switch to Postgres by editing application.properties and adding a driver.
- DTOs are minimal Java records mapping to domain manually.
- No authentication; consider API key header for production.
