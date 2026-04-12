# Master Instructor Guide: Continuous Testing with CI/CD

Welcome to the **Continuous Testing with CI/CD** course! This repository contains everything you need to deliver an engaging, hands-on, 8-day training program.

This guide is designed to ensure that **even a novice instructor** can smoothly deliver this course. By following this rhythm, you will be able to manage the pacing, the technology, and the participants flawlessly.

---

## 🛠️ 1. Instructor Pre-requisites (Do this 24 hours before class)

To deliver the live demos, your machine **must** have the following installed and verified:
- **Java 17+** (Eclipse Temurin recommended)
- **Maven 3.8+** (Added to your system `$PATH`)
- **Git** & a **GitHub Account**
- **Docker Desktop** (Engine running)
- An IDE (**IntelliJ IDEA** Community/Ultimate or Eclipse)
- **Postman** or **Newman CLI** 

**The Golden Rule:** Always run through the `lab[X]` markdown demo script on your own machine *before* arriving to class to ensure your environment isn't blocking you.

---

## 📂 2. Repository Architecture Explained

The curriculum is split carefully into structured folders. **Do not modify the `solutions` or `starter-repo` folders during live teaching.**

- `Day[1-8]_ContinuousTesting_CICD.pdf / .pptx`: The slide decks. This is where you explain the *Theory*.
- `lab1/` through `lab8/`: Daily folders. Inside each is a `Day_X_Instructor_Script_and_Lab.md`. **This is your script.** It tells you exactly what to type on screen during the demo, and exactly what the students do during the lab.
- `starter-repo/`: **CRITICAL.** This is a standalone, working Spring Boot repository. On Day 1/Day 2, you must instruct your students to clone/fork this repo. All student lab work happens *inside* their copy of this repo.
- `solutions/`: **Your Cheat Codes.** If a student breaks their pipeline on Day 4, they cannot proceed to Day 5. You simply send them the `Day4_ci_pipeline.yml` from this folder to unblock them.

---

## ⏱️ 3. The Daily Rhythm (2-Hour Block)

To maintain control of the classroom, adhere strictly to the 40/60 structure outlined in every lab document. 

### Phase A: Theory & Slides (35 - 45 mins)
- Open the `.pptx` file for the corresponding day.
- Deliver the core concepts. Focus on industry anti-patterns (flaky tests, slow builds) because it makes the material relatable.

### Phase B: Live Demo (15 - 20 mins)
- Switch to your IDE and Terminal.
- Open the `Day_X_Instructor_Script_and_Lab.md` file on a second monitor (don't show the script to the students). 
- Follow the "Instructor Live Demo Script" section step-by-step. Let them watch you execute the commands and see the green/red CI outputs. Make a deliberate typo if the script calls for it!

### Phase C: Participant Hands-on Lab (50 - 60 mins)
- Project or share the "Participant Hands-on Lab" section on screen.
- Move around the physical room (or use Zoom breakout rooms) to unblock students. Verify they achieve the defined deliverables for that day.

---

## 🚨 4. Emergency Troubleshooting (What if things go wrong?)

Even the best trainers hit environment issues. Here is how you handle them like a pro:

**"My Maven build says unresolved dependency or Java version error!"**
> This usually means their `$JAVA_HOME` is pointing to Java 8 or 11. REST Assured 6 in our POM requires Java 17. Have them update their IDE SDK paths or terminal `$JAVA_HOME`.

**"My GitHub Actions pipeline says 'maven not found' or immediately fails."**
> Verify their `ci.yml` is checking out the code properly (`actions/checkout@v4`) and setting up Java (`actions/setup-java@v4`). If they are hopelessly stuck, copy `solutions/Day4_ci_pipeline.yml` over to their screen.

**"Docker Compose says port 5432 is already in use."**
> A local Postgres instance is running on their laptop. Have them stop the local service, or instruct the class ahead of time that Docker must be the only database running.

**"I fell behind yesterday and my code doesn't work for today's lab."**
> Never halt the class to fix one student's 2-day-old code. Point them to the `solutions/` folder, have them copy the solution reference into their `starter-repo`, and move on securely.

---

## 🎉 5. Your Day 8 Capstone Execution

Day 8 is entirely different from Days 1-7. You will not be coding. On Day 8:
1. Divide the class into teams of 2 or 3.
2. They are required to use the tools added to `starter-repo` over the week to design a final CI Pipeline architecture.
3. You are acting as the "Principal Engineer" reviewing their Pull Requests. You will grade them strictly on whether they followed the **Quality Gates** (no hardcoded passwords, tests are parallelized if applicable, coverage meets 80%).

*Go confidently! The course is fully structured, container-backed, and natively scalable.*
