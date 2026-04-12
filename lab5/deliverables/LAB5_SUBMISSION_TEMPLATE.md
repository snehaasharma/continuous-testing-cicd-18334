# Lab 5 — Submission template

**Name:** ______________________ **Date:** ______________________

## 1. REST Assured

- [ ] Paste output tail of **`./mvnw verify -Dgroups=api`** showing **BUILD SUCCESS** and **Tests run:**

```
(paste here)
```

## 2. CI — REST Assured job

Link or screenshot: **Day 5 — API tests** workflow, **REST Assured** job green.

**Link / notes:** ______________________

## 3. Annotated Failsafe JUnit XML

Paste a **`<failure>`** fragment from **`target/failsafe-reports/`** after a deliberate red run, with your comments on root cause:

```
(paste XML + comments)
```

## 4. Newman

- [ ] Paste tail of **`newman run … -r cli,junit`** showing pass summary **or** link to CI **Newman** job log.

## 5. Reflection

One case where **JSON Schema** catches a bug that **status code 200** alone would miss:

-
