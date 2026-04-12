# Day 6: Test Environment, Containers & Test Data

**Session Duration:** 2 Hours  
**Focus:** 40% Theory | 60% Hands-on  
**Goal:** Containerise test execution for CI consistency, handle dependencies with Docker Compose & Testcontainers, and design reproducible test data strategies.

---

## Part 1: Instructor Live Demo Script (Approx. 45-50 mins)

*Instructors: Start by demonstrating why local environment inconsistencies ruin tests, then solve it with Docker.*

### Demo 1: Running Tests in Docker Containers

**Goal:** Ensure "it works on my machine" becomes "it works identically everywhere."

1. **Context Setting:** 
   *(Instructor Script)*: "A CI runner is completely stateless. To perfectly recreate the environment a test needs without painful global tooling installations, we use containers."
2. **Dockerfile creation:**
   - Walk through `Dockerfile.test` using `eclipse-temurin:21-jdk-alpine`.
   - Emphasize the importance of pulling POM layer dependencies before the source code to leverage Docker caching.
3. **Execution Execution:**
   - Build the image: `docker build -f Dockerfile.test -t myapp-test .`
   - Run via Docker Compose: `docker compose -f docker-compose.test.yml up --abort-on-container-exit`.
   - Point out how a database health check keeps the test runner from starting too early.

### Demo 2: Configuring Environment Variables for Tests

**Goal:** Separate code from configuration explicitly for testing in containers.

1. **Context Setting:**
   *(Instructor Script)*: "If your `localhost` DB password is baked into `src/test/resources/application.properties`, you cannot run this correctly in CI securely. We override this via env-vars."
2. **Demonstrate Variable Override:**
   - Show `application-test.properties` using Spring's/Maven's override syntax: `spring.datasource.url=${DB_URL:jdbc:h2...}`.
   - Run tests without setting the env-var (tests fallback to in-memory H2).
   - Export the URL to point to the Compose Postgres instance. Observe the runtime switch to a real DB.
   - Show how CI handles Secrets via YAML configuration dynamically.

### Demo 3: Managing Test Data Sets

**Goal:** Maintain test encapsulation so parallel testing runs don't step on each other's toes.

1. **Deterministic Data:**
   - Run `seed.sql` before all tests. Ensure baseline data exists.
2. **Dynamic Prefixing:**
   - During the test insert action, use a UUID prefix: `INSERT user_test_123456...`.
   - Show that parallel attempts to create `user_test_default` will result in database key clashes.
3. **Transactional Reset:**
   - Add `@Transactional` to a test. Insert a row, test it, and then inspect the database post-test to prove the transaction rolled back seamlessly.

---

<br>

## Part 2: Participant Hands-on Lab (Approx. 70-75 mins)

**Welcome to the Day 6 Lab!** Your mission is to take your test framework, package it into a Docker execution container, and run it against an ephemeral database using Docker Compose.

### Task 1: Create Dockerfile for Test Execution

1. In your project root, create a file named `Dockerfile.test`.
2. Start `FROM eclipse-temurin:21-jdk-alpine`.
3. Structure the layers to cache Maven dependencies:
   - `COPY pom.xml .`
   - `RUN mvn dependency:go-offline`
   - `COPY src/ src/`
4. Build the image: `docker build -f Dockerfile.test -t myapp-test .`
5. Verify it builds successfully and exists in your image cache.
6. Verify your test runs containerised: `docker run --rm myapp-test mvn test`

### Task 2: Set Up Docker Compose with Test Database

1. Create `docker-compose.test.yml`.
2. Add a `postgres:16-alpine` service. Be sure to configure a robust `healthcheck` block to monitor that the DB is ready on startup.
3. Add a `test-runner` service pointing to your `Dockerfile.test`.
4. Ensure `test-runner` includes `depends_on: postgres: condition: service_healthy`.
5. Pass database credentials securely to the test runner via the `environment:` block without explicitly hardcoding standard passwords.
6. Run the stack: `docker compose -f docker-compose.test.yml up --abort-on-container-exit`.

### Task 3: Environment Configuration Documentation

1. Create a markdown document or update your `README` to include **Environment Configuration Documentation**.
2. Outline what default properties your project expects (`application-test.properties`).
3. Define the exact Env-Vars needed to run the tests (e.g., `DB_URL`, `DB_USER`). Note where a developer or DevOps engineer needs to slot CI Secrets.
4. In your code, introduce a mechanism (like `seed.sql` or JUnit Extensions) to run baseline inserts with prefixed values for reliable data states.
5. Create a GitHub Actions workflow job that runs the `docker compose up` command in your CI environment for deployment-like integration validation.

---

## Final Deliverables Check

Before leaving the session, ensure you have:
- [ ] A functioning `Dockerfile.test` and `docker-compose.test.yml`.
- [ ] Demonstrated green console output executing tests against an ephemeral Postgres container.
- [ ] Authored Environment Configuration Documentation specifying your required variables and seed strategies.
