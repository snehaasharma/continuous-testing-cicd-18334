# Day 3 — Live demo & 2-hour session script

**Module 3 · Version control & test code management**  
Use with `Day3_ContinuousTesting_CICD.pdf`, `lab3/README.md`, and the course repo (or a demo fork). **Mix:** ~45% theory / ~55% hands-on.

**Pre-flight**

- Browser: GitHub logged in as trainer; second window or incognito for “participant” view if useful.
- Local clone of the **demo fork** (not production) with `origin` pointing to GitHub.
- Optional: branch protection + required checks configured on the demo repo (see `instructor/DEMO_SCENARIOS.md`).
- Open: `lab2/maven-junit5/pom.xml`, `LoginPage.java`, `.github/workflows/ci.yml`, and `lab3/templates/PULL_REQUEST_TEMPLATE.md`.

---

## Timeline (120 minutes)

| Block | Minutes | Focus |
|-------|---------|--------|
| A | 0–7 | Welcome, link to Day 1–2, outcomes |
| B | 7–22 | Git workflows: Feature branch, GitHub Flow, TBD |
| C | 22–32 | Strategy comparison table + GitFlow warning |
| D | 32–45 | Commit hygiene, branch names, Conventional Commits, test data in repo |
| E | 45–58 | PR practices: small PRs, template, CI green, CODEOWNERS, review depth |
| F | 58–70 | Branch protection + required checks; GitLab one-liner |
| G | 70–80 | Merge conflicts: causes, rebase vs merge, prevention |
| **H** | **80–98** | **Live demo 01–03** (repo → PR → merge; optional conflict) |
| I | 98–115 | Hands-on Tasks 1–3 + roaming |
| J | 115–120 | Deliverables, takeaways, recap quiz |

*Compress B or D by 3–5 min if behind; protect **H** and **I**.*

---

## Block A — Outcomes (0–7 min)

**Say:** “Pipelines run what’s in **Git**. Today we make test automation repos **safe to change in parallel**: branches, reviews, and rules — not just commands.”

**Show:** Learning outcomes slide.

---

## Block B — Workflows (7–22 min)

**Say:**

- **Feature branch:** `feat/…` from `main`, PR, CI on PR, merge when green — default for many QA-heavy teams.
- **GitHub Flow:** **short-lived** branches (hours to a day or two), PR early, merge to `main` often; CD often tied to `main`.
- **Trunk-based:** very short branches or direct to `main`, **feature flags** for app code; **fast CI** is non-negotiable.

**Show:** Workflow slide. Draw one line: **PR + CI + review** as the quality gate.

---

## Block C — Comparison (22–32 min)

**Walk the table:** branch lifespan, CI trigger, conflict risk, review gate, “best for QA teams vs senior teams.”

**Say:** “**GitFlow** is on the slide as a warning — heavy release branches fight continuous delivery; for new automation repos prefer **GitHub Flow** or **TBD**.”

---

## Block D — Commit hygiene (32–45 min)

**Show:** Branch naming `feat/QA-101-login-smoke-tests`, commits `feat(login): …`.

**Say:**

- Version **test data** with code; **config shape** in repo, **secrets** in GitHub Secrets / vault, injected in CI.
- `.gitignore`: `target/`, IDE noise, logs; never **tokens or PII**.
- **Tags** on test repo releases to align with app releases (`v1.4.2-tests`).

**Quick IDE flash:** `lab2/maven-junit5/.gitignore` or root `.gitignore`.

---

## Block E — Pull requests (45–58 min)

**Say:**

- Draft PR → CI on push → review → fix → **green + approve** → merge.
- **Small PRs** (< ~400 lines): one concern — easier review, less risk.
- **Meaningful description:** what, why, how to verify locally.
- **Never merge red CI** — branch protection makes that a button state, not a culture talk.
- **CODEOWNERS:** right reviewers for `**/payment/**` tests vs `.github/workflows/**`.

**Show:** PR slide + open `lab3/templates/PULL_REQUEST_TEMPLATE.md` (and root `.github/PULL_REQUEST_TEMPLATE.md` if you added it).

---

## Block F — Branch protection (58–70 min)

**Say:**

- Require PR, ≥1 approval, **required status checks**, up-to-date with `main`, dismiss stale reviews.
- **Require code owners** where compliance needs it.
- **Draft PRs:** CODEOWNERS often fire when **ready for review**, not always while draft — set expectations.

**Show:** Branch protection slide; if live, **Settings → Branches → Rule for `main`**.

---

## Block G — Merge conflicts (70–80 min)

**Say:**

- Typical pain: **same Page Object**, `testng.xml` / `pytest.ini`, **shared base class**, **CSV data**, **long-lived branches**.

**Say:**

- `git fetch` + `git rebase origin/main` → linear history; resolve markers → `git add` → `git rebase --continue`.
- Push: **`git push --force-with-lease`** after rebase — safer than `--force` (won’t clobber unseen pushes).

**Show:** Conflict slide + prevention: **merge main daily**, **short branches**, **split suite files**.

---

## Block H — Live demo: Repo → PR → merge (80–98 min)

Use a **dedicated demo fork** or org repo. Speak commands aloud; participants watch the **GitHub UI** (Actions tab, PR checks, merge button).

### Demo 01 — Repository & skeleton (~6 min)

**Narrate:**

1. “We already have **README**, **`.gitignore`**, and **CI** — same as a real automation repo.”
2. Show **`lab2/maven-junit5/`** on `main` in the browser — “Day 2 skeleton lives here.”
3. `git clone`, `cd`, `git status`, `git log --oneline -3` on **`main`**.

**Optional:** “Initialise empty repo” variant only if teaching from zero — usually skip to save time.

### Demo 02 — Feature branch, commit, push, PR (~10 min)

**Execute:**

```bash
git checkout main
git pull origin main
git checkout -b feat/demo/QA-101-login-smoke
```

Make a **tiny** change (e.g. add a comment in `LoginPage.java` or a one-line test in an existing test class — or use the participant template file).

```bash
git add lab2/maven-junit5/src/main/java/com/training/ctcicd/lab2/pages/LoginPage.java
git commit -m "feat(login): document Day 3 demo change for PR flow"
git push -u origin feat/demo/QA-101-login-smoke
```

**On GitHub:**

1. Open **Compare & pull request**.
2. Show **PR template** auto-filled sections.
3. Open **Actions** (or Checks): “Same **quality gate** as production — failing tests block merge.”
4. **Request reviewer** (or show CODEOWNERS if configured).
5. **Merge** (squash or merge — name team policy), **delete branch**.

**Say:** “**Exit code** from CI is the same contract as Day 2 — today we care *when* it runs: every push on the PR.”

### Demo 03 — Merge conflict & rebase (~8 min, optional if time tight)

Follow **`lab3/instructor/DEMO_SCENARIOS.md` — Scenario A**.

**Outline:**

1. Trainer merges a change to **`LoginPage.java`** on `main`.
2. Participant branch still has old base + conflicting edit to same region.
3. `git fetch origin && git rebase origin/main` → show **`<<<<<<<`** markers in IDE.
4. Resolve, `git add`, `git rebase --continue`, `git push --force-with-lease`.
5. PR updates; CI re-runs.

**Say:** “**--force-with-lease** means: fail if someone else pushed — I don’t overwrite their work by accident.”

---

## Block I — Hands-on (98–115 min)

Participants follow **`lab3/README.md`** Tasks 1–3.

**Common saves:**

- Wrong remote: `git remote -v`.
- CI fails: run `./mvnw test` inside `lab2/maven-junit5` before pushing.
- No permission to merge: trainer temporarily lowers protection or uses fork under trainee account.
- Partner review: use **draft PR** first, then **Ready for review**.

---

## Block J — Close (115–120 min)

**Show:** Deliverables slide + **branching strategy template**.  
**Quiz:** long-lived branch risk; `--force-with-lease`; CODEOWNERS two roles; rebase vs merge (one sentence each).

---

## Phrases that land

- “**Main is a contract** — only merge what you’d run in CI tomorrow morning.”
- “**Review the assertion**, not just the semicolons.”
- “**Branch age** is a risk metric — not just for app repos, for test repos too.”
