## Career Path Planner (Mini Project)

A simple **rule-based career guidance web application** that helps students identify suitable career domains based on their **interests and comfort areas**.  
This project does **not use marks or performance**, only preferences provided by the student.

### Tech Stack

- **Backend**: Java, Spring Boot (REST API)
- **Frontend**: HTML, CSS, JavaScript (simple single-page UI)
- **Database**: Local MySQL (using Spring Data JPA)
- **Build Tool**: Maven

### Requirements

- JDK 17 (or higher compatible with Spring Boot 3)
- Maven
- MySQL server installed locally  
  (e.g. XAMPP / WAMP / standalone MySQL)
- Eclipse IDE (or any Java IDE) – optional

### Database Setup (Local MySQL)

1. Start your MySQL server.
2. Open any MySQL client (MySQL Workbench, phpMyAdmin, command line).
3. Create an empty database (no tables needed; Spring Boot will create them):

   ```sql
   CREATE DATABASE career_path_planner;
   ```

4. In `src/main/resources/application.properties`, ensure these values match your MySQL login:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/career_path_planner?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=
   ```

   Change `username` and `password` if your MySQL uses different credentials.

Spring Boot is configured with:

- `spring.jpa.hibernate.ddl-auto=update` – so tables like `assessment_results` are created automatically on first run.

### How to Run (Using Maven)

1. Open a terminal in the project root:

   ```bash
   cd career_path_planner
   mvn clean package
   mvn spring-boot:run
   ```

2. After the application starts, open your browser and go to:

   - `http://localhost:8080/`

3. Optionally, test backend health:

   - `http://localhost:8080/api/health`

### How to Run in Eclipse

1. Open Eclipse.
2. `File` → `Import…` → `Existing Maven Projects`.
3. Select the `career_path_planner` folder and finish the import.
4. Right-click the project → `Run As` → `Spring Boot App` (or `Java Application` with `CareerPathPlannerApplication`).
5. Open `http://localhost:8080/` in your browser.

### How It Works (Rule-Based Logic)

1. The frontend (`index.html`) shows a **simple questionnaire**:
   - 7 statements about interests (technology, core engineering, government stability, business, creative work, hands-on work, social impact).
   - Each is rated from **1 (Strongly Disagree) to 5 (Strongly Agree)**.
2. The answers are sent as JSON to the backend endpoint:
   - `POST /api/recommend`
3. The backend (`CareerRuleEngineService`) calculates scores for these domains using fixed rules:
   - IT & Software  
   - Core Engineering  
   - Government & Public Sector  
   - Business & Commerce  
   - Creative & Media  
   - Skilled Trades & Technical  
   - Social / NGO & Education
4. The **highest scoring domain** is selected as the **primary suggested career path**.
5. Other domains with scores close to the maximum are also shown as **“Other suitable domains”**.
6. Each result includes:
   - A **primary domain name**
   - A short **explanation** in simple language
   - Example **career roles**
   - Suggested **next steps** for the student
7. Each assessment is stored in the `assessment_results` table using JPA (name, email, chosen domains, and internal scores).

### Frontend Files

- `src/main/resources/static/index.html` – main UI page
- `src/main/resources/static/style.css` – modern responsive styling
- `src/main/resources/static/app.js` – handles the questionnaire, calls the backend, and displays results

### Backend Structure

- `CareerPathPlannerApplication.java` – Spring Boot main class
- `controller/CareerRecommendationController.java` – REST endpoints (`/api/health`, `/api/recommend`)
- `service/CareerRuleEngineService.java` – rule-based scoring and recommendation logic
- `dto/CareerAssessmentRequest.java` – input from the frontend
- `dto/CareerRecommendationResponse.java` – response sent back to the frontend
- `model/AssessmentResult.java` – JPA entity mapped to MySQL table
- `repository/AssessmentResultRepository.java` – Spring Data JPA repository

### Notes

- This is intentionally simple and **transparent** – rules can be easily explained and modified.
- You can extend it by:
  - Adding more questions
  - Fine-tuning the scoring weights
  - Adding pages for admin / viewing previous results
  - Exporting data for reports

