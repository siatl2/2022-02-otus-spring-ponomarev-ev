package ru.otus.homework15.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.homework15.model.Butterfly;
import ru.otus.homework15.model.Larva;

@MessagingGateway
public interface LarvaToButterflyGateway {
    @Gateway(requestChannel = "larvaChannel", replyChannel = "butterflyChannel")
    Butterfly process(Larva larva);
}
