package com.example.callcenter.ivr.port;

import com.example.callcenter.ivr.domain.IvrRoute;

public interface IvrGateway {

    IvrRoute resolveRoute(String callId, String intent, String customerSegment);
}
