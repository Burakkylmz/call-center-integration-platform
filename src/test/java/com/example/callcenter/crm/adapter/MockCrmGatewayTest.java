package com.example.callcenter.crm.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class MockCrmGatewayTest {

    private final MockCrmGateway gateway = new MockCrmGateway(new CrmClientProperties(Duration.ofMillis(750)));

    @Test
    void returnsVipProfileForVipReference() {
        assertThat(gateway.findCustomer("VIP-42").segment()).isEqualTo("VIP");
    }

    @Test
    void returnsStandardProfileForNormalReference() {
        assertThat(gateway.findCustomer("CUST-42").segment()).isEqualTo("STANDARD");
    }

    @Test
    void simulatesTimeoutForTrainingReference() {
        assertThatThrownBy(() -> gateway.findCustomer("CRM-TIMEOUT-42"))
                .isInstanceOf(CrmTimeoutException.class)
                .hasMessageContaining("750 ms");
    }
}
