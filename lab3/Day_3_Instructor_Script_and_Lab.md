# Day 3: Version Control & Test Code Management

**Session Duration:** 2 Hours  
**Focus:** 45% Theory | 55% Hands-on  
**Goal:** Manage test code effectively using Git workflows, implement branching/merge strategies tailored for test automation, and collaborate via PRs.

---

## Part 1: Instructor Live Demo Script (Approx. 40-50 mins)

*Instructors: Follow this step-by-step guide to demonstrate branching, pull requests, and resolving conflicts for test code.*

### Demo 1: Creating Repositories for Automation Projects

**Goal:** Establish a single source of truth for the test automation framework codebase.

1. **Context Setting:** 
   *(Instructor Script)*: "Test automation code is production code. It deserves the same version control, branching, and code review rigor as the application it tests."
2. **Action:**
   - Go to GitHub / GitLab and create a new repository.
   - Initialise with a `README.md` and the standard Java/Python `.gitignore`.
   - Clone the repo locally (`git clone <url> && cd <repo>`).
   - Copy the Day 2 framework skeleton codebase into the Repo, and commit the initial structure.
   - Push to origin: `git push -u origin main`.

### Demo 2: Commit, Merge, and Pull Request Flow

**Goal:** Understand how to use short-lived branches to propose and review changes cleanly.

1. **Branch Creation:**
   - Create a feature branch: `git checkout -b feat/QA-101-login-smoke`
2. **Commit with Conventions:**
   - Make a change (e.g. adding a new test method).
   - Show how to use Conventional Commits: 
     `git commit -m "feat(login): add smoke test for invalid credentials"`
3. **Pull Request:**
   - Push branch to remote: `git push origin feat/QA-101-login-smoke`
   - Open a PR in the GitHub UI. Point out the CI status checks executing automatically against the PR.
   - Show the `.github/CODEOWNERS` file automatically assigning the right review squad.
   - Merge the PR once approved.

### Demo 3: Handling Merge Conflicts

**Goal:** De-mystify git conflicts and show the standard rebase strategy.

1. **Context Setting:**
   *(Instructor Script)*: "Long-lived test branches are the enemy of continuous integration. If I wait two weeks to merge my Page Object updates, someone else will inevitably change the same files, causing a merge conflict."
2. **Simulate Conflict:**
   - Trainer edits `LoginPage.java` directly on main via GitHub UI.
   - At the same time, edit the same line on a local branch.
3. **Resolve Conflict:**
   - `git fetch origin`
   - `git rebase origin/main` (Show the conflict markers popping up).
   - Resolve the conflict in the IDE.
   - `git add <file>` and `git rebase --continue`.
   - Safely force push the rebased branch: `git push --force-with-lease`.

---

<br>

## Part 2: Participant Hands-on Lab (Approx. 65-75 mins)

**Welcome to the Day 3 Lab!** In this lab, you will participate in a team Git workflow, creating branches, reviewing Pull Requests, and handling feedback professionally.

### Task 1: Push Automation Code to Repository

1. Initialize a new local Git repository (or clone an empty remote repo your instructor provided).
2. Copy the Day 2 framework skeleton into the directory.
3. Review your `.gitignore` to make sure `/target` or `/build` directories won't be pushed.
4. Stage and commit your code:
   ```bash
   git add .
   git commit -m "chore: add automation framework skeleton"
   ```
5. Push to remote: `git push origin main`.

### Task 2: Create and Merge Feature Branches

1. Branch off `main` using standard naming conventions:
   ```bash
   git checkout -b feat/QA-101-login-test
   ```
2. Add a new test class or method (it can literally simply test `assertTrue(true)` for now).
3. Commit with a Conventional Commit message.
4. Push the branch and open a **Pull Request** on GitHub.
5. **Teamwork:** Swap computers with a partner (or send them your PR link).
6. Review their PR, leave a complimentary comment or request a minor change, approve it, and Merge it.

### Task 3: Apply Code Review Feedback

1. Open your partner's PR, and leave an actual inline comment requesting a specific code change (e.g., "Please extract this hardcoded URL into a variable.")
2. As the PR author, go to your local code, make the requested change.
3. Add a new commit, and push it up to the existing branch.
4. Keep pushing commits until your reviewer approves the PR.
5. Verify the CI turns green before merging.

---

## Final Deliverables Check

Before leaving the session, ensure you have:
- [ ] A Git Repository with test automation code pushed.
- [ ] Successfully Merged a Pull Request in the repository history.
- [ ] Resolved PR with comments demonstrating code review in action.
