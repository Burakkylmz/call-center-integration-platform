package com.example.callcenter.call.api;

import com.example.callcenter.call.application.CallOrchestrationService;
import com.example.callcenter.call.domain.CallRoutingRequest;
import com.example.callcenter.call.domain.CallRoutingResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calls")
public class CallController {

    private final CallOrchestrationService orchestrationService;

    public CallController(CallOrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    @PostMapping("/route")
    public ResponseEntity<CallRoutingResult> route(@Valid @RequestBody CallRoutingRequest request) {
        return ResponseEntity.ok(orchestrationService.route(request));
    }
}
