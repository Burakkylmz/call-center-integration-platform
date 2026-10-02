package com.example.callcenter.bot.adapter;

import com.example.callcenter.bot.domain.BotDecision;
import com.example.callcenter.bot.port.BotGateway;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class MockBotGateway implements BotGateway {

    @Override
    public BotDecision classify(String utterance, String customerSegment) {
        String normalized = utterance.toLowerCase(Locale.ROOT);

        if (normalized.contains("invoice") || normalized.contains("billing")) {
            return new BotDecision("BILLING");
        }
        if (normalized.contains("error") || normalized.contains("technical")) {
            return new BotDecision("TECH_SUPPORT");
        }
        if ("VIP".equals(customerSegment)) {
            return new BotDecision("PRIORITY_SUPPORT");
        }
        return new BotDecision("GENERAL");
    }
}
