# Day 8: End-to-End Continuous Testing Strategy & Capstone Preparation

**Session Duration:** 2 Hours  
**Focus:** 30% Strategy | 70% Capstone  
**Goal:** Synthesise all 7 modules into a cohesive testing strategy and design a complete CI pipeline architecture for the Capstone Project.

---

## Part 1: Instructor Live Demo Script (Approx. 30 mins)

*Instructors: Start by summarizing the 7-day journey. Connect the dots across frameworks, API automation, CI/CD, and quality gates.*

### Demo 1: The 8-Day Journey Refresher
1. **Context Setting:**
   *(Instructor Script)*: "We started with the fundamentals of why testing needs to shift-left, and we end today with a fully automated, containerized pipeline. Let's quickly review where everything fits."
2. **Review Elements:**
   - **Frameworks (Day 2) + APIs (Day 5)**: This is our base test code execution layer.
   - **Version Control (Day 3)**: This is how we govern changes through branches and PRs securely.
   - **Containers (Day 6)**: This ensures our tests have a clean, reproducible database to execute against without external "works on my machine" issues.
   - **Pipelines & Gates (Day 4 & 7)**: The YAML definitions that tie it all together, blocking bad code via JaCoCo constraints and reporting everything visually via Allure.

### Demo 2: Capstone Brief Walkthrough
**Goal:** Clearly outline what participants must construct for their capstone.
1. Outline the Scenario: They are joining as the lead SDET for a pre-existing Spring Boot REST API. It has no CI pipeline.
2. Outline the 3 components of the Capstone Document:
   - **Test Strategy Document** (Pyramid distribution, Tools chosen).
   - **CI Pipeline Design** (YAML Job graph: triggers -> unit -> api -> quality gate).
   - **Environment/Data Configuration** (Mock vs real databases).

---

<br>

## Part 2: Capstone Work Session (Approx. 90 mins)

**Welcome to the Capstone Lab!** This is where you bring it all together. Design a complete CI pipeline and strategy for the dummy Spring Boot REST API application.

### Phase 1: Draft Test Strategy & Tool Selection (30 Mins)
1. **Define your Pyramid Split:** Write a brief rationale determining what % of efforts should go into unit vs API versus UI tests.
2. **Tool Selection:** Document your choices. (Hint: Look at the libraries injected into your `starter-repo` `pom.xml`, e.g., JUnit 5, REST Assured, JaCoCo, Allure).
3. **Quality Gates:** Explicitly state your required limits (e.g. Line coverage ≥ 80%).

### Phase 2: Design CI Pipeline YAML Structure (30 Mins)
1. Open your code editor and sketch out your `ci.yml` skeleton.
2. Map out the `jobs` block:
   - Job 1: `unit-test`
   - Job 2: `api-test` (Needs: `unit-test`)
   - Job 3: `report-publish` (Needs: `api-test`)
3. Detail out the specific CLI commands inside each job (e.g. `mvn test`, `mvn verify`, `mvn allure:report`). Don't forget `actions/cache` steps!

### Phase 3: Peer Review & Presentation (30 Mins)
1. Partner up or break into small groups.
2. Deliver a 5-minute explanation of your design, focusing heavily on *why* you chose specific execution orders or coverage limits.
3. As the reviewer, deliver structured feedback:
   - *One Strength:* "Good use of fail-fast sequencing."
   - *One Question:* "What happens if the database container doesn't spin up in time?"
   - *One Improvement:* "Consider adding dynamic UUIDs to your test data seeding."

---

## Final Deliverables Check

Before finishing Day 8 and the course, ensure you have:
- [ ] A completed Capstone Project Strategy document outlining your E2E continuous testing plan.
- [ ] A well-structured CI Pipeline YAML diagram/file.
- [ ] Exchanged actionable Peer Review feedback with a colleague.
