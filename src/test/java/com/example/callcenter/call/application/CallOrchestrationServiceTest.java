package com.example.callcenter.call.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.callcenter.bot.domain.BotDecision;
import com.example.callcenter.bot.port.BotGateway;
import com.example.callcenter.call.domain.CallRoutingRequest;
import com.example.callcenter.call.domain.CallRoutingResult;
import com.example.callcenter.crm.adapter.CrmTimeoutException;
import com.example.callcenter.crm.domain.CustomerProfile;
import com.example.callcenter.crm.port.CrmGateway;
import com.example.callcenter.ivr.domain.IvrRoute;
import com.example.callcenter.ivr.port.IvrGateway;
import com.example.callcenter.notification.domain.NotificationResult;
import com.example.callcenter.notification.port.NotificationGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CallOrchestrationServiceTest {

    @Mock
    private CrmGateway crmGateway;

    @Mock
    private BotGateway botGateway;

    @Mock
    private IvrGateway ivrGateway;

    @Mock
    private NotificationGateway notificationGateway;

    private CallOrchestrationService service;

    @BeforeEach
    void setUp() {
        service = new CallOrchestrationService(crmGateway, botGateway, ivrGateway, notificationGateway);
    }

    @Test
    void routesCallAcrossIntegrationBoundaries() {
        CallRoutingRequest request = new CallRoutingRequest(
                "CALL-1001",
                "CUST-1001",
                "I need help with my invoice");

        when(crmGateway.findCustomer("CUST-1001"))
                .thenReturn(new CustomerProfile("CUST-1001", "STANDARD"));
        when(botGateway.classify("I need help with my invoice", "STANDARD"))
                .thenReturn(new BotDecision("BILLING"));
        when(ivrGateway.resolveRoute("CALL-1001", "BILLING", "STANDARD"))
                .thenReturn(new IvrRoute("billing-desk"));
        when(notificationGateway.sendRoutingAcknowledgement("CUST-1001", "billing-desk"))
                .thenReturn(new NotificationResult(true));

        CallRoutingResult result = service.route(request);

        assertThat(result.callId()).isEqualTo("CALL-1001");
        assertThat(result.customerSegment()).isEqualTo("STANDARD");
        assertThat(result.detectedIntent()).isEqualTo("BILLING");
        assertThat(result.ivrRoute()).isEqualTo("billing-desk");
        assertThat(result.notificationAccepted()).isTrue();

        verify(notificationGateway).sendRoutingAcknowledgement("CUST-1001", "billing-desk");
    }

    @Test
    void baselinePropagatesCrmTimeout() {
        CallRoutingRequest request = new CallRoutingRequest(
                "CALL-2001",
                "CRM-TIMEOUT-2001",
                "I need support");

        when(crmGateway.findCustomer("CRM-TIMEOUT-2001"))
                .thenThrow(new CrmTimeoutException("Synthetic CRM timeout"));

        assertThatThrownBy(() -> service.route(request))
                .isInstanceOf(CrmTimeoutException.class)
                .hasMessageContaining("CRM timeout");
    }
}
