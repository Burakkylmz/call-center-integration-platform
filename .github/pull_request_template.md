## Change summary

Describe the smallest meaningful change introduced by this Pull Request.

## Requirement / acceptance criteria

- [ ] Requirement is stated clearly.
- [ ] Acceptance criteria are satisfied.

## Verification evidence

List the exact commands and results used to verify the change.

```text
mvn --batch-mode --no-transfer-progress verify
```

## Risk and scope

- [ ] No secrets, credentials, production URLs, or real customer data were added.
- [ ] The change surface is limited to the requested behavior.
- [ ] Existing tests were not weakened or bypassed.
- [ ] Integration boundaries were preserved unless a contract change was explicitly required.

## Human review

- [ ] Java/Spring correctness reviewed.
- [ ] Domain behavior reviewed.
- [ ] Security/data handling reviewed where relevant.
- [ ] Merge decision remains with an authorized human reviewer.
