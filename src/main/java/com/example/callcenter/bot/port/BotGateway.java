package com.example.callcenter.bot.port;

import com.example.callcenter.bot.domain.BotDecision;

public interface BotGateway {

    BotDecision classify(String utterance, String customerSegment);
}
