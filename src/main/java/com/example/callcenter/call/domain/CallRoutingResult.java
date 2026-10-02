package com.example.callcenter.call.domain;

public record CallRoutingResult(
        String callId,
        String customerSegment,
        String detectedIntent,
        String ivrRoute,
        boolean notificationAccepted) {
}
