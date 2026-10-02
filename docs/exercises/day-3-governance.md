# Day 3 Exercise — Git and Governance

Use the Day 2 implementation as the change set to govern.

## Exercise targets

- inspect `git status`, `git diff`, and `git log`,
- create/verify an atomic commit plan,
- add deterministic checks rather than relying only on model judgment,
- prepare a Pull Request description with test evidence,
- keep push/merge authority with the human developer,
- demonstrate that the repository remains the source of truth after the Claude session ends.

## Protected examples

During the exercise, treat these as protected or approval-gated operations:

- force push,
- hard reset,
- branch deletion,
- bypassing hooks,
- modifying deployment/production settings,
- adding secrets or production endpoints.
