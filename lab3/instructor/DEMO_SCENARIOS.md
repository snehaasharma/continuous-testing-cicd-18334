# Day 3 — Instructor demo scenarios (trainer only)

Use a **throwaway fork** or training org repo. Do not run destructive force-pushes on production repos.

---

## Scenario A — Merge conflict + safe force-push

**Goal:** Show conflict markers and `git push --force-with-lease` after rebase.

**Setup:**

1. On **`main`**, edit `lab2/maven-junit5/src/main/java/com/training/ctcicd/lab2/pages/LoginPage.java` — change the **Javadoc first line** (or add a comment after `public final class LoginPage {`) to say `// main: trainer baseline`.
2. Commit and push: `git commit -am "chore: trainer update LoginPage on main" && git push origin main`.

**Participant branch (or second local clone):**

1. Before pulling `main`, create branch `feat/demo/conflict-lab` from an **older** `main` (or edit the **same line** differently).
2. Change the **same region** of `LoginPage.java` to `// branch: trainee change`.
3. Commit and push; open PR.

**Resolution:**

```bash
git fetch origin
git rebase origin/main
# Fix conflict in IDE → one combined comment or choose one side
git add lab2/maven-junit5/src/main/java/com/training/ctcicd/lab2/pages/LoginPage.java
git rebase --continue
git push --force-with-lease
```

**Talking point:** same file hot spots in real teams — Page Objects, `testng.xml`, shared fixtures.

---

## Scenario B — “Bad PR” for Task 3 (review practice)

**Goal:** Give learners a clear defect to catch in review.

**On a branch**, introduce **one** of:

- Hardcoded URL in a new test: `String url = "http://hardcoded.example/login";` instead of `FrameworkConfig`.
- Assertion that always passes: `assertThat(true).isTrue();` with a comment “TODO”.
- Removed `@Tag("smoke")` from a test that your team policy requires tagged.

Open PR; ask class to **inline comment** + request change; you push fix commit; they re-approve.

---

## Scenario C — Branch protection checklist (screenshare)

In **Settings → Branches → Add rule** for `main`:

- Require a pull request before merging  
- Required approvals ≥ 1  
- Require status checks (select your CI job names, e.g. **Lab2 (Maven / JUnit 5)**)  
- Require branches to be up to date before merging  
- Dismiss stale pull request approvals when new commits are pushed  
- Optionally: Require review from Code Owners  

**Note:** Required checks only appear after the workflow has run at least once on a PR.

---

## Scenario D — CODEOWNERS demo

1. Add `.github/CODEOWNERS` from `lab3/templates/CODEOWNERS.example` with your real `@username` or `@org/team`.
2. Enable **Require review from Code Owners** on `main`.
3. Open a PR touching `lab2/maven-junit5/**` — show auto-requested reviewer.

---

## GitLab equivalents (one slide / verbal)

- **Merge request approvals** + **protected branches** + **pipeline must succeed**  
- **CODEOWNERS** file in `.gitlab/CODEOWNERS` (syntax differs slightly — point to GitLab docs)
