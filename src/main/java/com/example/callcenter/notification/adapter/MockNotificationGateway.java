package com.example.callcenter.notification.adapter;

import com.example.callcenter.notification.domain.NotificationResult;
import com.example.callcenter.notification.port.NotificationGateway;
import org.springframework.stereotype.Component;

@Component
public class MockNotificationGateway implements NotificationGateway {

    @Override
    public NotificationResult sendRoutingAcknowledgement(String customerRef, String routeCode) {
        return new NotificationResult(true);
    }
}
