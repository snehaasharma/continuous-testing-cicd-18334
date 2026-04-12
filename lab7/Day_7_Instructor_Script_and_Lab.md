# Day 7: Continuous Testing with CI/CD - Report, Gate & Optimise

**Session Duration:** 2 Hours  
**Focus:** 40% Theory | 60% Hands-on  
**Goal:** Teach participants how to generate comprehensive test reports, enforce quality gates using coverage, and optimize CI pipelines for speed.

---

## Part 1: Instructor Live Demo Script (Approx. 40-45 mins)

*Instructors: Follow this step-by-step guide to demonstrate the core concepts before students start their hands-on lab.*

### Demo 1: Generating and Publishing an Allure Report

**Goal:** Show how to augment standard JUnit tests with rich metadata and generate a human-readable Allure Report.

1. **Context Setting:** 
   *(Instructor Script)*: "Standard JUnit XML reports are great for machines but hard for humans to read, especially when analyzing trends or complex failures. We’re going to use Allure to build a rich, interactive test report."

2. **Update `pom.xml`:**
   Add the following dependency to your `pom.xml`:
   ```xml
   <dependency>
       <groupId>io.qameta.allure</groupId>
       <artifactId>allure-junit5</artifactId>
       <version>2.33.0</version>
       <scope>test</scope>
   </dependency>
   ```
   Add the Allure Maven plugin in the `<plugins>` section:
   ```xml
   <plugin>
       <groupId>io.qameta.allure</groupId>
       <artifactId>allure-maven</artifactId>
       <version>2.12.0</version>
   </plugin>
   ```

3. **Annotate a Test Case:**
   Show how to add metadata to an existing test class.
   ```java
   import io.qameta.allure.Feature;
   import io.qameta.allure.Story;
   import io.qameta.allure.Severity;
   import io.qameta.allure.SeverityLevel;
   import org.junit.jupiter.api.Test;

   @Feature("Login functionality")
   public class LoginTest {

       @Test
       @Story("Valid credentials login flow")
       @Severity(SeverityLevel.CRITICAL)
       public void testSuccessfulLogin() {
           // Test logic here
           org.junit.jupiter.api.Assertions.assertTrue(true);
       }
   }
   ```

4. **Execute and Generate Report:**
   - Run tests: `mvn clean test`
   - Point out the generated JSON results in `target/allure-results/`.
   - Generate the HTML report: `mvn allure:report`
   - Open `target/site/allure-maven-plugin/index.html` in the browser and showcase the Dashboard, Failures, and Categories tabs.

---

### Demo 2: Configuring JaCoCo Coverage Thresholds (Quality Gates)

**Goal:** Demonstrate how to block a build if code coverage falls below a certain threshold.

1. **Context Setting:**
   *(Instructor Script)*: "Code coverage tells us what code wasn't executed during our tests. But it's not enough to just measure it; we need to enforce it. We will configure JaCoCo to fail the build if minimum coverage isn't met."

2. **Update `pom.xml` for JaCoCo:**
   ```xml
   <plugin>
       <groupId>org.jacoco</groupId>
       <artifactId>jacoco-maven-plugin</artifactId>
       <version>0.8.14</version>
       <executions>
           <execution>
               <id>prepare-agent</id>
               <goals>
                   <goal>prepare-agent</goal>
               </goals>
           </execution>
           <execution>
               <id>report</id>
               <phase>test</phase>
               <goals>
                   <goal>report</goal>
               </goals>
           </execution>
           <execution>
               <id>check</id>
               <phase>verify</phase>
               <goals>
                   <goal>check</goal>
               </goals>
               <configuration>
                   <rules>
                       <rule>
                           <element>BUNDLE</element>
                           <limits>
                               <limit>
                                   <counter>LINE</counter>
                                   <value>COVEREDRATIO</value>
                                   <minimum>0.80</minimum>
                               </limit>
                               <limit>
                                   <counter>BRANCH</counter>
                                   <value>COVEREDRATIO</value>
                                   <minimum>0.70</minimum>
                               </limit>
                           </limits>
                       </rule>
                   </rules>
               </configuration>
           </execution>
       </executions>
   </plugin>
   ```

3. **Run Verification:**
   - Run: `mvn verify`
   - Show the terminal output. If coverage is met, the build passes.
   - Show the HTML report at `target/site/jacoco/index.html`.

4. **Trigger a Gate Failure:**
   - Go to a Java source file and add a new un-tested method, OR format out a test method.
   - Run `mvn verify` again.
   - Show the terminal throwing a **BUILD FAILURE** due to unmet coverage thresholds.

---

### Demo 3: Identifying and Fixing a Pipeline Bottleneck

**Goal:** Teach how to optimize slow pipelines using dependency caching and test sharding.

1. **Context Setting:**
   *(Instructor Script)*: "A slow CI pipeline discourages developers from pushing code. A good target for a pipeline is under 10 minutes. Let's see how we can drastically reduce times by caching Maven dependencies and running tests in parallel."

2. **Identify Bottlenecks:**
   - Open a recent GitHub Actions run.
   - Click a completed job and expand the steps to see the timing. Notice how dependency download and test execution take the longest.

3. **Add Maven Caching (`ci.yml`):**
   ```yaml
   - name: Cache Maven packages
     uses: actions/cache@v4
     with:
       path: ~/.m2
       key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
       restore-keys: ${{ runner.os }}-m2
   ```

4. **Introduce Test Sharding (Matrix Strategy):**
   - Update `ci.yml` to split integration tests:
   ```yaml
   integration-test:
     needs: unit-test
     runs-on: ubuntu-latest
     strategy:
       fail-fast: false
       matrix:
         shard: [1, 2] # Splitting into two parallel runners
     steps:
       # ... checkout and setup java steps ...
       - name: Run Integration Tests
         run: mvn verify -Dshard.index=${{ matrix.shard }} -Dshard.total=2
   ```
   *(Note: Ensure your project configuration supports the `shard.index` properties if you actually run this, or demonstrate the theoretical division of jobs in the UI).*

---

<br>

## Part 2: Participant Hands-on Lab (Approx. 75 mins)

**Welcome to the Day 7 Lab!** In this lab, you will generate an Allure report, enforce a code coverage gate using JaCoCo, and optimize your GitHub Actions runner.

### Task 1: Generate Allure Report with Test Annotations

1. Open your project's `pom.xml`.
2. Add the `allure-junit5` (v2.33.0) dependency and configure the `allure-maven` plugin.
3. Open at least 3 existing test cases inside your `src/test/java` directory.
4. Annotate them using Allure descriptors: `@Feature("Your Feature")`, `@Story("Your Story")`, and `@Severity(SeverityLevel.NORMAL)`.
5. Open your terminal and run:
   ```bash
   mvn test
   mvn allure:report
   ```
6. Open `target/site/allure-maven-plugin/index.html` in your browser. Verify your annotations appear.
7. **Bonus:** Update your `.github/workflows/ci.yml` with steps to publish using `simple-elf/allure-report-action` and `peaceiris/actions-gh-pages`. Push your code and confirm the report hosts successfully on GitHub pages!

### Task 2: Configure and Trigger a JaCoCo Coverage Gate

1. Open your `pom.xml` and add the `jacoco-maven-plugin` (v0.8.14).
2. Configure the `prepare-agent`, `report`, and `check` goals.
3. Set your limits: **LINE** minimum `0.80` (80%) and **BRANCH** minimum `0.70` (70%).
4. Run the verification:
   ```bash
   mvn verify
   ```
   *Confirm the build passes successfully.*
5. **Fail the Gate!** 
   - Comment out a couple of test methods in your test classes to simulate dropping coverage.
   - Run `mvn verify` again.
   - **Confirm** you get a `BUILD FAILURE` message in your console!
6. Restore your tests, confirm tests pass again, and add the `mvn verify` step directly into your CI workflow (`ci.yml`).

### Task 3: Optimise Slow CI Pipeline

1. **Baseline Measurement:** Go to your GitHub repository's Actions tab. Check your last full workflow execution time. Note this down.
2. **Setup Caching:** Open `.github/workflows/ci.yml` and add the `actions/cache@v4` step for `~/.m2` directly after your JDK setup step.
3. Commit and push the changes. Compare the new execution duration.
4. **Sharding Tests:** Modify your `integration-test` or `test` job to use a `matrix` strategy with two shards (`shard: [1, 2]`).
5. Commit, push the code, and analyze the resulting runs in the GitHub Actions UI. Notice how the jobs run in parallel now. 
6. **Comparison:** In a short text file (`optimisation-notes.txt` in your repo), optionally document the duration before and after your optimizations.

---

## Final Deliverables Check

Before leaving the session, ensure you have:
- [ ] Published your Allure Test Report with a trend history (GitHub Pages URL).
- [ ] Pipeline CI Evidence showing your JaCoCo gate passing, as well as a screenshot of your deliberate failure.
- [ ] Implemented dependency caching in your CI workflow file.
