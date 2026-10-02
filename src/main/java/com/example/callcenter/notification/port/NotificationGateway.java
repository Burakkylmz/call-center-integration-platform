package com.example.callcenter.notification.port;

import com.example.callcenter.notification.domain.NotificationResult;

public interface NotificationGateway {

    NotificationResult sendRoutingAcknowledgement(String customerRef, String routeCode);
}
