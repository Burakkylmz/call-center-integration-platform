# Git Governance Rules

- Keep commits atomic when commits are explicitly requested.
- Never push, force-push, merge, or delete branches without explicit developer approval.
- Before a proposed commit, inspect `git status` and `git diff`.
- Do not bypass hooks or CI checks with flags such as `--no-verify`.
- Human review remains required before merge.
