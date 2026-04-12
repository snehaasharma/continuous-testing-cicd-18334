# Day 5: API & Service-Level Continuous Testing

**Session Duration:** 2 Hours  
**Focus:** 35% Theory | 65% Hands-on  
**Goal:** Implement fast, reliable API tests using REST Assured and Newman, validate JSON contracts, and integrate API suites directly into CI pipelines.

---

## Part 1: Instructor Live Demo Script (Approx. 40-45 mins)

*Instructors: Walk through the creation of API tests and JSON schema validation, explicitly focusing on their speed compared to UI tests.*

### Demo 1: Writing API Automation Tests with REST Assured

**Goal:** Demonstrate the `given-when-then` DSL in Java over REST.

1. **Context Setting:** 
   *(Instructor Script)*: "API tests are the sweet spot of continuous testing. They give us high confidence like E2E tests, but run almost as fast as unit tests. Let's write one using REST Assured."
2. **Action:**
   - In your Java project, ensure `rest-assured` 6.0+ is in the `pom.xml`.
   - Write a simple GET test:
     ```java
     import static io.restassured.RestAssured.*;
     import static org.hamcrest.Matchers.*;
     
     @Test
     public void getUser_returns200() {
         given()
           .baseUri("https://api.example.com")
         .when()
           .get("/users/1")
         .then()
           .statusCode(200)
           .body("name", equalTo("Alice"));
     }
     ```
   - Demonstrate a JSON Schema validation block using `matchesJsonSchemaInClasspath("schemas/user-schema.json")`.
   - Run the test locally via IDE and terminal (`mvn verify -Dgroups=api`).

### Demo 2: Executing API Tests in CI Pipeline

**Goal:** Treat the API suite as a discrete, valuable job in the CI workflow.

1. **GitHub Actions config:**
   - Add an `api-test` job in `.github/workflows/ci.yml`.
   - Add `needs: unit-test` so it runs post-compilation/unit.
   - Inject repository variables, specifically showing how NOT to hardcode URLs in code:
     ```yaml
     env:
       API_BASE_URL: ${{ vars.STAGING_URL }}
     ```
   - Push to branch and observe the `api-test` job resolving successfully online.
   - Download the `failsafe-reports` artefact to show the output XML.

### Demo 3: Analysing API Test Reports & Validating Response Data

**Goal:** Debug common failure points in API/Contract testing.

1. **Newman CLI Comparison:**
   - Briefly show a Newman run via CLI to compare JS collections against Java code.
2. **Break a Contract (Schema):**
   - Deliberately modify the `user-schema.json` to require a `middle_name` property that does not exist on the API side.
   - Run the test and observe how REST Assured highlights exactly what part of the schema failed validation.
3. **Configuration Failure:**
   - Modify your pipeline to use an invalid `API_KEY`.
   - Show how to rapidly diagnose a `401 Unauthorized` in CI, distinguishing an infra config failure from an application defect.

---

<br>

## Part 2: Participant Hands-on Lab (Approx. 75 mins)

**Welcome to the Day 5 Lab!** You will write automated REST API checks and integrate them into your GitHub Actions pipeline, paying special attention to environment separation via injected URL variables.

### Task 1: Create Automated API Tests (REST Assured)

1. Open your project `pom.xml` and add the `rest-assured` (version 6.0.0 or later if on Java 17) and `json-schema-validator` dependencies.
2. Create an Integration Test class (e.g. `UserApiIT.java`) inside `src/test/java`.
3. Write a test asserting that a `GET /users/{id}` returns the expected status code and body assertions.
4. Add a sample JSON Schema file into `src/test/resources/schemas/`.
5. Write a test utilizing the `matchesJsonSchemaInClasspath` assertion on the schema file you created.
6. Verify your test runs on the command line: `mvn verify -Dgroups=api`

### Task 2: Integrate API Tests into CI Pipeline

1. Open `.github/workflows/ci.yml`.
2. Add a new job named `api-test`. Make it depend on the `unit-test` job (`needs: unit-test`).
3. Have the job execute `mvn verify -Dgroups=api`.
4. Define `API_BASE_URL` as an `env:` variable passing from a GitHub repository variable.
5. Add an `upload-artifact` step to extract your `failsafe-reports` if `always()`.
6. Push your branch, open a PR, and verify that the `api-test` job triggers and passes.

### Task 3: Validate Response Data and Status Codes

1. Back in your IDE, add a negative test: Try to `GET` a user ID that doesn't exist (e.g. `99999`) and assert that the endpoint returns a `404` status code.
2. Add a `POST` test: Create a user and assert a `201` status code.
3. Break the schema by modifying `schemas/user-schema.json` to require a field you know the API doesn't return.
4. Push the code and watch the CI pipeline fail your `api-test` job.
5. Review the resulting `failsafe` XML by downloading the artefact in Actions. Note how the schema failure is represented.
6. Fix the broken contract locally, re-push, and ensure your workflow runs completely 'Green'.

---

## Final Deliverables Check

Before leaving the session, ensure you have:
- [ ] Included an API Automation Test Suite utilizing JSON Schema validation.
- [ ] Gathered CI Execution Evidence showing your pipeline successfully executing the `api-test` job.
