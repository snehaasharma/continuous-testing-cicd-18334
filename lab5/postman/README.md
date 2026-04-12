# Postman / Newman (Lab 5)

- **`collection.json`** — sample requests + **Tests** tab scripts (assertions).
- **`env-ci.json`** — `baseUrl` defaulting to **https://jsonplaceholder.typicode.com** (public; requires network).

```bash
npm install -g newman
mkdir -p results
newman run collection.json -e env-ci.json -r cli,junit \
  --reporter-junit-export results/newman-junit.xml
```

For **internal APIs**, duplicate the environment file, set `baseUrl`, and add auth headers in the collection (use **secrets** in CI, not committed tokens).
