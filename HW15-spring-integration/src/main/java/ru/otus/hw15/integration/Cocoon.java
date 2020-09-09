package ru.otus.hw15.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.hw15.domain.Butterfly;
import ru.otus.hw15.domain.Caterpillar;

@MessagingGateway
public interface Cocoon {
    @Gateway(requestChannel = "caterpillarsChannel", replyChannel = "butterfliesChannel")
    Butterfly evolve(Caterpillar caterpillar);
}
