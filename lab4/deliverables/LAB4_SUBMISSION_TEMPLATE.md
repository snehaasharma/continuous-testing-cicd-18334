# Lab 4 — Submission template

**Name:** ______________________ **Date:** ______________________

## 1. Pipeline configuration

- [ ] Attached or linked: **`.github/workflows/ci.yml`** *or* **`day4-pipeline.yml`** *or* **`Jenkinsfile`** *or* **`azure-pipelines.yml`** (the file **you** authored or extended for this lab).

**File name + repo path:** ______________________

## 2. Green run evidence

Link to a **successful** workflow run **or** screenshot showing:

- both **unit** and **integration** stages complete (GitHub), or equivalent stages in Jenkins/Azure.

**Link / note:** ______________________

## 3. Annotated failure log

Paste a **short** excerpt from a **failed** job log (Task 3) and **annotate** in comments:

```
(paste log excerpt here)
<!-- your comments: e.g. "Line X: first FAILURE summary", "Line Y: root AssertionError" -->
```

**Approx. time from push to first red signal:** ______________________

## 4. Recovery

**Commit SHA or message** that fixed the build: ______________________

## 5. Reflection (pick one)

- When would you choose **`fail-fast: false`** in a matrix?
- Why use **`if: always()`** (or Azure **`condition: always()`**) on report/artefact steps?

**Answer:**

-
