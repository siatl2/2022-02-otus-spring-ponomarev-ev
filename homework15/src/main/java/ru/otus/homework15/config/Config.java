package ru.otus.homework15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
@EnableIntegration
public class Config {
    @Bean
    public DirectChannel larvaChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public DirectChannel pupaChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public DirectChannel butterflyChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow larvaToButterflyFlow() {
        return IntegrationFlows.from("larvaChannel")
                .handle("larvaToPupa", "transform")
                .channel("pupaChannel")
                .handle("pupaToButterfly", "transform")
                .channel("butterflyChannel")
                .get();
    }
}
