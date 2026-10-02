package com.example.callcenter.ivr.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MockIvrGatewayTest {

    private final MockIvrGateway gateway = new MockIvrGateway();

    @Test
    void resolvesBillingRoute() {
        assertThat(gateway.resolveRoute("CALL-1", "BILLING", "STANDARD").routeCode())
                .isEqualTo("billing-desk");
    }

    @Test
    void resolvesPriorityRouteForVipCustomer() {
        assertThat(gateway.resolveRoute("CALL-1", "GENERAL", "VIP").routeCode())
                .isEqualTo("priority-support");
    }
}
