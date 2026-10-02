# Call Center Integration Platform

A deliberately small but realistic **Java 21 / Spring Boot** repository used throughout the training **Claude Code ile Kurumsal Yazılım Geliştirme**.

The repository is not a Java masterclass project. Its purpose is to expose realistic software-engineering boundaries that Claude Code can discover, reason about, modify, test, review, and govern.

## Why one repository for the whole training?

The training is designed as a continuous engineering workflow. The same codebase is used to demonstrate:

1. repository discovery and agentic work,
2. safe usage boundaries and permissions,
3. context engineering with `CLAUDE.md` and project rules,
4. integration-heavy feature development,
5. unit testing, debugging, and refactoring,
6. Git / Pull Request governance,
7. hooks, reusable skills/subagents, and team standards.

Using separate repositories for each module would remove the accumulated context and governance history that the course is specifically trying to teach.

## Domain

The sample system represents a call-center integration layer.

- **IVR** is treated as a closed external system and exposed only through a Java contract (`IvrGateway`).
- **CRM**, **Bot/AI**, and **Notification** are represented by explicit gateway contracts with deterministic mock adapters.
- No production credentials, customer records, phone numbers, or real transcripts are included.
- Test data is synthetic.

## High-level flow

```text
POST /api/v1/calls/route
        |
        v
CallController
        |
        v
CallOrchestrationService
   |        |        |        |
   v        v        v        v
 CRM      Bot/AI    IVR   Notification
Gateway   Gateway  Gateway    Gateway
   |        |        |        |
   v        v        v        v
 Mock     Mock      Mock      Mock
Adapter   Adapter   Adapter   Adapter
```

## Repository structure

```text
call-center-integration-platform/
├── CLAUDE.md
├── .claude/
│   └── rules/
├── docs/
│   ├── architecture.md
│   ├── training-roadmap.md
│   └── exercises/
├── src/
│   ├── main/
│   │   ├── java/com/example/callcenter/
│   │   │   ├── call/
│   │   │   ├── crm/
│   │   │   ├── bot/
│   │   │   ├── ivr/
│   │   │   └── notification/
│   │   └── resources/application.yml
│   └── test/
└── pom.xml
```

## Requirements

- Java 21
- Maven 3.9+

The project uses Spring Boot **4.1.1**.

## Run

```bash
mvn clean test
mvn spring-boot:run
```

Then call the synthetic endpoint:

```bash
curl -X POST http://localhost:8080/api/v1/calls/route \
  -H "Content-Type: application/json" \
  -d '{
    "callId": "CALL-1001",
    "customerRef": "CUST-1001",
    "utterance": "I need help with my invoice"
  }'
```

Example response:

```json
{
  "callId": "CALL-1001",
  "customerSegment": "STANDARD",
  "detectedIntent": "BILLING",
  "ivrRoute": "billing-desk",
  "notificationAccepted": true
}
```

## Deliberate training seam

The mock CRM adapter intentionally treats customer references beginning with `CRM-TIMEOUT` as a timeout and throws `CrmTimeoutException`. The baseline application **propagates** that exception. This is intentional: Day 2 uses it as a controlled exercise for analysis, test design, failure handling, minimal-change implementation, and regression verification.

Do not "fix" this before the relevant exercise unless the trainer intentionally chooses to move that exercise earlier.

## Suggested Module 1 discovery prompt

```text
Analyze this repository without changing any files.

1. Identify the main modules and their responsibilities.
2. Find the IVR, CRM, Bot/AI, and Notification integration boundaries.
3. Identify the test structure and the most important tests.
4. Explain the request flow from the REST endpoint to the external-system adapters.
5. Identify which files you would inspect next before changing CRM timeout behavior, and explain why.

Do not edit files, run destructive commands, commit, push, or install packages.
```

See `docs/training-roadmap.md` for how the same repository evolves across the three training days.
