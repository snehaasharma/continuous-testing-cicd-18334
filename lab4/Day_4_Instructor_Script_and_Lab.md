# Day 4: CI Pipeline Design with Automated Testing

**Session Duration:** 2 Hours  
**Focus:** 40% Theory | 60% Hands-on  
**Goal:** Design real multi-stage CI pipelines using GitHub Actions/Jenkins that execute automated tests robustly, and learn how to triage CI failures.

---

## Part 1: Instructor Live Demo Script (Approx. 40-45 mins)

*Instructors: Follow this step-by-step to demonstrate creating a fully functional CI pipeline triggering tests.*

### Demo 1: Creating a CI Pipeline with Test Execution Steps

**Goal:** Build a green CI pipeline from scratch using GitHub Actions.

1. **Context Setting:** 
   *(Instructor Script)*: "A CI pipeline is our safety net. Any time a developer commits code, we want our automated tests to execute and act as the primary quality gate. Let's wire Maven up to GitHub Actions."
2. **Action:**
   - In your test repo, create a folder `.github/workflows/` and add `ci.yml`.
   - Walk through the structure:
     - `name:` and `on:` (triggers: push & pull_request).
     - `jobs:` (unit-test).
     - `runs-on: ubuntu-latest`.
     - `steps:` checkout code, setup Java, cache Maven dependencies, run tests (`mvn test`).
   - Push to a branch and switch immediately to the GitHub Actions tab.
   - Show the live job logs running and turning green.

### Demo 2: Adding Test Execution Steps & Viewing Pipeline Logs

**Goal:** Extend the pipeline with downstream stages and sequential execution.

1. **Action:**
   - Add a second job `integration-test` to the YAML file.
   - Set `needs: unit-test` to force sequential execution.
   - For the test command, run `mvn verify -Dgroups=integration`.
   - Add an artefact publish step at the end:
   ```yaml
   - name: Publish Test Report
     uses: actions/upload-artifact@v4
     if: always()
     with:
       name: surefire-reports
       path: target/surefire-reports/
   ```
   - Emphasize the importance of `if: always()` — we want reports regardless of success or failure.

### Demo 3: Analysing Failed Builds — Trace to Root Cause

**Goal:** Show participants what happens when a test breaks in CI and how to find the root cause fast.

1. **Context Setting:**
   *(Instructor Script)*: "When a build goes red, the timer starts. A broken build halts the entire team's ability to merge code. Fast diagnosis is a core CI/CD skill."
2. **Action:**
   - Introduce a deliberate typo or assertion failure in the Java test class.
   - Commit and push.
   - Navigate to the running GitHub Action. Watch it fail.
   - Open the job logs. Look for the non-zero exit code.
   - Scroll up to the **FAILURE** summary.
   - Keep scrolling to the specific `AssertionError` line. This is the root cause.
   - Fix the code and push again to verify the CI pipeline returns to green.

---

<br>

## Part 2: Participant Hands-on Lab (Approx. 75 mins)

**Welcome to the Day 4 Lab!** In this lab, you will configure a functional GitHub Actions pipeline, trigger it via PRs, and purposefully break it to practice your triage skills.

### Task 1: Configure CI Pipeline for Test Automation

1. Create a `.github/workflows/ci.yml` file in your repository from earlier labs.
2. Configure basic Triggers to run on push to any branch and pull_request to `main`.
3. Create a `unit-test` job. Add the following steps:
   - Configure Java 21 (`actions/setup-java`).
   - Add a dependency cache cache step (`actions/cache`).
   - Run the tests: `mvn test` (or `./gradlew test`).
4. Push to a branch and verify the GitHub Actions tab shows a passing pipeline.

### Task 2: Trigger Pipeline on Code Commit & View Results

1. Add a new test to a Feature branch.
2. Open a Pull Request from your feature branch to main.
3. Observe the CI run automatically as a status check on your PR page.
4. Add the `upload-artifact` step to your workflow configuration to save your `surefire-reports` XML.
5. Once your pipeline finishes, navigate to the pipeline summary page, download the test artifact (XML), and view its contents.
6. Merge the PR and verify `main` branch CI also executes.

### Task 3: Analyse Failed Builds — Locate Root Cause

1. Break your tests intentionally. Change an `assertEquals(1, 1)` to `assertEquals(1, 2)`, or mistype an import.
2. Push your broken commit to your repo and watch CI fail.
3. **Log Navigation:** 
   - Note the total time it took to give a red signal.
   - Navigate the job logs manually. Find the exit code line.
   - Locate your AssertionError line. Take a screenshot of the log showing the root cause.
4. Fix the test, push your code, and confirm your CI pipeline returns to a "Green" successful state.

---

## Final Deliverables Check

Before finishing Day 4, ensure you have:
- [ ] A functioning `.github/workflows/ci.yml` file committed to your repo.
- [ ] An Automated test execution log showing a *Green* test run.
- [ ] A screenshot/annotated failure log diagnosing an intentional CI break.
