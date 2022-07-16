package ru.otus.homework15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework15.config.Config;
import ru.otus.homework15.config.LarvaToButterflyGateway;
import ru.otus.homework15.model.Butterfly;
import ru.otus.homework15.model.Larva;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Config.class})
@ComponentScan(basePackages = "ru.otus.homework15.service")
@EnableIntegration
@IntegrationComponentScan
public class IntegrationTest {
    private static final String INCECT_NAME = "Test";
    @Autowired
    LarvaToButterflyGateway gateway;

    @Test
    public void whenInsectTransformthenNamesEqauals() throws ExecutionException, InterruptedException {
        Callable thread =
                () -> {
                    Larva larva = new Larva(INCECT_NAME);
                    Butterfly butterfly = gateway.process(larva);
                    return butterfly.getName();
                };
        FutureTask<String> future = new FutureTask<>(thread);
        new Thread(future).start();

        assertEquals(INCECT_NAME, future.get());
    }
}
