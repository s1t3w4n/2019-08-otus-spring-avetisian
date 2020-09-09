package ru.otus.hw15.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelsConfiguration {

    @Bean
    public MessageChannel caterpillarsChannel() {
        return MessageChannels.queue(100).get();
    }

    @Bean
    public MessageChannel butterfliesChannel() {
        return MessageChannels.direct().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(1).get();
    }

    @Bean
    public IntegrationFlow evolveFlow() {
        return IntegrationFlows.from("caterpillarsChannel")
                .handle("evolveServiceImpl", "evolve")
                .channel("butterfliesChannel")
                .get();
    }
}
