# Day 1: Continuous Testing with CI/CD - Fundamentals & Role of SDET/DevOps

**Session Duration:** 2 Hours  
**Focus:** 50% Theory | 50% Hands-on  
**Goal:** Understand Continuous Testing across the entire SDLC pipeline, align on the role of SDETs & DevOps engineers, and map testing types to pipeline stages.

---

## Part 1: Instructor Live Demo Script (Approx. 45-50 mins)

*Instructors: Use the steps below to demonstrate how testing integrates into a CI/CD pipeline and how to evaluate pipeline coverage.*

### Demo 1: CI/CD Pipeline Overview

**Goal:** Provide a high-level view of a multi-stage pipeline and identify testing trigger points.

1. **Context Setting:** 
   *(Instructor Script)*: "Traditional testing was an isolated phase at the end of development. High-performing teams now use Continuous Testing, where quality signals are generated at every stage of the pipeline."
2. **Action:**
   - Open GitHub Actions (or Jenkins/Azure DevOps depending on environment).
   - Walk through a sample multi-stage YAML pipeline file (e.g., `.github/workflows/ci.yml`).
   - Identify triggers: Pull Request (PR) and push to `main`.
   - Highlight the stages: `Build -> Unit Test -> Integration Test -> Deploy to Staging -> UI/E2E Test`.

### Demo 2: Review an Automated Test Execution in CI

**Goal:** Show participants how test results manifest in a CI environment and how to trace failures.

1. **Review CI Run:**
   - View a successfully completed pipeline run.
   - Inspect the logs for the test job.
2. **Simulate a Failure (If applicable, show historical failure):**
   - Trace a test failure back to a specific commit.
   - Discuss test pass/fail summaries and exit codes. Non-zero exit codes (like 1) block the pipeline.

### Demo 3: Identify Testing Gaps in a Pipeline

**Goal:** Apply the Test Pyramid to a pipeline to find missing quality gates.

1. **Context Setting:**
   *(Instructor Script)*: "The Test Pyramid guides us to have many fast unit tests and fewer slow E2E tests. If a pipeline lacks middle-tier integration tests, critical issues slip through, or E2E suites become bloated."
2. **Action:**
   - Display a sample pipeline missing an integration test stage.
   - Ask the audience: "What is missing here between Unit Tests and the Deploy stage?"
   - Discuss the risk of missing contract/integration coverage.

---

<br>

## Part 2: Participant Hands-on Lab (Approx. 60-75 mins)

**Welcome to the Day 1 Lab!** You will evaluate a typical CI pipeline configuration, map different testing strategies to its stages, and identify opportunities to introduce continuous automation.

### Task 1: Analyse a Sample CI Pipeline Configuration

1. **Fork the sample repository:** Obtain the standard course repository link from your instructor and fork it to your own GitHub account.
2. Open `.github/workflows/ci.yml` (or Jenkinsfile if applicable).
3. Review the file and **identify all testing-related steps and triggers.** Add comments to your file on what each stage does.
4. Note which test types (Unit, Integration, E2E) are currently present, and which appear to be missing.

### Task 2: Map Test Types to Pipeline Stages

1. Open a text document or spreadsheet to act as your "Continuous Testing Mapping Matrix".
2. **Create a mapping table:** `Stage -> Test Type -> Tool`
3. Use the CI stages from Task 1 as your rows.
4. Classify the test types involved: Unit, Integration, Contract, E2E, Performance.
5. Consider the **Test Pyramid**: Are there enough unit tests compared to E2E tests?

### Task 3: Identify Automation Opportunities

1. Based on the gaps identified in Task 2, create a prioritized list of what should be automated first.
2. **Estimate effort:** Classify each automation opportunity as Low, Medium, or High effort.
3. Document your findings in a **Continuous Testing Strategy Worksheet**. 

---

## Final Deliverables Check

Before finishing Day 1, ensure you have:
- [ ] Annotated Pipeline YAML with comments noting testing gaps.
- [ ] CI Test Coverage Mapping Document created.
- [ ] Continuous Testing Strategy Worksheet drafted with automation priorities.
