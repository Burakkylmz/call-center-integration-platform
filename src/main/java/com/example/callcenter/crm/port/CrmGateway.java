package com.example.callcenter.crm.port;

import com.example.callcenter.crm.domain.CustomerProfile;

public interface CrmGateway {

    CustomerProfile findCustomer(String customerRef);
}
