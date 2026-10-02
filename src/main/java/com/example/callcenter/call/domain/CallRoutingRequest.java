package com.example.callcenter.call.domain;

import jakarta.validation.constraints.NotBlank;

public record CallRoutingRequest(
        @NotBlank String callId,
        @NotBlank String customerRef,
        @NotBlank String utterance) {
}
