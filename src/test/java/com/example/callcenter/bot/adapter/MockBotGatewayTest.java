package com.example.callcenter.bot.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MockBotGatewayTest {

    private final MockBotGateway gateway = new MockBotGateway();

    @Test
    void classifiesBillingIntent() {
        assertThat(gateway.classify("Please explain my invoice", "STANDARD").intent())
                .isEqualTo("BILLING");
    }

    @Test
    void classifiesTechnicalIntent() {
        assertThat(gateway.classify("I have a technical error", "STANDARD").intent())
                .isEqualTo("TECH_SUPPORT");
    }

    @Test
    void routesVipCustomerToPrioritySupportWhenIntentIsOtherwiseGeneral() {
        assertThat(gateway.classify("I need help", "VIP").intent())
                .isEqualTo("PRIORITY_SUPPORT");
    }
}
