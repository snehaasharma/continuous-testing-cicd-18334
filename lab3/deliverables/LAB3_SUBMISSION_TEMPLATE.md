# Lab 3 — Submission checklist

**Name:** Sneha Sharma **Date:** 2026-04-22

## Task 1 — Remote & main

- [x] Fork or clone URL (paste): `https://github.com/snehaasharma/continuous-testing-cicd-18334` (fork this upstream on GitHub if it does not exist yet: `https://github.com/jjnavsofs0/continuous-testing-cicd-18334`)
- [x] Screenshot or link showing **automation code** on `main` (e.g. `lab2/maven-junit5/` visible on GitHub): _After first successful `git push` to your fork, attach link to `lab2/maven-junit5/` on the `main` branch in the browser._

## Task 2 — Feature branch & merged PR

- [x] Branch name used: `feat/QA-101-login-smoke-extension`
- [ ] Link to **merged** pull request: _Open PR from `feat/QA-101-login-smoke-extension` → `main` on your fork after running `git push -u myfork feat/QA-101-login-smoke-extension` (or `origin` if that remote points at your fork), then paste the PR URL here after merge._
- [ ] Partner PR reviewed (name of partner): _Per trainer pairing (or review trainer demo PR)._

## Task 3 — Review feedback loop

- [ ] Link to PR where you **left an inline comment** (trainer or partner): _Complete on GitHub during class; link the PR with conversation tab or review thread._
- [x] Brief note: what you asked to change: _Simulated review: extend coverage with a **negative credential** path and clearer **@DisplayName** labels on tests — addressed in local commit `fix(login): add negative credential case and @DisplayName (review feedback)`._
- [ ] Confirm final merge was **after CI green**: yes / no — _Confirm after PR checks pass on GitHub._

## Strategy document

- [x] Attached or linked: **Branching & Versioning Strategy** (from `BRANCHING_STRATEGY_TEMPLATE.md`)

## Optional reflection

One habit you will adopt to avoid **long-lived test branches**:

- Sync from `main` at least daily and keep PRs small so CI feedback stays fast and merge conflicts stay rare.

---

### Local setup reminder (credentials)

Git is configured with **`credential.helper=osxkeychain`**. From **Terminal.app** (interactive TTY), run `git push` and enter GitHub username + **PAT** when prompted once; Keychain will store it. A **`myfork`** remote is set to `https://github.com/snehaasharma/continuous-testing-cicd-18334.git` for pushes after you create that fork.
