# Lab 3 — Push, branch, review & merge (Module 3)

**Day 3 · Version control & test code management**  
**Tools:** Git, GitHub / GitLab (examples use GitHub).

## How to run this lab

Use a **fork** of the course repository (or a fresh empty repo if your trainer provides one). You need permission to push branches and open pull requests.

- If you use the **course monorepo**, most exercises touch **`lab2/maven-junit5/`** (Day 2 automation skeleton) and Git metadata under **`.github/`**.
- Work with a **partner** for Task 2 (review swap) if the trainer organises pairs.

## Task 1 — Push automation code to the remote

1. **Clone** your fork: `git clone <your-fork-url> && cd <repo>`.
2. Ensure **Day 2 code** is present (`lab2/maven-junit5/` with `pom.xml` and tests). On the course repo it is already there; on an empty fork, copy that folder from the trainer’s source or upstream.
3. **Commit hygiene:** if you are adding the skeleton for the first time:

   ```bash
   git checkout main
   git pull origin main
   git add lab2/maven-junit5
   git commit -m "chore(automation): add framework skeleton for course"
   git push -u origin main
   ```

4. Confirm on GitHub: **latest commit on `main`**, files visible, **CI** (if enabled) green on `main`.

## Task 2 — Feature branch, PR, review, merge

1. Create a branch using the naming convention from the deck:

   ```bash
   git checkout main
   git pull origin main
   git checkout -b feat/QA-101-login-smoke-extension
   ```

2. Add **one new test class** for the exercise:
   - Copy [`participant-work/FeatureBranchExerciseTest.java`](participant-work/FeatureBranchExerciseTest.java) into:

     `lab2/maven-junit5/src/test/java/com/training/ctcicd/lab2/tests/`

   - Adjust the test so it exercises **`LoginPage`** or **`FrameworkConfig`** meaningfully (not a trivial `assertTrue(true)`).

3. Commit with **Conventional Commits** style:

   ```bash
   git add lab2/maven-junit5/src/test/java/com/training/ctcicd/lab2/tests/FeatureBranchExerciseTest.java
   git commit -m "feat(login): add Day 3 feature-branch exercise test"
   git push -u origin feat/QA-101-login-smoke-extension
   ```

4. On GitHub: open a **Pull Request** into `main`, use the PR template if present.
5. **Swap with a partner:** open their PR, leave at least **one constructive review comment**, approve when satisfied, **merge** after **CI is green** (and per your trainer’s branch protection rules).
6. Delete the remote branch after merge if your team policy says so; update local:

   ```bash
   git checkout main
   git pull origin main
   ```

## Task 3 — Review feedback loop

1. Open the **trainer’s demo PR** (or a partner PR seeded with an issue — see [`instructor/DEMO_SCENARIOS.md`](instructor/DEMO_SCENARIOS.md)).
2. Leave an **inline comment** on a specific line (e.g. hardcoded URL, weak assertion, missing tag).
3. As **author**, push a **new commit** addressing the feedback; confirm **stale review** behaviour if branch protection dismisses old approvals.
4. **Re-review**, ensure **CI passes**, then merge.

### Optional: rebase after `main` moved

```bash
git fetch origin
git rebase origin/main
# resolve conflicts if any → git add … → git rebase --continue
git push --force-with-lease
```

Use **`--force-with-lease`** only after rebase, never blind `--force`.

## Deliverables

1. **Link** to your merged PR (or screenshot of merge + commit on `main`).
2. Completed **[`deliverables/BRANCHING_STRATEGY_TEMPLATE.md`](deliverables/BRANCHING_STRATEGY_TEMPLATE.md)** (short strategy doc for your test repo).
3. **[`deliverables/LAB3_SUBMISSION_TEMPLATE.md`](deliverables/LAB3_SUBMISSION_TEMPLATE.md)** checklist.

## Reference files (copy into your fork root as needed)

| File | Where to install |
|------|------------------|
| [`templates/PULL_REQUEST_TEMPLATE.md`](templates/PULL_REQUEST_TEMPLATE.md) | `.github/PULL_REQUEST_TEMPLATE.md` |
| [`templates/CODEOWNERS.example`](templates/CODEOWNERS.example) | `.github/CODEOWNERS` (edit `@handles`) |

## Instructor-led flow

Timed talk track and live GitHub demo: **[`DEMO_SCRIPT.md`](DEMO_SCRIPT.md)**.  
Trainer-only conflict / “bad PR” recipes: **[`instructor/DEMO_SCENARIOS.md`](instructor/DEMO_SCENARIOS.md)**.
