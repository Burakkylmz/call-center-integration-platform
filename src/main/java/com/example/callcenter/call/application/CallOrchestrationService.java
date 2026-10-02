package com.example.callcenter.call.application;

import com.example.callcenter.bot.domain.BotDecision;
import com.example.callcenter.bot.port.BotGateway;
import com.example.callcenter.call.domain.CallRoutingRequest;
import com.example.callcenter.call.domain.CallRoutingResult;
import com.example.callcenter.crm.domain.CustomerProfile;
import com.example.callcenter.crm.port.CrmGateway;
import com.example.callcenter.ivr.domain.IvrRoute;
import com.example.callcenter.ivr.port.IvrGateway;
import com.example.callcenter.notification.domain.NotificationResult;
import com.example.callcenter.notification.port.NotificationGateway;
import org.springframework.stereotype.Service;

@Service
public class CallOrchestrationService {

    private final CrmGateway crmGateway;
    private final BotGateway botGateway;
    private final IvrGateway ivrGateway;
    private final NotificationGateway notificationGateway;

    public CallOrchestrationService(
            CrmGateway crmGateway,
            BotGateway botGateway,
            IvrGateway ivrGateway,
            NotificationGateway notificationGateway) {
        this.crmGateway = crmGateway;
        this.botGateway = botGateway;
        this.ivrGateway = ivrGateway;
        this.notificationGateway = notificationGateway;
    }

    public CallRoutingResult route(CallRoutingRequest request) {
        CustomerProfile profile = crmGateway.findCustomer(request.customerRef());
        BotDecision decision = botGateway.classify(request.utterance(), profile.segment());
        IvrRoute ivrRoute = ivrGateway.resolveRoute(request.callId(), decision.intent(), profile.segment());
        NotificationResult notification = notificationGateway.sendRoutingAcknowledgement(
                request.customerRef(),
                ivrRoute.routeCode());

        return new CallRoutingResult(
                request.callId(),
                profile.segment(),
                decision.intent(),
                ivrRoute.routeCode(),
                notification.accepted());
    }
}
