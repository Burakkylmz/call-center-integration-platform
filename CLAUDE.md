# Project Instructions for Claude Code

## Purpose

This repository is a training codebase for a call-center integration platform. Treat it as an enterprise-style integration project, not as a greenfield toy application.

## Working principles

1. Investigate before editing. Read the relevant production code, tests, configuration, and project rules before proposing a change.
2. Prefer the smallest change surface that satisfies the requirement.
3. Do not rewrite unrelated code or introduce abstractions without a concrete need.
4. Never treat generated code as automatically correct. Provide evidence from tests, build output, and diffs.
5. If a requirement is ambiguous, identify the ambiguity instead of silently inventing domain behavior.

## Integration boundaries

- `ivr` represents a closed external IVR platform. Do not invent internal IVR implementation details.
- `crm`, `bot`, and `notification` are external-system boundaries represented by gateway interfaces and mock adapters.
- Keep external integration behavior behind the existing gateway contracts unless the task explicitly requires a contract change.

## Data and security

- Use synthetic data only.
- Do not add real customer data, phone numbers, credentials, access tokens, secrets, production URLs, or copied production logs.
- Do not print sensitive values to logs.
- Do not add dependencies or external network calls unless the task explicitly requires them and the developer approves them.

## Testing

- Read existing tests before changing behavior.
- Add or update tests for observable behavior changes.
- Cover happy path, boundary/negative cases, and relevant integration failures.
- Do not weaken, delete, or bypass tests merely to make a change pass.

## Git and destructive actions

Do not perform these actions unless the developer explicitly asks for them:

- `git push`
- `git push --force`
- `git reset --hard`
- branch deletion
- file-system deletion outside clearly task-owned temporary files
- changing CI/CD or deployment configuration
- committing secrets

Before any commit request, show the intended diff and summarize test evidence.
