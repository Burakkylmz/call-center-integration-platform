# Architecture

## Goal

Provide a compact integration-heavy codebase that is large enough for repository discovery, dependency tracing, testing, debugging, refactoring, and governance exercises without requiring access to real third-party systems.

## Style

The code uses a light ports-and-adapters shape:

- `call` owns the orchestration use case and HTTP entry point.
- `crm`, `bot`, `ivr`, and `notification` each expose a gateway contract.
- `adapter` packages contain deterministic mock implementations.
- The orchestration service depends on gateway interfaces, not adapter classes.

This is intentionally not a full hexagonal-architecture framework. The boundaries exist because they are useful for the training scenario.

## Request flow

1. `CallController` accepts a synthetic call-routing request.
2. `CallOrchestrationService` asks CRM for a customer profile.
3. Bot/AI classifies the utterance into an intent.
4. IVR resolves the destination route.
5. Notification records a synthetic acknowledgement.
6. A `CallRoutingResult` is returned to the caller.

## External systems

### IVR

`IvrGateway` is the contract boundary for a closed IVR platform. The repository does not model IVR internals.

### CRM

`CrmGateway` resolves a synthetic customer profile. `MockCrmGateway` contains the deliberate timeout behavior used in a Day 2 failure-handling exercise.

### Bot/AI

`BotGateway` deterministically maps example text to an intent. It does not call a real LLM.

### Notification

`NotificationGateway` records a deterministic accepted response. It does not send SMS, email, or push notifications.

## Dependency direction

```text
HTTP -> call service -> gateway interfaces <- mock adapters
```

The call service may depend on domain records returned by the gateways. It must not depend on concrete adapter implementations.

## Data policy

All repository fixtures and examples are synthetic. The codebase must remain safe to use in a classroom and to publish as a public training repository.
