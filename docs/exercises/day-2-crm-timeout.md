# Day 2 Exercise — CRM Timeout Handling

## Baseline

`MockCrmGateway` throws `CrmTimeoutException` when `customerRef` starts with `CRM-TIMEOUT`. The orchestration service currently propagates the failure.

## Exercise goal

Use Claude Code to analyze and implement a product decision without bulk changes.

Possible requirement for the workshop:

> When CRM times out, route the call using an `UNKNOWN` customer segment instead of failing the whole request. Preserve the current behavior for non-timeout CRM failures.

## Required workflow

1. Analyze affected files without editing.
2. Produce acceptance criteria.
3. Propose the minimal change set.
4. Get human approval of the plan.
5. Add/adjust tests first or alongside implementation.
6. Implement.
7. Run targeted tests, then the broader test suite.
8. Inspect the diff.
9. Perform human review of Java/Spring correctness.

## Review questions

- Did the change catch only the intended exception?
- Did it preserve the gateway boundary?
- Is the fallback domain behavior explicit?
- Were existing tests weakened?
- Did unrelated files change?
