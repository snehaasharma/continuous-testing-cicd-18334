# Testcontainers (optional extension — Day 6 theory)

Slides use `@Testcontainers` + `PostgreSQLContainer`. This lab’s **Compose** path avoids Docker-in-Docker on runners.

**To try locally** (Docker required on the host):

1. Add BOM + dependency to `maven-docker-demo/pom.xml`:

   ```xml
   <dependencyManagement>
     <dependencies>
       <dependency>
         <groupId>org.testcontainers</groupId>
         <artifactId>testcontainers-bom</artifactId>
         <version>1.20.4</version>
         <type>pom</type>
         <scope>import</scope>
       </dependency>
     </dependencies>
   </dependencyManagement>
   ```

   ```xml
   <dependency>
     <groupId>org.testcontainers</groupId>
     <artifactId>postgresql</artifactId>
     <scope>test</scope>
   </dependency>
   <dependency>
     <groupId>org.testcontainers</groupId>
     <artifactId>junit-jupiter</artifactId>
     <scope>test</scope>
   </dependency>
   ```

2. Implement a class like the slide’s `UserRepositoryIT` using `getJdbcUrl()`, `waitingFor(...)`, and `@Container static`.

3. Run **`mvn test`** on the host (not inside the Compose `test-runner` unless you mount **`/var/run/docker.sock`** — advanced / not required for this module).

**Ryuk** cleans up labelled resources after the JVM exits — compare with Compose **`down -v`**.
