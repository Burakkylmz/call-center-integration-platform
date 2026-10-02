package com.example.callcenter.crm.adapter;

import com.example.callcenter.crm.domain.CustomerProfile;
import com.example.callcenter.crm.port.CrmGateway;
import org.springframework.stereotype.Component;

@Component
public class MockCrmGateway implements CrmGateway {

    private final CrmClientProperties properties;

    public MockCrmGateway(CrmClientProperties properties) {
        this.properties = properties;
    }

    @Override
    public CustomerProfile findCustomer(String customerRef) {
        if (customerRef.startsWith("CRM-TIMEOUT")) {
            throw new CrmTimeoutException(
                    "Synthetic CRM timeout after " + properties.timeout().toMillis() + " ms");
        }

        String segment = customerRef.startsWith("VIP-") ? "VIP" : "STANDARD";
        return new CustomerProfile(customerRef, segment);
    }
}
