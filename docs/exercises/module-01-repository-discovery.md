# Module 1 Exercise — Repository Discovery

## Objective

Observe Claude Code's discovery and agentic loop without allowing code changes.

## Trainer prompt

```text
Analyze this repository without modifying any files.

- Map the main modules.
- Find IVR, CRM, Bot/AI, and Notification integration boundaries.
- Show the test structure.
- Trace the request flow from the REST endpoint to each external-system adapter.
- Identify the files that would matter for a future CRM-timeout change.
- Explain why each file matters.

Do not edit files. Do not install packages. Do not commit or push anything.
```

## What to observe

The teaching target is not the final answer alone. Observe:

- which directories Claude inspects first,
- whether it reads `CLAUDE.md` and project rules,
- whether it narrows search scope,
- which symbols/files it searches for,
- whether it reads tests before suggesting a change,
- whether it respects the read-only instruction.
