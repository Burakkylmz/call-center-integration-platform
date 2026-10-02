package com.example.callcenter.crm.adapter;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.crm")
public record CrmClientProperties(Duration timeout) {
}
