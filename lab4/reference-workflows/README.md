# Reference workflows (Day 4)

Copy or adapt these into your own repository layout.

| File | Use case |
|------|----------|
| `github-actions-matrix-snippet.yml` | Paste into a workflow to teach **matrix** + **`fail-fast`** (not active until merged). |
| `Jenkinsfile` | Declarative pipeline; tune `tools { }` to your Jenkins **Global Tool Configuration**. |
| `azure-pipelines.yml` | Multi-stage; uses Microsoft-hosted **ubuntu-latest** + **Java 17**. |

This course’s **canonical** GitHub workflow for Lab 2 lives at **`.github/workflows/day4-pipeline.yml`** (repository root).
