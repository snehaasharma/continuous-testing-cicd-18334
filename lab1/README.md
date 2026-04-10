# Lab 1 — Analyse, Map & Identify

**Module 1 · Continuous Testing Fundamentals**  
This folder is designed to be used as the root of a small sample repository (fork or copy into a new GitHub repo) for the Day 1 hands-on lab and instructor live demo.

## Prerequisites

- A GitHub account (for fork + Actions).
- Basic familiarity with YAML and pull requests (helpful but not required).

## Hands-on lab (learner)

Complete the three tasks below. Your **deliverables** are listed on the final slide of Day 1:

1. **Annotated pipeline YAML** — testing-related steps and triggers identified; gaps called out in comments or a short appendix.
2. **CI test coverage mapping document** — use the template in `deliverables/CI_TEST_MAPPING_TEMPLATE.md`.
3. **Continuous testing strategy worksheet** — use the template in `deliverables/STRATEGY_WORKSHEET_TEMPLATE.md`.

### Task 1 — Analyse the sample CI pipeline

1. Fork this material into your own repository (or copy `lab1/` contents to a new repo root so `.github/workflows/` is at the repository root).
2. Open `.github/workflows/ci.yml`.
3. List every **trigger** (`on:`) and **job** that relates to quality or testing.
4. Note which **test types** from the course (unit, integration, contract, E2E, performance, security, etc.) are **present** vs **missing**.
5. Add **comments** directly in a copy of `ci.yml` (your annotated version) explaining what each step does and where the risks are.

### Task 2 — Map test types to pipeline stages

1. Build a table: **Pipeline stage → Test type → Tool/command** (add a column for “Present / Missing” if helpful).
2. Use the stages from Task 1 as rows.
3. Classify coverage against the **test pyramid** (many fast unit tests, fewer integration, very few E2E).
4. Save as your completed mapping document (from the template).

### Task 3 — Automation opportunities

1. Starting from the **gaps** in Task 2, list what you would automate next.
2. Prioritise order (what first, what later).
3. For each item, estimate effort: **Low / Medium / High**.
4. Record in the strategy worksheet template.

## Live demo (instructor) — alignment with slides

Use the same `ci.yml` and this repo in the demo:

1. **Pipeline overview** — Walk through triggers, jobs, and steps; point to where tests run (push/PR) vs where they do not.
2. **Automated test execution** — Show a successful Actions run; open the **unit-tests** job log and relate pytest output to pass/fail and exit codes. Optionally show a failing run by temporarily breaking a test on a branch.
3. **Testing gaps** — Contrast **unit-tests** + **lint** with what is **not** present before **deploy-staging** (e.g. no API integration suite, no contract tests, no browser E2E, no performance gate). Tie risks to production and to the test pyramid.

## Local quick check (optional)

```bash
cd lab1
python3 -m venv .venv
source .venv/bin/activate   # Windows: .venv\Scripts\activate
pip install -r requirements.txt
pytest tests/ -v
ruff check .
```

## Repository layout

| Path | Purpose |
|------|---------|
| `.github/workflows/ci.yml` | Sample CI pipeline for analysis (intentional gaps) |
| `calculator.py` | Tiny module under test |
| `tests/test_calculator.py` | Unit tests executed in CI |
| `requirements.txt` | Runtime + test + lint dependencies |
| `deliverables/*` | Blank templates for Task 2 and Task 3 |
