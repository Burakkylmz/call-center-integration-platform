# Training Roadmap: One Repository, Progressive Governance

The same repository is used for the full training. Each section adds engineering behavior or governance to the existing codebase rather than switching to a new project.

## Day 1 — Agentic model, safety, context, and token discipline

Start from the baseline repository.

Use it to demonstrate:

- repository discovery,
- read/search before write,
- tool and shell feedback loops,
- developer-agent responsibility boundaries,
- usage boundaries,
- `CLAUDE.md` and `.claude/rules`,
- synthetic-data and secret restrictions,
- plan-first analysis,
- scoped context instead of whole-repository exploration.

The primary exercise is read-only repository analysis.

## Day 2 — Integration-heavy development and human review

Use the existing CRM timeout seam.

Suggested feature progression:

1. analyze current timeout behavior,
2. identify affected modules and tests,
3. define desired fallback behavior and acceptance criteria,
4. implement a minimal change,
5. add failure-path tests,
6. run tests,
7. inspect the diff,
8. perform human review,
9. optionally create a focused reviewer subagent/skill.

Additional exercises can target Bot intent handling, notification failures, or refactoring of orchestration logic.

## Day 3 — Git/GitHub governance, hooks, and team standards

Keep the implementation from Day 2 and add process controls around it.

Suggested progression:

- create a feature branch,
- inspect status/diff/log,
- introduce deterministic validation hooks,
- prevent protected-file or unsafe-command patterns,
- prepare an atomic commit,
- prepare a Pull Request description,
- run CI checks,
- conduct independent human review,
- discuss merge authority and protected branches,
- version team-level Claude rules alongside the code.

The end state should remain understandable without Claude session history. Source code, tests, documentation, Git history, and CI are the canonical project record.
