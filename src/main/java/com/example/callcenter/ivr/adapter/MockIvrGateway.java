package com.example.callcenter.ivr.adapter;

import com.example.callcenter.ivr.domain.IvrRoute;
import com.example.callcenter.ivr.port.IvrGateway;
import org.springframework.stereotype.Component;

@Component
public class MockIvrGateway implements IvrGateway {

    @Override
    public IvrRoute resolveRoute(String callId, String intent, String customerSegment) {
        if ("VIP".equals(customerSegment) || "PRIORITY_SUPPORT".equals(intent)) {
            return new IvrRoute("priority-support");
        }
        if ("BILLING".equals(intent)) {
            return new IvrRoute("billing-desk");
        }
        if ("TECH_SUPPORT".equals(intent)) {
            return new IvrRoute("technical-support");
        }
        return new IvrRoute("general-support");
    }
}
